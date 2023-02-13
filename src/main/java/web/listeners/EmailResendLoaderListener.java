package web.listeners;

import service.api.ISendingService;

public class EmailResendLoaderListener {

    private final ISendingService sendingService;

    public EmailResendLoaderListener(ISendingService sendingService) {
        this.sendingService = sendingService;
    }

    public void contextInitialized() {
        sendingService.initializeSendingService();
    }

    public void destroy() {
        sendingService.stopSendingService();
    }
}