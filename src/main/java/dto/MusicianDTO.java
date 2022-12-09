package dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicianDTO that = (MusicianDTO) o;
        return id == that.id && musician.equals(that.musician);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, musician);
    }
}
