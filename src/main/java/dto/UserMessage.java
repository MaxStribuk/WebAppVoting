package dto;

import java.time.LocalDateTime;

public class UserMessage {

    private final String username;
    private final String message;
    private final LocalDateTime datePosted;

    public UserMessage(String username, String message) {
        this.username = username;
        this.message = message;
        this.datePosted = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
    public LocalDateTime getDatePosted() {
        return datePosted;
    }
}
