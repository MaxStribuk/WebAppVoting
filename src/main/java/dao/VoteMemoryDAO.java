package dao;

import dao.api.VoteDAO;
import dto.VoteDTO;

import java.util.ArrayList;
import java.util.List;

public class VoteMemoryDAO implements VoteDAO {

    private static volatile VoteMemoryDAO instance = null;

    private final List<VoteDTO> votes = new ArrayList<>();

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
    public List<VoteDTO> getAllVotes() {
        return votes;
    }

    @Override
    public void save(VoteDTO vote) {
        votes.add(vote);
    }
}
