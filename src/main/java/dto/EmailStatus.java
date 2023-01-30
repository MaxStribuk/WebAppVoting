package dto;

public enum EmailStatus {

    WAITING("email waiting to be sent"),
    SENT("email is sent"),
    ERROR("email sending error"),
    SUCCESS("email sent successfully");

    private final String title;

    EmailStatus(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}