package service.api;

import dto.response.AboutStatisticDTOResponse;
import dto.response.ArtistStatisticDTOResponse;
import dto.response.GenreStatisticDTOResponse;
import dto.response.StatisticDTOResponse;

import java.util.List;

public interface IStatisticsService {

    List<ArtistStatisticDTOResponse> getBestArtists();

    List<GenreStatisticDTOResponse> getBestGenres();

    List<AboutStatisticDTOResponse> getAbouts();

    StatisticDTOResponse getStatistics();
}