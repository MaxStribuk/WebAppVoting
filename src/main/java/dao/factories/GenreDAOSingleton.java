package dao.factories;

import dao.GenreMemoryDAO;
import dao.api.IGenreDAO;

public class GenreDAOSingleton {

    private static volatile IGenreDAO instance;

    private GenreDAOSingleton() {
    }

    public static IGenreDAO getInstance() {
        if (instance == null) {
            synchronized (GenreDAOSingleton.class) {
                if (instance == null) {
                    instance = new GenreMemoryDAO();
                }
            }
        }
        return instance;
    }
}