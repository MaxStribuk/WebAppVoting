package dao.database;

import dao.api.IEmailSendingDAO;
import dao.factories.ConnectionSingleton;
import dto.EmailDTO;
import dto.EmailStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailSendingDBDAO implements IEmailSendingDAO {

    private static final String ADD = "INSERT INTO app.emails(" +
            "vote_id, recipient, topic, text_message, departures, status)" +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String GET = "SELECT vote_id, recipient, topic, " +
            "text_message, departures, status FROM app.emails WHERE vote_id = ?;";
    private static final String GET_UNSENT = "SELECT vote_id, recipient, topic, " +
            "text_message, departures, status FROM app.emails " +
            "WHERE departures<? AND status=? " +
            "ORDER BY vote_id LIMIT ?;";
    private static final String UPDATE = "UPDATE app.emails " +
            "SET vote_id=?, recipient=?, topic=?, text_message=?, departures=?, status=? " +
            "WHERE vote_id=?;";
    private static final int MAX_EMAIL_SENDS_NUMBER = 5;
    private static final int NUMBER_EMAILS_TO_SEND = 10;

    @Override
    public void add(EmailDTO email) {
        try (Connection conn = ConnectionSingleton.getInstance().open();
             PreparedStatement stmt = conn.prepareStatement(ADD)) {
            stmt.setInt(1, email.getVoteID());
            stmt.setString(2, email.getRecipient());
            stmt.setString(3, email.getTopic());
            stmt.setString(4, email.getTextMessage());
            stmt.setInt(5, email.getDepartures());
            stmt.setString(6, email.getStatus().toString());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("database query error");
        }
    }

    @Override
    public List<EmailDTO> getUnsent() {
        try (Connection conn = ConnectionSingleton.getInstance().open();
             PreparedStatement stmt = conn.prepareStatement(GET_UNSENT,
                     ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            stmt.setInt(1, MAX_EMAIL_SENDS_NUMBER);
            stmt.setString(2, EmailStatus.WAITING.toString());
            stmt.setInt(3, NUMBER_EMAILS_TO_SEND);
            List<EmailDTO> emails = new ArrayList<>();
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    emails.add(get(resultSet));
                }
            }
            return emails;
        } catch (SQLException e) {
            throw new RuntimeException("database query error");
        }
    }

    @Override
    public EmailDTO get(int voteID) {
        try (Connection conn = ConnectionSingleton.getInstance().open();
             PreparedStatement stmt = conn.prepareStatement(GET,
                     ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            stmt.setInt(1, voteID);
            try (ResultSet email = stmt.executeQuery()) {
                if (email.first()) {
                    return get(email);
                } else {
                    throw new IllegalArgumentException("email with the" +
                            " specified id does not exist");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("database query error");
        }
    }

    @Override
    public void update(EmailDTO email) {
        try (Connection conn = ConnectionSingleton.getInstance().open();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {
            stmt.setInt(1, email.getVoteID());
            stmt.setString(2, email.getRecipient());
            stmt.setString(3, email.getTopic());
            stmt.setString(4, email.getTextMessage());
            stmt.setInt(5, email.getDepartures());
            stmt.setString(6, email.getStatus().toString());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("database query error");
        }
    }

    private EmailDTO get(ResultSet email) throws SQLException {
        return new EmailDTO(
                email.getInt("vote_id"),
                email.getString("recipient"),
                email.getString("topic"),
                email.getString("text_message"),
                email.getInt("departures"),
                email.getString("status")
        );
    }
}