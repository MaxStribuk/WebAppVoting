package service.api;

import dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    List<GenreDTO> getAllGenres();
    boolean exists(GenreDTO genre);
}
