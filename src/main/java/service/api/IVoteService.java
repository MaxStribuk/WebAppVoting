package service.api;

import dto.SavedVoteDTO;

import java.util.List;

public interface IVoteService {

    List<SavedVoteDTO> getAllVotes();
    void save(SavedVoteDTO vote);
    void validate(SavedVoteDTO vote);
}
