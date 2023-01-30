package dao.memory;

import dao.api.IEmailSendingDAO;
import dto.EmailDTO;

import java.util.List;

public class EmailSendingMemoryDAO implements IEmailSendingDAO {


    @Override
    public void add(EmailDTO email) {

    }

    @Override
    public EmailDTO get(int voteID) {
        return null;
    }

    @Override
    public List<EmailDTO> getUnsent() {
        return null;
    }

    @Override
    public void update(EmailDTO email) {

    }
}