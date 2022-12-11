package dto;

import java.util.Collections;
import java.util.List;

public class VoteDTO {

    private final int musicianId;
    private final List<Integer> genreIds;
    private final String message;

    public VoteDTO(int musicianId, List<Integer> genreIds, String message) {
        this.musicianId = musicianId;
        this.genreIds = genreIds;
        this.message = message;
    }

    public int getMusicianId() {
        return musicianId;
    }

    public List<Integer> getGenreIds() {
        return Collections.unmodifiableList(genreIds);
    }

    public String getMessage() {
        return message;
    }

}
