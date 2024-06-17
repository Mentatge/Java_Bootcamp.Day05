package edu.school21.chat.models;


import java.time.LocalDate;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private Chatroom room;
    private String message;
    private LocalDate dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id == message1.id && Objects.equals(author, message1.author) && Objects.equals(room, message1.room) && Objects.equals(message, message1.message) && Objects.equals(dateTime, message1.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, message, dateTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", room=" + room +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
