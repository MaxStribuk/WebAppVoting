package dao.database;

import dao.api.IEmailSendingDAO;
import dao.factories.ConnectionSingleton;
import dto.EmailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailSendingDBDAO implements IEmailSendingDAO {

    private static final String GET = "SELECT vote_id, recipient, topic, " +
            "text_message, sending, departures FROM app.emails WHERE id = ?;";
    private static final String GET_ALL_UNSENT = "SELECT vote_id, recipient, topic, " +
            "text_message, sending, departures FROM app.emails " +
            "WHERE sending = false AND departures = ?;";
    private static final String ADD = "INSERT INTO app.emails " +
            "(vote_id, recipient, topic, text_message, sending) " +
            "VALUES (?,?,?,?,?);";
    private static final String UPDATE_DEPARTURES = "UPDATE app.emails SET departures=? WHERE id=?;";
    private static final String UPDATE_SENDING = "UPDATE app.emails SET sending=true WHERE id=?;";
    private static final int MAX_SENDS_NUMBER = 5;

    @Override
    public void add(EmailDTO email) {
        try (Connection conn = ConnectionSingleton.getInstance().open();
             PreparedStatement stmt = conn.prepareStatement(ADD)) {
            stmt.setInt(1, email.getVoteID());
            stmt.setString(2, email.getRecipient());
            stmt.setString(3, email.getTopic());
            stmt.setString(4, email.getTextMessage());
            stmt.setBoolean(5, email.isSending());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("database query error");
        }
    }

    @Override
    public void updateDepartures(int voteId) {
        try (Connection conn = ConnectionSingleton.getInstance().open();
             PreparedStatement stmt = conn.prepareStatement(GET,
                     ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            stmt.setInt(1, voteId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                resultSet.first();
                EmailDTO email = get(resultSet);
                updateDepartures(email);
            }
        } catch (SQLException e) {
            throw new RuntimeException("database query error");
        }
    }

    private void updateDepartures(EmailDTO email) throws SQLException {
        try (Connection conn = ConnectionSingleton.getInstance().open();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_DEPARTURES)) {
            stmt.setInt(1, email.getDepartures() + 1);
            stmt.setInt(2, email.getVoteID());
            stmt.execute();
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

    private EmailDTO get(ResultSet email) throws SQLException {
        return new EmailDTO(
                email.getInt("vote_id"),
                email.getString("recipient"),
                email.getString("topic"),
                email.getString("text_message"),
                email.getBoolean("sending"),
                email.getInt("departures")
        );
    }

    @Override
    public void updateSending(int voteId) {
        try (Connection conn = ConnectionSingleton.getInstance().open();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SENDING)) {
            stmt.setInt(1, voteId);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("database query error");
        }
    }

    @Override
    public List<EmailDTO> receiveUnsent() {
        try (Connection conn = ConnectionSingleton.getInstance().open();
             PreparedStatement stmt = conn.prepareStatement(GET_ALL_UNSENT,
                     ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            stmt.setInt(1, MAX_SENDS_NUMBER);
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
}