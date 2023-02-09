package service.api;

import dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    List<GenreDTO> getAll();

    GenreDTO get(long id);

    boolean exists(long id);

    void add(GenreDTO genre);

    void update(GenreDTO genre);

    void delete(long id);
}