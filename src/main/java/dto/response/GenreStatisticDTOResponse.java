package dto.response;

import java.io.Serializable;
import java.util.Objects;

public class GenreStatisticDTOResponse implements Serializable {

    private final long id;
    private final String title;
    private final int countVotes;

    public GenreStatisticDTOResponse(long id, String title, int countVotes) {
        this.id = id;
        this.title = title;
        this.countVotes = countVotes;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCountVotes() {
        return countVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreStatisticDTOResponse that = (GenreStatisticDTOResponse) o;
        return id == that.id && countVotes == that.countVotes && title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, countVotes);
    }

    @Override
    public String toString() {
        return "GenreStatisticDTOResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", countVotes=" + countVotes +
                '}';
    }
}