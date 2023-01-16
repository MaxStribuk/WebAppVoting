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
    public List<GenreDTO> getAll() {
        return dataSource.getAll();
    }

    @Override
    public GenreDTO get(int id) {
        return dataSource.get(id);
    }

    @Override
    public boolean exists(int id) {
        return dataSource.exists(id);
    }

    @Override
    public void add(String genre) {
        boolean isRecurringGenre = getAll().stream()
                .map(GenreDTO::getGenre)
                .anyMatch(nameGenre -> nameGenre.equalsIgnoreCase(genre));
        if (isRecurringGenre) {
            throw new IllegalArgumentException("This genre has already " +
                    "been added");
        } else {
            dataSource.add(genre);
        }
    }

    @Override
    public void update(int id, String genre) {
        if (dataSource.exists(id)) {
            dataSource.update(id, genre);
        } else {
            throw new IllegalArgumentException("No genre updated for id " + id);
        }
    }

    @Override
    public void delete(int id) {
        if (dataSource.exists(id)) {
            dataSource.delete(id);
        } else {
            throw new IllegalArgumentException("No genre deleted for id " + id);
        }
    }
}