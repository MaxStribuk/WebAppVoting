package dto.request;

import java.util.Objects;

public class ArtistDTORequest {

    private String name;

    public ArtistDTORequest(String name) {
        this.name = name;
    }

    public ArtistDTORequest() {
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistDTORequest that = (ArtistDTORequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ArtistDTORequest{" +
                "name='" + name + '\'' +
                '}';
    }
}