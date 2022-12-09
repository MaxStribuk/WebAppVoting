package dao.factories;

import dao.VoteDAO;
import dao.api.IVoteDAO;

public class VoteDAOSingleton {

    private static volatile IVoteDAO instance;

    private VoteDAOSingleton() {
    }

    public static IVoteDAO getInstance() {
        if (instance == null) {
            synchronized (VoteDAOSingleton.class) {
                if (instance == null) {
                    instance = new VoteDAO();
                }
            }
        }
        return instance;
    }
}
