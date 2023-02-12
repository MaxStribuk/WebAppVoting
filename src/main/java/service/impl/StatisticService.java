package service.impl;

import dto.response.ArtistDTOResponse;
import dto.response.GenreDTOResponse;
import dto.response.VoteDTOResponse;
import dto.response.StatisticDTOResponse;
import service.api.IGenreService;
import service.api.IArtistService;
import service.api.IStatisticsService;
import service.api.IVoteService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticService implements IStatisticsService {

    private final IVoteService voteService;
    private final IGenreService genreService;
    private final IArtistService artistService;

    public StatisticService(IVoteService voteService,
                            IGenreService genreService,
                            IArtistService artistService) {
        this.voteService = voteService;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    @Override
    public Map<ArtistDTOResponse, Integer> getBestArtists() {
        final Map<Long, Integer> artistVotes = artistService.getAll()
                .stream()
                .collect(Collectors.toMap(ArtistDTOResponse::getId, artist -> 0));
        voteService.getAll()
                .stream()
                .map(VoteDTOResponse::getArtistId)
                .forEach(artistId -> artistVotes.put(
                        artistId,
                        artistVotes.get(artistId) + 1));
        return sortArtistsByVotes(artistVotes);
    }

    private Map<ArtistDTOResponse, Integer> sortArtistsByVotes(Map<Long, Integer> artists) {
        return artists.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        entry -> artistService.get(entry.getKey()),
                        Map.Entry::getValue,
                        Integer::sum,
                        LinkedHashMap::new));
    }

    @Override
    public Map<GenreDTOResponse, Integer> getBestGenres() {
        final Map<Long, Integer> genreVotes = genreService.getAll()
                .stream()
                .collect(Collectors.toMap(GenreDTOResponse::getId, genre -> 0));
        voteService.getAll()
                .stream()
                .map(VoteDTOResponse::getGenreIds)
                .flatMap(Collection::stream)
                .forEach(genreId -> genreVotes.put(
                        genreId,
                        genreVotes.get(genreId) + 1));
        return sortGenresByVotes(genreVotes);
    }

    private Map<GenreDTOResponse, Integer> sortGenresByVotes(Map<Long, Integer> genres) {
        return genres.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        entry -> genreService.get(entry.getKey()),
                        Map.Entry::getValue,
                        Integer::sum,
                        LinkedHashMap::new));
    }

    @Override
    public Map<LocalDateTime, String> getAbouts() {
        return voteService.getAll()
                .stream()
                .sorted(Comparator.comparing(VoteDTOResponse::getCreationTime))
                .collect(Collectors.toMap(
                        VoteDTOResponse::getCreationTime,
                        VoteDTOResponse::getAbout,
                        (value1, value2) -> value1 + "\n\n"+ value2,
                        LinkedHashMap::new));
    }

    @Override
    public StatisticDTOResponse getStatistics() {
        return new StatisticDTOResponse(getBestArtists(), getBestGenres(), getAbouts());
    }
}