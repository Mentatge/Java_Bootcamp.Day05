package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Chatroom> createRooms;
    private List<Chatroom> socializesRooms;

    public User(int id, String login, String password, List<Chatroom> createRooms, List<Chatroom> socializesRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createRooms = createRooms != null ? createRooms : new ArrayList<>();
        this.socializesRooms = socializesRooms != null ? socializesRooms : new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(createRooms, user.createRooms) &&
                Objects.equals(socializesRooms, user.socializesRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createRooms, socializesRooms);
    }

    @Override
    public String toString() {
        StringBuilder stringCreatedRooms = new StringBuilder();
        if (createRooms == null) {
            stringCreatedRooms.append("createdRooms = null");
        } else {
            stringCreatedRooms.append("createdRooms: ");
            for (Chatroom rooms : createRooms) {
                stringCreatedRooms.append(rooms.toString() + "\n");
            }
        }
        StringBuilder stringRooms = new StringBuilder();
        if (socializesRooms == null) {
            stringRooms.append("rooms = null");
        } else {
            stringRooms.append("rooms: ");
            for (Chatroom rooms : socializesRooms) {
                stringRooms.append(rooms.toString() + "\n");
            }
        }

        return "{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + stringCreatedRooms +
                ", rooms=" + stringRooms+
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

    public void addCreatedRoom(Chatroom room) {
        if (createRooms == null) {
            createRooms = new ArrayList<>();
        }
        createRooms.add(room);
    }

    public void addRoom(Chatroom room) {
        if (socializesRooms == null) {
            socializesRooms = new ArrayList<>();
        }
        socializesRooms.add(room);
    }
}
