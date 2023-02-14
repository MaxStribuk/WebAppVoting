package dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class VoteDTOResponse implements Serializable {

    private final Long artistId;
    private final List<Long> genreIds;
    private final String about;
    private final String email;
    private final LocalDateTime creationTime;

    public VoteDTOResponse(Long artistId, List<Long> genreIds, String about,
                           String email, LocalDateTime creationTime) {
        this.artistId = artistId;
        this.genreIds = genreIds;
        this.about = about;
        this.email = email;
        this.creationTime = creationTime;
    }

    public Long getArtistId() {
        return artistId;
    }

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public String getAbout() {
        return about;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteDTOResponse that = (VoteDTOResponse) o;
        return artistId.equals(that.artistId) && genreIds.equals(that.genreIds) && about.equals(that.about) && email.equals(that.email) && creationTime.equals(that.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistId, genreIds, about, email, creationTime);
    }

    @Override
    public String toString() {
        return "VoteDTOResponse{" +
                "artistId=" + artistId +
                ", genreIds=" + genreIds +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}