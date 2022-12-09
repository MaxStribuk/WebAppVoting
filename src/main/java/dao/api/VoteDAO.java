package dao.api;


import dto.SavedVoteDTO;

import java.util.List;

public interface VoteDAO {

    List<SavedVoteDTO> getAllVotes();
    void save(SavedVoteDTO vote);
}
