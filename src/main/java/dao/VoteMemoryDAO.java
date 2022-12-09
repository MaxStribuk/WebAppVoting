package dao;

import dao.api.VoteDAO;
import dto.SavedVoteDTO;

import java.util.ArrayList;
import java.util.List;

public class VoteMemoryDAO implements VoteDAO {

    private static volatile VoteMemoryDAO instance = null;

    private final List<SavedVoteDTO> votes = new ArrayList<>();

    private VoteMemoryDAO() {
    }

    public static VoteMemoryDAO getInstance() {
        if (instance == null) {
            synchronized (VoteMemoryDAO.class) {
                if (instance == null) {
                    instance = new VoteMemoryDAO();
                }
            }
        }
        return instance;
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
