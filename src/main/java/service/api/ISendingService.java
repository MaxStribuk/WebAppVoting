package service.api;

import dao.entity.EmailEntity;
import dao.entity.VoteEntity;

import javax.mail.MessagingException;

public interface ISendingService {

    void initializeSendingService();

    void stopSendingService();

    void confirmVote(VoteEntity vote);

    void send(EmailEntity email) throws MessagingException;
}