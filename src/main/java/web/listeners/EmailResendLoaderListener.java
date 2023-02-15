package web.listeners;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import service.api.ISendingService;

@Component
public class EmailResendLoaderListener {

    private final ISendingService sendingService;

    public EmailResendLoaderListener(ISendingService sendingService) {
        this.sendingService = sendingService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextInitialized() {
        sendingService.initializeSendingService();
    }

    @EventListener(ContextClosedEvent.class)
    public void destroy() {
        sendingService.stopSendingService();
    }
}