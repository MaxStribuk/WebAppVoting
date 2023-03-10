package dto.request;

import java.util.Objects;

public class GenreDTORequest {

    private String title;

    public GenreDTORequest() {
    }

    public GenreDTORequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTORequest that = (GenreDTORequest) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "GenreDTORequest{" +
                "title='" + title + '\'' +
                '}';
    }
}