package dao.api;

import dto.EmailDTO;

import java.util.List;

public interface IEmailSendingDAO {

    void add(EmailDTO email);
    void updateDepartures(int voteId);

    EmailDTO get(int voteID);

    void updateSending(int voteId);
    List<EmailDTO> receiveUnsent();
}