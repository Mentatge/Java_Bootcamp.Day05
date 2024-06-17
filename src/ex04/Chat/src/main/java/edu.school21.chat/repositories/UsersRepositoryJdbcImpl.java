package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = getPreparedStatement(connection, page, size);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"), new ArrayList<>(), new ArrayList<>());
                    Chatroom chatroom = new Chatroom(resultSet.getLong("room_id"), resultSet.getString("room_name"), user, new ArrayList<>());
                    if (resultSet.getString("status").equals("owner")) {
                        user.addCreatedRoom(chatroom);
                    } else {
                        user.addRoom(chatroom);
                    }
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private PreparedStatement getPreparedStatement(Connection connection, int page, int size) throws SQLException {
        String query = "WITH r AS (\n" +
                "    SELECT rooms.id AS room_id,\n" +
                "           rooms.name AS room_name,\n" +
                "           rooms.owner,\n" +
                "           messages.author\n" +
                "    FROM chat.chatroom rooms\n" +
                "    JOIN chat.message messages ON rooms.id = messages.chatroom\n" +
                ")\n" +
                "SELECT u.id,\n" +
                "       u.login,\n" +
                "       u.password,\n" +
                "       CASE\n" +
                "           WHEN r.owner = u.id THEN 'owner'\n" +
                "           WHEN r.author = u.id THEN 'author'\n" +
                "           ELSE 'other'\n" +
                "       END AS status,\n" +
                "       r.room_id, r.room_name\n" +
                "FROM chat.users u\n" +
                "JOIN r ON u.id = r.owner OR u.id = r.author\n" +
                "LIMIT ? OFFSET ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, size);
        preparedStatement.setInt(2, (page - 1) * size);
        return preparedStatement;
    }
}
