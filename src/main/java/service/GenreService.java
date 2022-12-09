package service;

import dao.api.IGenreDAO;
import dto.GenreDTO;
import service.api.IGenreService;

import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDAO dataSource;

    public GenreService(IGenreDAO dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return dataSource.getAllGenres();
    }

    @Override
    public boolean exists(GenreDTO genre) {
        return dataSource.exists(genre);
    }
}
