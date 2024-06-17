package edu.school21.chat.app;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Program {

    private static HikariDataSource dataSource;

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres?currentSchema=chat\"");
        config.setUsername("mentatge");
        config.setPassword("1234"); // проверить на пасспорт да
        dataSource = new HikariDataSource(config);
        MessagesRepositoryJdbcImpl messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        User user = new User(-1, "user3", "password3", null, null);
        Chatroom chatroom = new Chatroom(3L, "third", user, null);
        Message message = new Message(1, user, chatroom, "Hello", LocalDateTime.now());
        try {
            messagesRepository.save(message);
            System.out.println(message);
        } catch (NotSavedSubEntityException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
