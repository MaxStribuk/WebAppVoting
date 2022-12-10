package dao;

import dao.api.IGenreDAO;
import dto.GenreDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenreMemoryDAO implements IGenreDAO {

    private final List<GenreDTO> genres;

    public GenreMemoryDAO() {
        genres = new ArrayList<>();
        genres.add(new GenreDTO(1,"Pop"));
        genres.add(new GenreDTO(2,"Rap"));
        genres.add(new GenreDTO(3,"Techno"));
        genres.add(new GenreDTO(4,"Dubstep"));
        genres.add(new GenreDTO(5,"Jazz"));
        genres.add(new GenreDTO(6,"Classic Rock"));
        genres.add(new GenreDTO(7,"Country"));
        genres.add(new GenreDTO(8,"Hard Rock"));
        genres.add(new GenreDTO(9,"Blues"));
        genres.add(new GenreDTO(10,"Hip Hop"));
    }

    @Override
    public List<GenreDTO> getAll() {
        return Collections.unmodifiableList(genres);
    }

    @Override
    public boolean exists(GenreDTO genre) {
        return genres.contains(genre);
    }
}
