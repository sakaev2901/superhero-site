package repositories;

import config.ConnectionConfig;
import models.Ability;

import javax.enterprise.inject.Stereotype;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AbilityRepositoryImpl implements AbilityRepository{

    private final static ConnectionConfig CONNECTION_CONFIG = new ConnectionConfig();
    private final static String FIND_BY_NAME = "SELECT * FROM ability WHERE \"name\" = ?";
    private final static String FIND_ALL = "SELECT * FROM ability";
    private final static String FIND_HERO_ABILITIES = "SELECT name, description, id FROM ability INNER JOIN (SELECT * FROM hero_ability WHERE \"hero_id\" = ?) AS t ON id = t.ability_id";
    private final static String SAVE = "INSERT INTO ability(name, description) VALUES (?, ?)";

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet set;

    public Ability findById(Long id) {
        return null;
    }

    public List<Ability> findAll() {
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(FIND_ALL);
            set = statement.executeQuery();
            List<Ability> abilities = new LinkedList<>();
            while (set.next()) {
                Ability ability = Ability.builder()
                        .id(set.getLong("id"))
                        .name(set.getString("name"))
                        .description(set.getString("description"))
                        .build();
                abilities.add(ability);
            }
            return abilities;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }

    public void delete(Long id) {

    }

    public void update(Ability model) {

    }

    public void save(Ability model) {
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(SAVE);
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }

    @Override
    public Ability findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        Ability ability = null;
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(FIND_BY_NAME);
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                ability = Ability.builder()
                        .id(set.getLong("id"))
                        .name(set.getString("name"))
                        .description(set.getString("description"))
                        .build();
            }
            return ability;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(connection);
            CONNECTION_CONFIG.close(statement);
        }
    }

    @Override
    public List<Ability> findHeroAbilities(Long id) {
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(FIND_HERO_ABILITIES);
            statement.setLong(1, id);
            set = statement.executeQuery();
            List<Ability> abilities = new LinkedList<>();
            while (set.next()) {
                Ability ability = Ability.builder()
                        .id(set.getLong("id"))
                        .name(set.getString("name"))
                        .description(set.getString("description"))
                        .build();
                abilities.add(ability);
            }
            return abilities;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }
}
