package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long id;
    private String name;
    private User owner;
    private List<Message> messages;

    public Chatroom(Long id, String name, User owner, List<Message> messages) {
        this.setId(id);
        this.setName(name);
        this.setOwner(owner);
        this.setMessages(messages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return getId() == chatroom.getId() && Objects.equals(getName(), chatroom.getName()) && Objects.equals(getOwner(), chatroom.getOwner()) && Objects.equals(getMessages(), chatroom.getMessages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getOwner(), getMessages());
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", owner=" + getOwner() +
                ", messages=" + getMessages() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
