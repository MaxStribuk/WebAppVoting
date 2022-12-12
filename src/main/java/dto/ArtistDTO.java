package dto;

import java.util.Objects;

public class ArtistDTO {

    private final int id;
    private final String artist;

    public ArtistDTO(int id, String artist) {
        this.id = id;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistDTO that = (ArtistDTO) o;
        return id == that.id && artist.equals(that.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist);
    }
}