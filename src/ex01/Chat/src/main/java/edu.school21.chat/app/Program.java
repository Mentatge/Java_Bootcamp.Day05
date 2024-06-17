package edu.school21.chat.app;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Scanner;

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

        System.out.println("Enter a message ID");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        try {
            Message message = messagesRepository.findById(id).get();
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        scanner.close();
    }
}
