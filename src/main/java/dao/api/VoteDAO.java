package dao.api;

import dto.VoteDTO;

import java.util.List;

public interface VoteDAO {

    List<VoteDTO> getAllVotes();
    void save(VoteDTO vote);
}
