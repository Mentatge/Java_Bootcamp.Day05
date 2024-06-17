package edu.school21.chat.models;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private Chatroom room;
    private String message;
    private LocalDateTime dateTime;

    public Message(long id, User author, Chatroom room, String message, LocalDateTime dateTime) {
        this.setId(id);
        this.setAuthor(author);
        this.setRoom(room);
        this.setMessage(message);
        this.setDateTime(dateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return getId() == message1.getId() && Objects.equals(getAuthor(), message1.getAuthor()) && Objects.equals(getRoom(), message1.getRoom()) && Objects.equals(getMessage(), message1.getMessage()) && Objects.equals(getDateTime(), message1.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getRoom(), getMessage(), getDateTime());
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + getId() +
                ", author=" + getAuthor() +
                ", room=" + getRoom() +
                ", message='" + getMessage() + '\'' +
                ", dateTime=" + getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")) +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
