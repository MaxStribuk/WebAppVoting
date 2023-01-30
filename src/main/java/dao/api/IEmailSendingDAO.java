package dao.api;

import dto.EmailDTO;

import java.util.List;

public interface IEmailSendingDAO {

    void add(EmailDTO email);

    EmailDTO get(int voteID);

    List<EmailDTO> getUnsent();

    void update(EmailDTO email);
}