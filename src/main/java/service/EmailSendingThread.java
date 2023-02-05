package service;

import dao.api.IEmailSendingDAO;
import dao.entity.EmailEntity;
import dao.entity.EmailStatus;
import service.api.ISendingService;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EmailSendingThread implements Runnable {

    private final ScheduledExecutorService executorService;
    private final IEmailSendingDAO emailSendingDAO;
    private final ISendingService sendingService;
    private static final long PAUSE_SENDING_EMAIL = 10L;

    public EmailSendingThread(ScheduledExecutorService executorService,
                              IEmailSendingDAO emailSendingDAO,
                              ISendingService sendingService) {
        this.executorService = executorService;
        this.emailSendingDAO = emailSendingDAO;
        this.sendingService = sendingService;
    }

    @Override
    public void run() {
        List<EmailEntity> emails = emailSendingDAO.getUnsent();
        int countExceptions = 0;
        for (EmailEntity email : emails) {
            email.setStatus(EmailStatus.SENT);
            email.setDepartures(email.getDepartures() - 1);
            emailSendingDAO.update(email);
            try {
                executorService.submit(() -> {
                    try {
                        sendingService.send(email);
                        email.setStatus(EmailStatus.SUCCESS);
                    } catch (AddressException e) {
                        email.setStatus(EmailStatus.ERROR);
                        throw new RuntimeException("Failed to send the confirmation " +
                                "email due to wrongly formatted address ", e);
                    } catch (MessagingException e) {
                        email.setStatus(EmailStatus.WAITING);
                        throw new RuntimeException("Failed to send " +
                                "the confirmation email", e);
                    } finally {
                        emailSendingDAO.update(email);
                    }
                }).get();
            } catch (Exception e) {
                countExceptions++;
                e.printStackTrace();
            }
        }
        if (countExceptions == emails.size() && emails.size() != 0) {
            try {
                TimeUnit.MINUTES.sleep(PAUSE_SENDING_EMAIL);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}