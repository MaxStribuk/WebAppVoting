package service.factories;

import dao.VoteMemoryDAO;
import service.VoteService;
import service.api.IVoteService;

public class VoteServiceSingleton {

    private volatile static IVoteService instance = null;

    private VoteServiceSingleton() {
    }

    public static IVoteService getInstance() {
        if(instance == null){
            synchronized (VoteServiceSingleton.class){
                if(instance == null){
                    instance = new VoteService(VoteMemoryDAO.getInstance());
                }
            }
        }
        return instance;
    }
}
