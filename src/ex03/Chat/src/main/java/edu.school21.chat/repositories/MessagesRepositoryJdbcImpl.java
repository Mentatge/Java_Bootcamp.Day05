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
            ResultSet userSet = connectionStatement.executeQuery("SELECT * FROM chat.users WHERE id = " + resultSet.getLong("author"));
            userSet.next();
            User user = new User(userSet.getInt("id"), userSet.getString("login"), userSet.getString("password"), null, null);
            Statement roomStatement = connection.createStatement();
            ResultSet roomSet = roomStatement.executeQuery("SELECT * FROM chat.chatroom WHERE id = " + resultSet.getLong("chatroom"));
            roomSet.next();
            Chatroom chatroom = new Chatroom(roomSet.getLong("id"), roomSet.getString("name"), null, null);
            String dateString = resultSet.getString("date");
            LocalDateTime dateTime = null;
            if (dateString != null) {
                dateTime = LocalDateTime.parse(dateString, dateTimeFormatter);
            }
            Optional<Message> message = Optional.of(new Message(resultSet.getLong("id"), user, chatroom, resultSet.getString("message"), dateTime));
            connection.close();
            return message;
        }
        connection.close();
        return Optional.empty();
    }

    @Override
    public void save(Message message) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO chat.message (author, chatroom, message) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, message.getAuthor().getId());
        statement.setLong(2, message.getRoom().getId());
        statement.setString(3, message.getMessage());
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 0) {
            throw new NotSavedSubEntityException("Not saved to database, try again");
        }
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
    @Override
    public void update(Message message) throws SQLException {
    Connection connection =  dataSource.getConnection();
    PreparedStatement statement = connection.prepareStatement("UPDATE chat.message SET author = ?, chatroom = ?, message = ?, date = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
    statement.setLong(1, message.getAuthor().getId());
    statement.setLong(2, message.getRoom().getId());
    statement.setString(3, message.getMessage());
    if (message.getDateTime() == null) {
        statement.setNull(4, Types.TIMESTAMP);
    }
    else {
       statement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
    }
    statement.setLong(5, message.getId());
    try {
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 0) {
            throw new NotSavedSubEntityException("Not saved to database, try again");
        }
    } catch (SQLException e) {
        throw new NotSavedSubEntityException("Not saved to database, try again");
    }
//    int rowsAffected = statement.executeUpdate();
//    if (rowsAffected == 0) {
//        throw new NotSavedSubEntityException("No update in database, try again");
//    }
    statement.close();
    connection.close();
    }
}
