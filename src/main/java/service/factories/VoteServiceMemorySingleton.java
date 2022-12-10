package service.factories;

import dao.VoteMemoryDAO;
import service.VoteService;
import service.api.IVoteService;

public class VoteServiceMemorySingleton {

    private volatile static IVoteService instance = null;

    private VoteServiceMemorySingleton() {
    }

    public static IVoteService getInstance() {
        if(instance == null){
            synchronized (VoteServiceMemorySingleton.class){
                if(instance == null){
                    instance = new VoteService(VoteMemoryDAO.getInstance());
                }
            }
        }
        return instance;
    }
}
