package dto;

import java.util.Objects;

public class GenreDTO {
    private int id;
    private String genre;

    public GenreDTO(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return id == genreDTO.id && genre.equals(genreDTO.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre);
    }
}
