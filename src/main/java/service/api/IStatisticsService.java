package service.api;

import dto.response.ArtistDTOResponse;
import dto.response.GenreDTOResponse;
import dto.response.StatisticDTOResponse;

import java.time.LocalDateTime;
import java.util.Map;

public interface IStatisticsService {

    Map<ArtistDTOResponse, Integer> getBestArtists();

    Map<GenreDTOResponse, Integer> getBestGenres();

    Map<LocalDateTime, String> getAbouts();

    StatisticDTOResponse getStatistics();
}