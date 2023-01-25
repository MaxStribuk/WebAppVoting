package service.api;

import dto.SavedVoteDTO;

import javax.mail.MessagingException;
import java.util.concurrent.ScheduledExecutorService;

public interface ISendingService {

    void send(SavedVoteDTO vote, int voteID);

    void resend() throws MessagingException;

    ScheduledExecutorService getExecutorService();
}