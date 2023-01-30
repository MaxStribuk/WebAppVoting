package service.api;

import dto.SavedVoteDTO;

public interface ISendingService {

    void send(SavedVoteDTO vote, int voteID);

    void initializeSendingService();

    void stopSendingService();

    void sendVerificationLink(String email, String verificationLink);
}