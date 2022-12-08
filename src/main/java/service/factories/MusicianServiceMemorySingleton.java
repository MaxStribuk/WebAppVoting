package service.factories;

import dao.MusicianMemoryDAO;
import service.MusicianService;
import service.api.IMusicianService;

public class MusicianServiceMemorySingleton {

    private volatile static IMusicianService instance = null;

    private MusicianServiceMemorySingleton() {

    }

    public static IMusicianService getInstance() {
        if(instance == null){
            synchronized (MusicianServiceMemorySingleton.class){
                if(instance == null){
                    instance = new MusicianService(MusicianMemoryDAO
                            .getInstance());
                }
            }
        }
        return instance;
    }
}
