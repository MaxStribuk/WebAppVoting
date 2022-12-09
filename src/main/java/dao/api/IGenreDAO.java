package dao.api;

import dto.GenreDTO;

import java.util.List;

public interface IGenreDAO {

    List<GenreDTO> getAllGenres();
    boolean exists(GenreDTO genre);
}
