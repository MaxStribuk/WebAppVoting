package dao.factories;

import dao.api.IConnection;
import dao.util.PostgresConnection;

public class ConnectionSingleton {
    private static volatile IConnection instance;

    private ConnectionSingleton() {
    }

    public static IConnection getInstance() {
        if (instance == null) {
            synchronized (ArtistDAOSingleton.class) {
                if (instance == null) {
                    instance=new PostgresConnection();
                }
            }
        }
        return instance;
    }
}
