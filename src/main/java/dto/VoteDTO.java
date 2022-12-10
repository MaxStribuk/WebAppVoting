package dto;

import java.util.Collections;
import java.util.List;

public class VoteDTO {

    private final int musicianId;
    private final List<Integer> genreIDList;
    private final String message;

    public VoteDTO(int musicianId, List<Integer> genreIDList, String message) {
        this.musicianId = musicianId;
        this.genreIDList = genreIDList;
        this.message = message;
    }

    public int getMusicianId() {
        return musicianId;
    }

    public List<Integer> getGenreIDList() {
        return Collections.unmodifiableList(genreIDList);
    }

    public String getMessage() {
        return message;
    }

}
