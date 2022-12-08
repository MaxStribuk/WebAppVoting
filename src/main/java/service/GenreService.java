package service;

import dao.api.GenreDAO;
import service.api.IGenreService;

import java.util.List;

public class GenreService implements IGenreService {

    private final GenreDAO dataSource;

    public GenreService(GenreDAO dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getAllGenres() {
        return dataSource.getAllGenres();
    }

    @Override
    public boolean exists(String genre) {
        return dataSource.exists(genre);
    }
}
