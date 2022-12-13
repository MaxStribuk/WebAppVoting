package service.api;

import dto.ArtistDTO;
import dto.GenreDTO;
import dto.StatisticsDTO;

import java.time.LocalDateTime;
import java.util.Map;

public interface IStatisticsService {

    Map<ArtistDTO, Integer> getBestArtists();

    Map<GenreDTO, Integer> getBestGenres();

    Map<LocalDateTime, String> getAbouts();

    StatisticsDTO getStatistics();
}