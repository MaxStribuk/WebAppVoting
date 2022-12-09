package dao.api;

import dto.GenreDTO;

import java.util.List;

public interface GenreDAO {

    List<GenreDTO> getAllGenres();
    boolean exists(String genre);
}
