package web.listeners;

import service.api.ISendingService;
import service.factories.SendingServiceSingleton;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EmailResendLoaderListener implements ServletContextListener {

    private final ISendingService sendingService;

    public EmailResendLoaderListener() {
        sendingService = SendingServiceSingleton.getInstance();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sendingService.initializeSendingService();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sendingService.stopSendingService();
    }
}