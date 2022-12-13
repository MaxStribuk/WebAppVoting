package dao.factories;

import dao.ArtistMemoryDAO;
import dao.api.IArtistDAO;

public class ArtistDAOSingleton {

    private static volatile IArtistDAO instance;

    private ArtistDAOSingleton() {
    }

    public static IArtistDAO getInstance() {
        if (instance == null) {
            synchronized (ArtistDAOSingleton.class) {
                if (instance == null) {
                    instance = new ArtistMemoryDAO();
                }
            }
        }
        return instance;
    }
}