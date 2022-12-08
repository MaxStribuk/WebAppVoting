package dto;

public class VoteDTO {

    private final String musician;
    private final String[] genres;
    private final UserMessage message;

    public VoteDTO(String musician, String[] genres, UserMessage message) {
        this.musician = musician;
        this.genres = genres;
        this.message = message;
    }

    public String getMusician() {
        return musician;
    }

    public String[] getGenres() {
        return genres;
    }

    public UserMessage getMessage() {
        return message;
    }

}
