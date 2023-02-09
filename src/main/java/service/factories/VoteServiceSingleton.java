package service.factories;

import dao.factories.VoteDAOSingleton;
import service.VoteService;
import service.api.IVoteService;
import service.converters.VoteEntitySavedDTOConverter;
import service.converters.VoteSavedDTOEntityConverter;

public class VoteServiceSingleton {

    private volatile static IVoteService instance = null;

    private VoteServiceSingleton() {
    }

    public static IVoteService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceSingleton.class) {
                if (instance == null) {
                    instance = new VoteService(
                            VoteDAOSingleton.getInstance(),
                            GenreServiceSingleton.getInstance(),
                            ArtistServiceSingleton.getInstance(),
                            SendingServiceSingleton.getInstance(),
                            new VoteEntitySavedDTOConverter(),
                            new VoteSavedDTOEntityConverter()
                    );
                }
            }
        }
        return instance;
    }
}