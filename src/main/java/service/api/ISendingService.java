package service.api;

import dao.entity.EmailEntity;
import dao.entity.VoteEntity;
import dto.EmailDTO;

import javax.mail.MessagingException;

public interface ISendingService {

    void initializeSendingService();

    void stopSendingService();

    void confirmVote(VoteEntity vote);

    void verifyEmail(EmailDTO email);

    void send(EmailEntity email) throws MessagingException;
}