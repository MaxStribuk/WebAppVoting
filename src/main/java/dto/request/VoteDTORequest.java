package dto.request;

import java.util.List;
import java.util.Objects;

public class VoteDTORequest {

    private Long artistId;
    private List<Long> genreIds;
    private String about;
    private String email;

    public VoteDTORequest() {
    }

    public VoteDTORequest(Long artistId, List<Long> genreIds,
                          String about, String email) {
        this.artistId = artistId;
        this.genreIds = genreIds;
        this.about = about;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteDTORequest that = (VoteDTORequest) o;
        return artistId.equals(that.artistId) && genreIds.equals(that.genreIds) && about.equals(that.about) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistId, genreIds, about, email);
    }

    @Override
    public String toString() {
        return "VoteDTORequest{" +
                "artistId=" + artistId +
                ", genreIds=" + genreIds +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}