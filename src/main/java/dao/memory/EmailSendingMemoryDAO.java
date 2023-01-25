package dao.memory;

import dao.api.IEmailSendingDAO;
import dto.EmailDTO;

import java.util.List;

public class EmailSendingMemoryDAO implements IEmailSendingDAO {


    @Override
    public void add(EmailDTO email) {

    }

    @Override
    public void updateDepartures(int voteId) {

    }

    @Override
    public EmailDTO get(int voteID) {
        return null;
    }

    @Override
    public void updateSending(int voteId) {

    }

    @Override
    public List<EmailDTO> receiveUnsent() {
        return null;
    }
}