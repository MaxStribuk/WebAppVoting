package dao.factories;

import dao.api.IEmailSendingDAO;
import dao.database.EmailSendingDBDAO;

public class EmailSendingDAOSingleton {

    private static volatile IEmailSendingDAO instance;

    private EmailSendingDAOSingleton() {
    }

    public static IEmailSendingDAO getInstance() {
        if (instance == null) {
            synchronized (EmailSendingDAOSingleton.class) {
                if (instance == null) {
                    instance = new EmailSendingDBDAO();
                }
            }
        }
        return instance;
    }
}