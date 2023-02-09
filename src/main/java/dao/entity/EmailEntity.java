package dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "emails", schema = "app")
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emails_seq")
    @SequenceGenerator(name = "emails_seq", sequenceName = "emails_id_seq",
            allocationSize = 1, schema = "app")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private VoteEntity vote;

    @Column(name = "recipient", nullable = false)
    private String recipient;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "text_message", nullable = false)
    private String textMessage;

    @Column(name = "departures", nullable = false)
    private int departures;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmailStatus status;

    public EmailEntity() {
    }

    public EmailEntity(VoteEntity vote, String recipient, String topic,
                       String textMessage, int departures, EmailStatus status) {
        this.vote = vote;
        this.recipient = recipient;
        this.topic = topic;
        this.textMessage = textMessage;
        this.departures = departures;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public VoteEntity getVote() {
        return vote;
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

    public void setVote(VoteEntity vote) {
        this.vote = vote;
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