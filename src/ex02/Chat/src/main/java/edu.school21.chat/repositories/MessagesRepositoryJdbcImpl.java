package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM chat.message WHERE id = " + id);
        if (resultSet.next()) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
            Statement connectionStatement = connection.createStatement();
            ResultSet userSet = connectionStatement.executeQuery("SELECT * FROM chat.users WHERE id = " + resultSet.getLong(2));
            userSet.next();
            User user = new User(userSet.getInt(1), userSet.getString(2), userSet.getString(3), null, null);
            Statement roomStatement = connection.createStatement();
            ResultSet roomSet = roomStatement.executeQuery("SELECT * FROM chat.chatroom WHERE id = " + resultSet.getLong(3));
            roomSet.next();
            Chatroom chatroom = new Chatroom(roomSet.getLong(1), roomSet.getString(2), null, null);
            Optional<Message> message = Optional.of(new Message(resultSet.getLong("id"), user, chatroom, resultSet.getString("message"), LocalDateTime.parse(resultSet.getString("date"), dateTimeFormatter)));
            connection.close();
            return message;
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) throws SQLException {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO chat.message (author, chatroom, message) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getRoom().getId());
            statement.setString(3, message.getMessage());
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new NotSavedSubEntityException(e.getMessage());
            }
//            if (rowsAffected == 0) {
//                throw new NotSavedSubEntityException("Not saved to database, try again");
//            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    message.setId(id);
                } else {
                    throw new NotSavedSubEntityException("Insert failed");
                }
            } finally {
                statement.close();
                connection.close();
            }
    }
}
