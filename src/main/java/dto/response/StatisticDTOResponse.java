package dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class StatisticDTOResponse implements Serializable {

    private final List<ArtistStatisticDTOResponse> artists;
    private final List<GenreStatisticDTOResponse> genres;
    private final List<AboutStatisticDTOResponse> abouts;

    public StatisticDTOResponse(List<ArtistStatisticDTOResponse> artists,
                                List<GenreStatisticDTOResponse> genres,
                                List<AboutStatisticDTOResponse> abouts) {
        this.artists = artists;
        this.genres = genres;
        this.abouts = abouts;
    }

    public List<ArtistStatisticDTOResponse> getArtists() {
        return artists;
    }

    public List<GenreStatisticDTOResponse> getGenres() {
        return genres;
    }

    public List<AboutStatisticDTOResponse> getAbouts() {
        return abouts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticDTOResponse that = (StatisticDTOResponse) o;
        return artists.equals(that.artists) && genres.equals(that.genres) && abouts.equals(that.abouts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artists, genres, abouts);
    }

    @Override
    public String toString() {
        return "StatisticDTOResponse{" +
                "artists=" + artists +
                ", genres=" + genres +
                ", abouts=" + abouts +
                '}';
    }
}