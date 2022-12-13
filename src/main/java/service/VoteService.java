package service;

import dao.api.IGenreDAO;
import dao.api.IArtistDAO;
import dao.api.IVoteDAO;
import dto.SavedVoteDTO;
import dto.VoteDTO;
import service.api.IVoteService;

import java.util.List;
import java.util.NoSuchElementException;

public class VoteService implements IVoteService {

    private final IVoteDAO voteDAO;
    private final IGenreDAO genreDAO;
    private final IArtistDAO artistDAO;

    public VoteService(IVoteDAO voteDAO, IGenreDAO genreDAO, IArtistDAO artistDAO) {
        this.voteDAO = voteDAO;
        this.genreDAO = genreDAO;
        this.artistDAO = artistDAO;
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
        int artistId = vote.getArtistId();
        validateArtist(artistId);

        List<Integer> genresIdList = vote.getGenreIds();
        validateGenres(genresIdList);

        String about = vote.getAbout();
        validateAbout(about);
    }

    private void validateArtist(int artistId) {
        if (!artistDAO.exists(artistId)) {
            throw new NoSuchElementException("Invalid artist id " +
                    "provided - '" + artistId + "' ");
        }
    }

    private void validateGenres(List<Integer> genresIdList) {

        if (genresIdList.size() < 3 || genresIdList.size() > 5) {
            throw new IllegalArgumentException("Number of genres outside" +
                    " allowed range (3-5) ");
        }
        if (genresIdList.size() > genresIdList.stream()
                                              .distinct()
                                              .count()) {
            throw new IllegalArgumentException("Genre parameter " +
                    "must be non-repeating ");
        }
        for (int genreId : genresIdList) {
            if (!genreDAO.exists(genreId)) {
                throw new NoSuchElementException("Invalid genre id " +
                        "provided - '" + genreId + "' ");
            }
        }
    }

    private void validateAbout(String about) {
        if (about == null || about.isBlank()) {
            throw new IllegalArgumentException("User failed to provide" +
                    " a message ");
        }
    }
}