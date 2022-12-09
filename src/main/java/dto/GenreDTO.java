package dto;

public class GenreDTO {
    private int id;
    private String genre;

    public GenreDTO(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }
}
