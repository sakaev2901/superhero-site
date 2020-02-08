package repositories;

import config.ConnectionConfig;
import models.Ability;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AbilityRepositoryImpl implements AbilityRepository{

    private final static ConnectionConfig CONNECTION_CONFIG = new ConnectionConfig();
    private final static String FIND_BY_NAME = "SELECT * FROM ability WHERE \"name\" = ?";

    public Ability findById(Long id) {
        return null;
    }

    public List<Ability> findAll() {
        return null;
    }

    public void delete(Long id) {

    }

    public void update(Ability model) {

    }

    public void save(Ability model) {

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
}
