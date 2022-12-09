package dto;

public class MusicianDTO {
    private int id;
    private String musician;

    public MusicianDTO(int id, String musician) {
        this.id = id;
        this.musician = musician;
    }

    public int getId() {
        return id;
    }

    public String getMusician() {
        return musician;
    }
}
