package edu.school21.chat.app;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.List;

public class Program {

    private static HikariDataSource dataSource;

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres?currentSchema=chat\"");
        config.setUsername("mentatge");
        config.setPassword("1234"); // проверить на пасспорт да
        dataSource = new HikariDataSource(config);
        DataSource dataSource = new HikariDataSource(config);

        UsersRepositoryJdbcImpl usersRepository = new UsersRepositoryJdbcImpl(dataSource);

        List<User> userList = null;
        userList = usersRepository.findAll(2, 3);
        System.out.println("Size of result list = " + userList.size());
        int i = 1;
        for (User user : userList) {
            System.out.println(i + ". user: ");
            System.out.println(user);
            i++;
        }
    }
}
