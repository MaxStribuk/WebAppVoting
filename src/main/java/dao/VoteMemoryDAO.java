package dao;

import dao.api.IVoteDAO;
import dto.SavedVoteDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VoteMemoryDAO implements IVoteDAO {
    private final List<SavedVoteDTO> votes = new ArrayList<>();

    public VoteMemoryDAO() {
    }
    @Override
    public List<SavedVoteDTO> getAll() {
        return Collections.unmodifiableList(votes);
    }

    @Override
    public void save(SavedVoteDTO vote) {
        votes.add(vote);
    }
}
