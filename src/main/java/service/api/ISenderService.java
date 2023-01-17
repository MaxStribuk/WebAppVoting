package service.api;

import dto.SavedVoteDTO;

public interface ISenderService {
    void send(SavedVoteDTO vote);
}
