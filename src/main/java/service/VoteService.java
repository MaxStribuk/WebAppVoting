package service;

import dao.api.IVoteDAO;
import dto.SavedVoteDTO;
import dto.VoteDTO;
import service.api.IVoteService;
import service.factories.GenreServiceSingleton;
import service.factories.MusicianServiceSingleton;

import java.util.List;
import java.util.NoSuchElementException;

public class VoteService implements IVoteService {

    private final IVoteDAO dataSource;

    public VoteService(IVoteDAO dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<SavedVoteDTO> getAll() {
        return dataSource.getAll();
    }

    @Override
    public void save(SavedVoteDTO vote) {
        dataSource.save(vote);
    }

    @Override
    public void validate(VoteDTO vote) {
        int musicianId = vote.getMusicianId();
        validateMusicianId(musicianId);

        List<Integer> genresIdList = vote.getGenreIdList();
        validateGenresId(genresIdList);

        String message = vote.getMessage();
        validateMessage(message);
    }

    private void validateMusicianId(int musicianId) {
        if (!MusicianServiceSingleton.getInstance()
                .exists(musicianId)) {
            throw new NoSuchElementException("Invalid musician id " +
                    "provided - '" + musicianId + "'");
        }
    }

    private void validateGenresId(List<Integer> genresIdList) {
        if (genresIdList == null) {
            throw new IllegalArgumentException("User failed to provide " +
                    "a list of genres");
        }
        if (genresIdList.size() < 3 || genresIdList.size() > 5) {
            throw new IllegalArgumentException("Number of genres outside" +
                    " allowed range (3-5)");
        }
        if (genresIdList.size() > genresIdList.stream().distinct().count()) {
            throw new IllegalArgumentException("Genre parameter " +
                    "must be non-repeating");
        }
        for (int genreId : genresIdList) {
            if (!GenreServiceSingleton.getInstance().exists(genreId)) {
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

