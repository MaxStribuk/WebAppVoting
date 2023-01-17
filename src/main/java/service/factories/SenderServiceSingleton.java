package service.factories;

import service.SenderService;
import service.api.ISenderService;

public class SenderServiceSingleton {

    private volatile static ISenderService instance = null;

    private SenderServiceSingleton() {
    }

    public static ISenderService getInstance() {
        if (instance == null) {
            synchronized (SenderServiceSingleton.class) {
                if (instance == null) {
                    instance = new SenderService(GenreServiceSingleton.getInstance(),
                            ArtistServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
