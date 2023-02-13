package dao.api;

import dao.entity.GenreEntity;

import java.util.List;

public interface IGenreDAO {

    List<GenreEntity> getAll();

    boolean exists(long id);

    GenreEntity get(long id);

    void add(GenreEntity genre);

    void update(long id, GenreEntity genre);
    
    void delete(long id);
}