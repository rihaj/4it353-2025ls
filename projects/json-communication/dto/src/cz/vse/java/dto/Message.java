package cz.vse.java.dto;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime dateTime;
    private String userId;
    private String text;

    public Message(String text) {
        this();
        this.text = text;
    }

    public Message() {
        this.dateTime = LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Message [");

        sb.append("dateTime='").append(dateTime).append("', ");
        sb.append("userId='").append(userId).append("', ");
        sb.append("text='").append(text).append("']");

        return sb.toString();
    }
}
