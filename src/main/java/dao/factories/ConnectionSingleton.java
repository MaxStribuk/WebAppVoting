package dao.factories;

import dao.api.IArtistDAO;
import dao.api.IConnection;
import dao.util.ConnectionManager;

public class ConnectionSingleton {
    private static volatile IConnection instance;

    private ConnectionSingleton() {
    }

    public static IConnection getInstance() {
        if (instance == null) {
            synchronized (ArtistDAOSingleton.class) {
                if (instance == null) {
                    instance=new ConnectionManager();
                }
            }
        }
        return instance;
    }
}
