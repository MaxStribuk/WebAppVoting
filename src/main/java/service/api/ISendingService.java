package service.api;

import dao.entity.EmailEntity;
import dto.SavedVoteDTO;

import javax.mail.MessagingException;

public interface ISendingService {

    void initializeSendingService();

    void stopSendingService();

    void confirmVote(SavedVoteDTO vote);

    void verifyEmail(String email, String verificationLink);

    void send(EmailEntity email) throws MessagingException;
}