package dto.response;

import java.util.Objects;

public class GenreDTOResponse {

    private final long id;
    private final String title;

    public GenreDTOResponse(long id, String title) {
        this.id = id;
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
        GenreDTOResponse genreDTOResponse = (GenreDTOResponse) o;
        return id == genreDTOResponse.id && title.equals(genreDTOResponse.title);
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