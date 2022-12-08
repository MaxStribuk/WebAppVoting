package service.factories;

import dao.GenreMemoryDAO;
import service.GenreService;
import service.api.IGenreService;

public class GenreServiceMemorySingleton {


    private volatile static IGenreService instance = null;

    private GenreServiceMemorySingleton() {
    }

    public static IGenreService getInstance() {
        if(instance == null){
            synchronized (GenreServiceMemorySingleton.class){
                if(instance == null){
                    instance = new GenreService(GenreMemoryDAO.getInstance());
                }
            }
        }
        return instance;
    }
}
