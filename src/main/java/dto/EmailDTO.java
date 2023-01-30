package dto;

public class EmailDTO {

    private int voteID;
    private String recipient;
    private String topic;
    private String textMessage;
    private int departures;
    private EmailStatus status;

    public EmailDTO(int voteID, String recipient, String topic,
                    String textMessage) {
        this.voteID = voteID;
        this.recipient = recipient;
        this.topic = topic;
        this.textMessage = textMessage;
        this.status = EmailStatus.WAITING;
    }

    public EmailDTO(String recipient, String topic, String textMessage) {
        this.recipient = recipient;
        this.topic = topic;
        this.textMessage = textMessage;
    }

    public EmailDTO(int voteID, String recipient, String topic,
                    String textMessage, int departures, String status) {
        this.voteID = voteID;
        this.recipient = recipient;
        this.topic = topic;
        this.textMessage = textMessage;
        this.departures = departures;
        this.status = EmailStatus.valueOf(status);
    }

    public int getVoteID() {
        return voteID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getTopic() {
        return topic;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public int getDepartures() {
        return departures;
    }

    public EmailStatus getStatus() {
        return status;
    }

    public void setVoteID(int voteID) {
        this.voteID = voteID;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public void setDepartures(int departures) {
        this.departures = departures;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }
}