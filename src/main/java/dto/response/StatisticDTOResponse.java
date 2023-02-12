package dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public class StatisticDTOResponse {

    private final Map<ArtistDTOResponse, Integer> bestArtists;
    private final Map<GenreDTOResponse, Integer> bestGenres;
    private final Map<LocalDateTime, String> abouts;

    public StatisticDTOResponse(Map<ArtistDTOResponse, Integer> bestArtists,
                                Map<GenreDTOResponse, Integer> bestGenres,
                                Map<LocalDateTime, String> abouts) {
        this.bestArtists = bestArtists;
        this.bestGenres = bestGenres;
        this.abouts = abouts;
    }

    public Map<ArtistDTOResponse, Integer> getBestArtists() {
        return bestArtists;
    }

    public Map<GenreDTOResponse, Integer> getBestGenres() {
        return bestGenres;
    }

    public Map<LocalDateTime, String> getAbouts() {
        return abouts;
    }
}