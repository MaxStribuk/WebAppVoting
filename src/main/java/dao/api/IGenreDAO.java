package dao.api;

import dao.entity.GenreEntity;

import java.util.List;

public interface IGenreDAO {

    List<GenreEntity> getAll();

    GenreEntity get(long id);

    void add(GenreEntity genre);

    void update(GenreEntity genre);
    
    void delete(long id);
}