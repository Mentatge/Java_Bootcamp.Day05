package edu.school21.chat.repositories;

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
            return message;
        }

        return Optional.empty();
    }
}
