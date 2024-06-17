package edu.school21.chat.app;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Optional;

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
        Optional<Message> messageOptional = null;
        try {
            messageOptional = messagesRepository.findById(1L);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!messageOptional.isPresent()) {
            return;
        }
        Message message = messageOptional.get();
        message.setMessage("It's time to Javac");
        message.setDateTime(null);
        try {
            messagesRepository.update(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(messagesRepository.findById(1L).get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
