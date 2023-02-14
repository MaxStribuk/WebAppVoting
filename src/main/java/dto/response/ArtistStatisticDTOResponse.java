package dto.response;

import java.io.Serializable;
import java.util.Objects;

public class ArtistStatisticDTOResponse implements Serializable {

    private final long id;
    private final String name;
    private final int countVotes;

    public ArtistStatisticDTOResponse(long id, String name, int countVotes) {
        this.id = id;
        this.name = name;
        this.countVotes = countVotes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountVotes() {
        return countVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistStatisticDTOResponse that = (ArtistStatisticDTOResponse) o;
        return id == that.id && countVotes == that.countVotes && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countVotes);
    }

    @Override
    public String toString() {
        return "ArtistStatisticDTOResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countVotes=" + countVotes +
                '}';
    }
}