package dao;

import dao.api.GenreDAO;
import java.util.List;

public class GenreMemoryDAO implements GenreDAO {

    private static volatile GenreMemoryDAO instance = null;

    private final List<String> genres = List.of("Pop",
            "Rap",
            "Techno",
            "Dubstep",
            "Jazz",
            "Classic Rock",
            "Country",
            "Hard Rock",
            "Blues",
            "Hip Hop");
    private GenreMemoryDAO() {
    }

    public static GenreMemoryDAO getInstance() {
        if (instance == null) {
            synchronized (GenreMemoryDAO.class) {
                if (instance == null) {
                    instance = new GenreMemoryDAO();
                }
            }
        }
        return instance;
    }

    @Override
    //no list? List.of?
    public List<String> getAllGenres() {
        return genres;
    }

    @Override
    public boolean exists(String genre) {
        return genres.contains(genre);
    }
}
