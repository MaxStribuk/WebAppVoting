package service.factories;

import dao.factories.MusicianDAOSingleton;
import service.MusicianService;
import service.api.IMusicianService;

public class MusicianServiceSingleton {

    private volatile static IMusicianService instance = null;

    private MusicianServiceSingleton() {
    }

    public static IMusicianService getInstance() {
        if(instance == null){
            synchronized (MusicianServiceSingleton.class){
                if(instance == null){
                    instance = new MusicianService(MusicianDAOSingleton
                            .getInstance());
                }
            }
        }
        return instance;
    }
}
