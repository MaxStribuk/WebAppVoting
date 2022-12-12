package service;

import dto.VoteDTO;
import service.api.IGenreService;
import service.api.IArtistService;
import service.api.IStatisticsService;
import service.api.IVoteService;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsService implements IStatisticsService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.
            ofPattern("HH:mm, dd.MM.yyyy");
    private final IVoteService voteService;
    private final IGenreService genreService;
    private final IArtistService artistService;

    public StatisticsService(IVoteService voteService,
                             IGenreService genreService,
                             IArtistService artistService) {
        this.voteService = voteService;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    @Override
    public Map<String, Integer> getArtistVotes() {
        List<VoteDTO> votes = voteService.getAll();
        Map<String, Integer> artistVotes = artistService.getAllMusicians()
                .stream().collect(Collectors.toMap(x -> x, x -> 0));
        for (VoteDTO vote : votes) {
            String artist = vote.getMusician();
            artistVotes.put(artist, artistVotes.get(artist) + 1);
        }

        return getSortedMap(artistVotes);
    }

    @Override
    public Map<String, Integer> getGenreVotes() {
        List<VoteDTO> votes = voteService.getAll();
        Map<String, Integer> genreVotes = genreService.getAllGenres()
                .stream().collect(Collectors.toMap(x -> x, x -> 0));

        for (VoteDTO vote : votes) {
            String[] genres = vote.getGenres();
            Arrays.stream(genres).forEach(genre -> genreVotes.put(genre,
                    genreVotes.get(genre) + 1));
        }

        return getSortedMap(genreVotes);
    }

    @Override
    public List<UserMessage> getUserAbouts() {
        List<VoteDTO> votes = voteService.getAll();
        return votes.stream().map(VoteDTO::getAbout)
                .sorted(Comparator.comparing(UserMessage::getDatePosted))
                .collect(Collectors.toList());
    }
    @Override
    public String getStringValue() {

        StringBuilder data = new StringBuilder();
        Map<String, Integer> artistVotes = getArtistVotes();
        Map<String, Integer> genreVotes = getGenreVotes();
        List<UserMessage> userMessages = getUserAbouts();

        data.append("Current Musician Rankings:\n");
        appendDataFromMap(artistVotes, data);

        data.append("Current Genre Rankings:\n");
        appendDataFromMap(genreVotes, data);

        data.append("User messages:\n");
        userMessages.stream()
                .sorted(Comparator.comparing(UserMessage::getDatePosted))
                .forEachOrdered(entry -> data.append(entry.getUsername())
                        .append(": ")
                        .append(entry.getMessage())
                        .append(" (")
                        .append(entry.getDatePosted().format(formatter))
                        .append(")\n"));
        return data.toString();
    }

    private LinkedHashMap<String, Integer> getSortedMap(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e1,
                        LinkedHashMap::new ));
    }

    private void appendDataFromMap(Map<String, Integer> map,
                                  StringBuilder builder) {
        map.forEach((key, value) -> builder.append(key).append(" - ")
                .append(value)
                .append(" votes\n"));
    }
}