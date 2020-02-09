package repositories;

import config.ConnectionConfig;
import models.Ability;
import models.Hero;
import models.SearchCriteria;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {

    private final static ConnectionConfig CONNECTION_CONFIG = new ConnectionConfig();
    private final static String SAVE = "INSERT INTO hero(name, description, power, endurance, dexterity, photo_path) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private final static String HERO_ABILITY_BINDING = "INSERT INTO hero_ability VALUES(?, ?)";
    private final static String FIND_BY_ID = "SELECT * FROM hero WHERE \"id\" = ?";
    private final static String FIND_HERO_ABILITIES = "SELECT name, description, id FROM ability INNER JOIN (SELECT * FROM hero_ability WHERE \"hero_id\" = ?) AS t ON id = t.ability_id";
    private final static String FIND_ALL = "SELECT * FROM hero";
    private final static String DELETE = "DELETE FROM hero WHERE \"id\" = ?";
    private final static String FIND_BY_CRITERIA_WITHOUT_ABILITIES = "SELECT * FROM hero WHERE hero.power >= ? AND hero.power <= ? " +
            "AND hero.endurance >= ? AND hero.endurance <= ? " +
            "AND hero.dexterity >= ? AND hero.dexterity <= ?";
    private final static String UPDATE = "UPDATE hero SET \"name\" = ?, \"description\" = ?, \"power\" = ?, " +
            "\"endurance\" = ?, \"dexterity\" = ? WHERE \"id\" = ?";
    private final static String DELETE_HERO_ABILITIES = "DELETE FROM hero_ability WHERE hero_id = ?";

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet set;

    public Hero findById(Long id) {
        Hero hero = null;
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            set = statement.executeQuery();
            if (set.next()) {
                hero = heroInitialization();
            }
            return hero;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(connection);
            CONNECTION_CONFIG.close(statement);
        }
    }

    public List<Hero> findAll() {
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(FIND_ALL);
            set = statement.executeQuery();
            List<Hero> heroes = new LinkedList<>();
            while (set.next()) {
                heroes.add(heroInitialization());
            }
            return heroes;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }

    private Hero heroInitialization() throws SQLException{
        Hero hero = null;
        hero = Hero.builder()
                    .id(set.getLong("id"))
                    .name(set.getString("name"))
                    .description(set.getString("description"))
                    .power(set.getInt("power"))
                    .endurance(set.getInt("endurance"))
                    .dexterity(set.getInt("dexterity"))
                    .photoPath(set.getString("photo_path"))
                    .abilities(new LinkedList<>())
                    .build();
        statement = connection.prepareStatement(FIND_HERO_ABILITIES);
        statement.setLong(1, hero.getId());
        ResultSet abilitySet = statement.executeQuery();
        while (abilitySet.next()) {
            Ability ability = Ability.builder()
                    .id(abilitySet.getLong("id"))
                    .name(abilitySet.getString("name"))
                    .description(abilitySet.getString("description"))
                    .build();
            hero.addAbility(ability);
        }
        return hero;
    }

    public void delete(Long id) {
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }

    public void update(Hero model) {
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setInt(3, model.getPower());
            statement.setInt(4, model.getEndurance());
            statement.setInt(5, model.getDexterity());
            statement.setLong(6, model.getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(DELETE_HERO_ABILITIES);
            statement.setLong(1, model.getId());
            statement.executeUpdate();
            if (model.getPhotoPath() != null) {
                statement = connection.prepareStatement("UPDATE hero SET \"photo_path\" = ? WHERE \"id\" = ?");
                statement.setString(1, model.getPhotoPath());
                statement.setLong(2, model.getId());
                statement.executeUpdate();
            }
            statement = connection.prepareStatement(HERO_ABILITY_BINDING);
            List<Ability> abilities = model.getAbilities();
            for (Ability ability:
                    abilities) {
                statement.setLong(1, model.getId());
                statement.setLong(2, ability.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new  IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }

    public void save(Hero model) {
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setInt(3, model.getPower());
            statement.setInt(4, model.getEndurance());
            statement.setInt(5, model.getDexterity());
            statement.setString(6, model.getPhotoPath());
            statement.executeUpdate();
            set = statement.getGeneratedKeys();
            Long heroId = null;
            if(set.next()) {
                heroId = set.getLong(1);
            }
            statement = connection.prepareStatement(HERO_ABILITY_BINDING);
            List<Ability> abilities = model.getAbilities();
            for (Ability ability:
                 abilities) {
                statement.setLong(1, heroId);
                statement.setLong(2, ability.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(connection);
            CONNECTION_CONFIG.close(statement);
        }
    }

    @Override
    public List<Hero> findByCriteria(SearchCriteria criteria) {
        try {
            String sql = "";
            connection = CONNECTION_CONFIG.getConnection();
            if (criteria.getName().equals("") && criteria.getDescription().equals("")) {
                sql = FIND_BY_CRITERIA_WITHOUT_ABILITIES + " AND hero.name <> ? AND hero.description <> ?";
            }
            if (criteria.getName().equals("") && !criteria.getDescription().equals("")) {
                sql = FIND_BY_CRITERIA_WITHOUT_ABILITIES + " AND hero.name <> ? AND hero.description = ?";
            }
            if (!criteria.getName().equals("") && criteria.getDescription().equals("")) {
                sql = FIND_BY_CRITERIA_WITHOUT_ABILITIES + " AND hero.name = ? AND hero.description <> ?";

            }
            if (!criteria.getName().equals("") && !criteria.getDescription().equals("")) {
                sql = FIND_BY_CRITERIA_WITHOUT_ABILITIES + " AND hero.name = ? AND hero.description = ?";

            }
            statement = connection.prepareStatement(sql);
            statement.setInt(1, criteria.getPowerFrom());
            statement.setInt(2, criteria.getPowerTo());
            statement.setInt(3, criteria.getEnduranceFrom());
            statement.setInt(4, criteria.getEnduranceTo());
            statement.setInt(5, criteria.getDexterityFrom());
            statement.setInt(6, criteria.getDexterityTo());
            statement.setString(7, criteria.getName());
            statement.setString(8, criteria.getDescription());
            set = statement.executeQuery();
            List<Hero> heroes = new LinkedList<>();
            while (set.next()) {
                heroes.add(heroInitialization());
            }
            return heroes;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }
}
