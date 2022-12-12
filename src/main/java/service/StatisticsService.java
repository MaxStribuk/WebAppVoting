package service;

import dto.ArtistDTO;
import dto.GenreDTO;
import dto.SavedVoteDTO;
import dto.StatisticsDTO;
import dto.VoteDTO;
import service.api.IGenreService;
import service.api.IArtistService;
import service.api.IStatisticsService;
import service.api.IVoteService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsService implements IStatisticsService {

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm:sss, dd.MM.yyyy");
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
    public Map<ArtistDTO, Integer> getBestArtists() {
        final Map<Integer, Integer> artistVotes = artistService.getAll()
                .stream()
                .collect(Collectors.toMap(ArtistDTO::getId, artist -> 0));
        voteService.getAll()
                .stream()
                .map(SavedVoteDTO::getVoteDTO)
                .map(VoteDTO::getArtistId)
                .forEach(artistId -> artistVotes.put(
                        artistId,
                        artistVotes.get(artistId) + 1));
        return artistVotes.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        entry -> artistService.getArtistById(entry.getKey()),
                        Map.Entry::getValue,
                        Integer::sum,
                        LinkedHashMap::new));
    }

    @Override
    public Map<GenreDTO, Integer> getBestGenres() {
        final Map<Integer, Integer> genreVotes = genreService.getAll()
                .stream()
                .collect(Collectors.toMap(GenreDTO::getId, genre -> 0));
        voteService.getAll()
                .stream()
                .map(SavedVoteDTO::getVoteDTO)
                .map(VoteDTO::getGenreIds)
                .flatMap(Collection::stream)
                .forEach(genreId -> genreVotes.put(
                        genreId,
                        genreVotes.get(genreId) + 1));
        return genreVotes.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        entry -> genreService.getGenreById(entry.getKey()),
                        Map.Entry::getValue,
                        Integer::sum,
                        LinkedHashMap::new));
    }

    @Override
    public Map<LocalDateTime, String> getAbouts() {
        List<SavedVoteDTO> votes = voteService.getAll();
        return votes.stream()
                .peek(vote -> vote.getCreateDataTime().format(formatter))
                .collect(Collectors.toMap(
                        SavedVoteDTO::getCreateDataTime,
                        vote -> vote.getVoteDTO().getAbout()
                ));
    }

    @Override
    public StatisticsDTO getStatistics() {
        return new StatisticsDTO(getBestArtists(), getBestGenres(), getAbouts());
    }
}