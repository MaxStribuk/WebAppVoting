package dao.factories;

import dao.GenreDBDAO;
import dao.GenreMemoryDAO;
import dao.api.IGenreDAO;

public class GenreDAOSingleton {

    private static volatile IGenreDAO instance;

    private GenreDAOSingleton() {
    }

    public static IGenreDAO getInstance(DAOType type) {
        if (instance == null) {
            synchronized (GenreDAOSingleton.class) {
                if (instance == null) {
                    switch (type) {
                        case DB: {
                            instance = new GenreDBDAO();
                            break;
                        }
                        case MEMORY: {
                            instance = new GenreMemoryDAO();
                            break;
                        }
                        default: {
                            throw new IllegalArgumentException("Illegal DAO type provided");
                        }
                    }
                }
            }
        }
        return instance;
    }
}