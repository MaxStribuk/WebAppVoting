package service.api;

import dto.SavedVoteDTO;
import dto.VoteDTO;

import java.util.List;

public interface IVoteService {

    List<SavedVoteDTO> getAll();

    void save(SavedVoteDTO vote);

    void validate(VoteDTO vote);
}