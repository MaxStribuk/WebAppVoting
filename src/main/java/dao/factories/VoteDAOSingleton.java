package dao.factories;

import dao.database.VoteDBDAO;
import dao.memory.VoteMemoryDAO;
import dao.api.IVoteDAO;

public class VoteDAOSingleton {

    private static volatile IVoteDAO instance;

    private VoteDAOSingleton() {
    }

    public static IVoteDAO getInstance(DAOType type) {
        if (instance == null) {
            synchronized (VoteDAOSingleton.class) {
                if (instance == null) {
                    switch (type) {
                        case DB: {
                            instance = new VoteDBDAO();
                            break;
                        }
                        case MEMORY: {
                            instance = new VoteMemoryDAO();
                            break;
                        }
                        default: {
                            throw new IllegalArgumentException("Illegal DAO type provided");
                        }
                    }
                }
            }
        }
        return instance;
    }
}