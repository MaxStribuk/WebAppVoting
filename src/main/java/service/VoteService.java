package service;

import dao.api.IGenreDAO;
import dao.api.IMusicianDAO;
import dao.api.IVoteDAO;
import dto.SavedVoteDTO;
import dto.VoteDTO;
import service.api.IVoteService;

import java.util.List;
import java.util.NoSuchElementException;

public class VoteService implements IVoteService {

    private final IVoteDAO voteDAO;
    private final IGenreDAO genreDAO;
    private final IMusicianDAO musicianDAO;

    public VoteService(IVoteDAO voteDAO, IGenreDAO genreDAO, IMusicianDAO musicianDAO) {
        this.voteDAO = voteDAO;
        this.genreDAO = genreDAO;
        this.musicianDAO = musicianDAO;
    }

    @Override
    public List<SavedVoteDTO> getAll() {
        return voteDAO.getAll();
    }

    @Override
    public void save(SavedVoteDTO vote) {
        voteDAO.save(vote);
    }

    @Override
    public void validate(VoteDTO vote) {
        int musicianId = vote.getMusicianId();
        validateMusician(musicianId);

        List<Integer> genresIdList = vote.getGenreIds();
        validateGenres(genresIdList);

        String message = vote.getMessage();
        validateMessage(message);
    }

    private void validateMusician(int musicianId) {
        if (!musicianDAO.exists(musicianId)) {
            throw new NoSuchElementException("Invalid musician id " +
                    "provided - '" + musicianId + "'");
        }
    }

    private void validateGenres(List<Integer> genresIdList) {

        if (genresIdList.size() < 3 || genresIdList.size() > 5) {
            throw new IllegalArgumentException("Number of genres outside" +
                    " allowed range (3-5)");
        }
        if (genresIdList.size() > genresIdList.stream().distinct().count()) {
            throw new IllegalArgumentException("Genre parameter " +
                    "must be non-repeating");
        }
        for (int genreId : genresIdList) {
            if (!genreDAO.exists(genreId)) {
                throw new NoSuchElementException("Invalid genre id " +
                        "provided - '" + genreId + "'");
            }
        }
    }

    private void validateMessage(String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("User failed to provide" +
                    " a message");
        }
    }
}

