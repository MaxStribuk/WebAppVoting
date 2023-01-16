package dao.memory;

import dao.api.IVoteDAO;
import dto.SavedVoteDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VoteMemoryDAO implements IVoteDAO {
    private final List<SavedVoteDTO> votes;

    public VoteMemoryDAO() {
        votes = new ArrayList<>();
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