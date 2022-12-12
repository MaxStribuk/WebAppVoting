package dto;

import java.util.Collections;
import java.util.List;

public class VoteDTO {

    private final int artistId;
    private final List<Integer> genreIds;
    private final String about;

    public VoteDTO(int artistId, List<Integer> genreIds, String about) {
        this.artistId = artistId;
        this.genreIds = genreIds;
        this.about = about;
    }

    public int getArtistId() {
        return artistId;
    }

    public List<Integer> getGenreIds() {
        return Collections.unmodifiableList(genreIds);
    }

    public String getAbout() {
        return about;
    }

}