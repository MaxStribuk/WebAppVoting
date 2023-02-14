package service.impl;

import dto.response.AboutStatisticDTOResponse;
import dto.response.ArtistDTOResponse;
import dto.response.ArtistStatisticDTOResponse;
import dto.response.GenreDTOResponse;
import dto.response.GenreStatisticDTOResponse;
import dto.response.VoteDTOResponse;
import dto.response.StatisticDTOResponse;
import service.api.IGenreService;
import service.api.IArtistService;
import service.api.IStatisticsService;
import service.api.IVoteService;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
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
    public List<ArtistStatisticDTOResponse> getBestArtists() {
        final Map<Long, Integer> artistVotes = artistService.getAll()
                .stream()
                .collect(Collectors.toMap(ArtistDTOResponse::getId, artist -> 0));
        voteService.getAll()
                .stream()
                .map(VoteDTOResponse::getArtistId)
                .forEach(artistId -> artistVotes.put(
                        artistId,
                        artistVotes.get(artistId) + 1));
        return sortArtists(artistVotes);
    }

    @Override
    public List<GenreStatisticDTOResponse> getBestGenres() {
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
        return sortGenres(genreVotes);
    }

    @Override
    public List<AboutStatisticDTOResponse> getAbouts() {
        return voteService.getAll()
                .stream()
                .map(vote -> new AboutStatisticDTOResponse(
                        vote.getCreationTime(),
                        vote.getAbout()
                ))
                .sorted(Comparator.comparing(AboutStatisticDTOResponse::getCreationTime))
                .collect(Collectors.toList());
    }

    @Override
    public StatisticDTOResponse getStatistics() {
        return new StatisticDTOResponse(getBestArtists(), getBestGenres(), getAbouts());
    }

    private List<ArtistStatisticDTOResponse> sortArtists(Map<Long, Integer> artistVotes) {
        return artistVotes.entrySet()
                .stream()
                .map(entry -> new ArtistStatisticDTOResponse(
                        entry.getKey(),
                        artistService.get(entry.getKey()).getName(),
                        entry.getValue()
                ))
                .sorted(Comparator.comparingInt(ArtistStatisticDTOResponse::getCountVotes).reversed())
                .collect(Collectors.toList());
    }

    private List<GenreStatisticDTOResponse> sortGenres(Map<Long, Integer> genreVotes) {
        return genreVotes.entrySet()
                .stream()
                .map(entry -> new GenreStatisticDTOResponse(
                        entry.getKey(),
                        genreService.get(entry.getKey()).getTitle(),
                        entry.getValue()
                ))
                .sorted(Comparator.comparingInt(GenreStatisticDTOResponse::getCountVotes).reversed())
                .collect(Collectors.toList());
    }
}