package service.api;

import dto.VoteDTO;

import java.util.List;

public interface IVoteService {

    List<VoteDTO> getAllVotes();
    void save(VoteDTO vote);
    void validate(VoteDTO vote);
}
