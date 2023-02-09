package dto;

import java.util.Objects;

public class GenreDTO {

    private long id;
    private String title;

    public GenreDTO(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public GenreDTO(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return id == genreDTO.id && title.equals(genreDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "GenreDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}