package dto.response;

import java.io.Serializable;
import java.util.Objects;

public class GenreDTOResponse implements Serializable {

    private final long id;
    private final String title;
    private final long version;

    public GenreDTOResponse(long id, String title, long version) {
        this.id = id;
        this.title = title;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTOResponse that = (GenreDTOResponse) o;
        return id == that.id && version == that.version && title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, version);
    }

    @Override
    public String toString() {
        return "GenreDTOResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", version=" + version +
                '}';
    }
}