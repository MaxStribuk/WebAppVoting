package dto.response;

import java.io.Serializable;
import java.util.Objects;

public class ArtistDTOResponse implements Serializable {

    private final long id;
    private final String name;
    private final long version;

    public ArtistDTOResponse(long id, String name, long version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistDTOResponse that = (ArtistDTOResponse) o;
        return id == that.id && version == that.version && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, version);
    }

    @Override
    public String toString() {
        return "ArtistDTOResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}