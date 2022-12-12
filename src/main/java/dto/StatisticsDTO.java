package dto;

import java.time.LocalDateTime;
import java.util.Map;

public class StatisticsDTO {

    private final Map<ArtistDTO, Integer> bestArtists;
    private final Map<GenreDTO, Integer> bestGenres;
    private final Map<LocalDateTime, String> abouts;

    public StatisticsDTO(Map<ArtistDTO, Integer> bestArtists,
                         Map<GenreDTO, Integer> bestGenres,
                         Map<LocalDateTime, String> abouts) {
        this.bestArtists = bestArtists;
        this.bestGenres = bestGenres;
        this.abouts = abouts;
    }

    public Map<ArtistDTO, Integer> getBestArtists() {
        return bestArtists;
    }

    public Map<GenreDTO, Integer> getBestGenres() {
        return bestGenres;
    }

    public Map<LocalDateTime, String> getAbouts() {
        return abouts;
    }
}