package dao;

import dao.api.IVoteDAO;
import dto.SavedVoteDTO;

import java.util.ArrayList;
import java.util.List;

public class VoteDAO implements IVoteDAO {

    private static volatile VoteDAO instance = null;

    private final List<SavedVoteDTO> votes = new ArrayList<>();

    public VoteDAO() {
    }

    public static VoteDAO getInstance() {
        if (instance == null) {
            synchronized (VoteDAO.class) {
                if (instance == null) {
                    instance = new VoteDAO();
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
