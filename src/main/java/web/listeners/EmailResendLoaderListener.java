package web.listeners;

import service.api.ISendingService;
import service.factories.SenderServiceSingleton;

import javax.mail.MessagingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.TimeUnit;

public class EmailResendLoaderListener implements ServletContextListener {

    private final ISendingService sendingService;

    public EmailResendLoaderListener() {
        sendingService = SenderServiceSingleton.getInstance();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        while (true) {
            sendingService.getExecutorService().scheduleWithFixedDelay(() -> {
                try {
                    sendingService.resend();
                } catch (MessagingException e) {
                    try {
                        TimeUnit.HOURS.sleep(1L);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }, 60L, 5L, TimeUnit.SECONDS);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sendingService.getExecutorService().shutdown();
    }
}