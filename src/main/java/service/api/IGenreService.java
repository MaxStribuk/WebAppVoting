package service.api;

import dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    List<GenreDTO> getAll();

    GenreDTO get(int id);

    boolean exists(int id);

    void add(String genre);

    void update(int id, String genre);

    void delete(int id);
}