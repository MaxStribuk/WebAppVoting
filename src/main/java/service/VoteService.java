package service;

import dao.api.IVoteDAO;
import dto.VoteDTO;
import service.api.IVoteService;
import service.factories.GenreServiceMemorySingleton;
import service.factories.MusicianServiceMemorySingleton;

import java.util.List;
import java.util.NoSuchElementException;

public class VoteService implements IVoteService {

    private final IVoteDAO dataSource;

    public VoteService(IVoteDAO dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<VoteDTO> getAllVotes() {
        return dataSource.getAllVotes();
    }

    @Override
    public void save(VoteDTO vote) {
        dataSource.save(vote);
    }

    @Override
    public void validate(VoteDTO vote) {
        String musician = vote.getMusician();
        validateMusician(musician);

        String[] genres = vote.getGenres();
        validateGenres(genres);

        UserMessage message = vote.getMessage();
        String username = message.getUsername();
        validateUsername(username);

        String contents = message.getMessage();
        validateMessage(contents);
    }

    private void validateMusician(String musician) {
        if (musician == null || musician.isBlank()) {
            throw new IllegalArgumentException("User failed to provide " +
                    "a musician name");
        }
        if(!MusicianServiceMemorySingleton.getInstance()
                .exists(musician)) {
            throw new NoSuchElementException("Invalid musician name " +
                    "provided - '" + musician + "'");
        }
    }

    private void validateGenres(String[] genres) {
        if (genres == null) {
            throw new IllegalArgumentException("User failed to provide " +
                    "a list of genres");
        }
        if (genres.length < 3 || genres.length > 5) {
            throw new IllegalArgumentException("Number of genres outside" +
                    " allowed range (3-5)");
        }
        for (String genre : genres) {
            if (genre == null || genre.isBlank()) {
                throw new IllegalArgumentException("Genre parameter " +
                        "must be non-empty");
            }
            if(!GenreServiceMemorySingleton.getInstance()
                    .exists(genre)) {
                throw new NoSuchElementException("Invalid genre name " +
                        "provided - '" + genre + "'");
            }
        }
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("User failed to provide " +
                    "a username");
        }
    }

    private void validateMessage(String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("User failed to provide" +
                    " a message");
        }
    }
}
