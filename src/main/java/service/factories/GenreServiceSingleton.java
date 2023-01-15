package service.factories;

import dao.factories.DAOType;
import dao.factories.GenreDAOSingleton;
import service.GenreService;
import service.api.IGenreService;

public class GenreServiceSingleton {

    private volatile static IGenreService instance = null;

    private GenreServiceSingleton() {
    }

    public static IGenreService getInstance() {
        if (instance == null) {
            synchronized (GenreServiceSingleton.class) {
                if (instance == null) {
                    instance = new GenreService(
                            GenreDAOSingleton.getInstance(DAOType.DB));
                }
            }
        }
        return instance;
    }
}