package dto;

import java.util.Collections;
import java.util.List;

public class VoteDTO {

    private final int musicianId;
    private final List<Integer> genreIdList;
    private final String message;

    public VoteDTO(int musicianId, List<Integer> genreIdList, String message) {
        this.musicianId = musicianId;
        this.genreIdList = genreIdList;
        this.message = message;
    }

    public int getMusicianId() {
        return musicianId;
    }

    public List<Integer> getGenreIdList() {
        return Collections.unmodifiableList(genreIdList);
    }

    public String getMessage() {
        return message;
    }

}
