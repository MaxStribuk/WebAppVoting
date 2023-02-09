package service.factories;

import dao.factories.EmailSendingDAOSingleton;
import service.EmailSendingService;
import service.api.ISendingService;
import service.converters.VoteEntitySavedDTOConverter;

public class SendingServiceSingleton {

    private volatile static ISendingService instance = null;

    private SendingServiceSingleton() {
    }

    public static ISendingService getInstance() {
        if (instance == null) {
            synchronized (SendingServiceSingleton.class) {
                if (instance == null) {
                    instance = new EmailSendingService(
                            GenreServiceSingleton.getInstance(),
                            ArtistServiceSingleton.getInstance(),
                            EmailSendingDAOSingleton.getInstance(),
                            new VoteEntitySavedDTOConverter());
                }
            }
        }
        return instance;
    }
}