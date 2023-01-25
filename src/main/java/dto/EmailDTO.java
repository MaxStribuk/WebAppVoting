package dto;

public class EmailDTO {

    private int voteID;
    private String recipient;
    private String topic;
    private String textMessage;
    private boolean sending;
    private int departures;

    public EmailDTO(int voteID, String recipient, String topic,
                    String textMessage) {
        this.voteID = voteID;
        this.recipient = recipient;
        this.topic = topic;
        this.textMessage = textMessage;
    }

    public EmailDTO(int voteID, String recipient, String topic,
                    String textMessage, boolean sending, int departures) {
        this.voteID = voteID;
        this.recipient = recipient;
        this.topic = topic;
        this.textMessage = textMessage;
        this.sending = sending;
        this.departures = departures;
    }

    public void setSending(boolean sending) {
        this.sending = sending;
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

    public boolean isSending() {
        return sending;
    }

    public int getDepartures() {
        return departures;
    }
}