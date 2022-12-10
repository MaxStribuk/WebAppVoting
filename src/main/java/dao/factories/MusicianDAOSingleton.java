package dao.factories;

import dao.MusicianMemoryDAO;
import dao.api.IMusicianDAO;

public class MusicianDAOSingleton {

    private static volatile IMusicianDAO instance;

    private MusicianDAOSingleton() {
    }

    public static IMusicianDAO getInstance() {
        if (instance == null) {
            synchronized (MusicianDAOSingleton.class) {
                if (instance == null) {
                    instance = new MusicianMemoryDAO();
                }
            }
        }
        return instance;
    }
}
