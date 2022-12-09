package dao;

import dao.api.IVoteDAO;
import dto.SavedVoteDTO;

import java.util.ArrayList;
import java.util.List;

public class VoteDAO implements IVoteDAO {
    private final List<SavedVoteDTO> votes = new ArrayList<>();

    public VoteDAO() {
    }
    @Override
    public List<SavedVoteDTO> getAllVotes() {
        return votes;
    }

    @Override
    public void save(SavedVoteDTO vote) {
        votes.add(vote);
    }
}
