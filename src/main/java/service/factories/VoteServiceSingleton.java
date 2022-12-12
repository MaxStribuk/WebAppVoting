package service.factories;

import dao.factories.GenreDAOSingleton;
import dao.factories.MusicianDAOSingleton;
import dao.factories.VoteDAOSingleton;
import service.VoteService;
import service.api.IVoteService;

public class VoteServiceSingleton {

    private volatile static IVoteService instance = null;

    private VoteServiceSingleton() {
    }

    public static IVoteService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceSingleton.class) {
                if (instance == null) {
                    instance = new VoteService(VoteDAOSingleton.getInstance(),
                            GenreDAOSingleton.getInstance(),
                            MusicianDAOSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
