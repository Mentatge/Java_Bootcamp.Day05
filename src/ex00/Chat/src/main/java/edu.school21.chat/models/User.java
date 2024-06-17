package edu.school21.chat.models;


import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private List<Chatroom> createRooms;
    private List<Chatroom> socializesRooms;

    public User(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(createRooms, user.createRooms) && Objects.equals(socializesRooms, user.socializesRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createRooms, socializesRooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createRooms=" + createRooms +
                ", socializesRooms=" + socializesRooms +
                '}';
    }
}
