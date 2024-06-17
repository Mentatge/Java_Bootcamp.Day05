package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Chatroom> createRooms;
    private List<Chatroom> socializesRooms;

    public User(int id, String login, String password, List<Chatroom> createRooms, List<Chatroom> socializesRooms) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);
        this.setCreateRooms(createRooms);
        this.setSocializesRooms(socializesRooms);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getCreateRooms(), user.getCreateRooms()) && Objects.equals(getSocializesRooms(), user.getSocializesRooms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getCreateRooms(), getSocializesRooms());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", createRooms=" + getCreateRooms() +
                ", socializesRooms=" + getSocializesRooms() +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreateRooms() {
        return createRooms;
    }

    public void setCreateRooms(List<Chatroom> createRooms) {
        this.createRooms = createRooms;
    }

    public List<Chatroom> getSocializesRooms() {
        return socializesRooms;
    }

    public void setSocializesRooms(List<Chatroom> socializesRooms) {
        this.socializesRooms = socializesRooms;
    }
}
