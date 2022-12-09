package service.factories;

import dao.VoteDAO;
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
                    instance = new VoteService(VoteDAO.getInstance());
                }
            }
        }
        return instance;
    }
}
