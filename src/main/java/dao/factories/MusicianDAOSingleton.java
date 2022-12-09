package dao.factories;

import dao.MusicianDAO;
import dao.api.IMusicianDAO;

public class MusicianDAOSingleton {

    private static volatile IMusicianDAO instance;

    private MusicianDAOSingleton() {
    }

    public static IMusicianDAO getInstance() {
        if (instance == null) {
            synchronized (MusicianDAOSingleton.class) {
                if (instance == null) {
                    instance = new MusicianDAO();
                }
            }
        }
        return instance;
    }
}
