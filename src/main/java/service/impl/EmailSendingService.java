package service.impl;

import dao.api.IEmailSendingDAO;
import dao.entity.VoteEntity;
import dao.util.PropertiesUtil;
import dao.entity.EmailEntity;
import dao.entity.EmailStatus;
import dto.response.VoteDTOResponse;
import service.util.EmailSendingThread;
import service.api.IArtistService;
import service.api.IConvertable;
import service.api.IGenreService;
import service.api.ISendingService;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EmailSendingService implements ISendingService {

    private final IGenreService genreService;
    private final IArtistService artistService;
    private final IEmailSendingDAO emailSendingDAO;
    private final EmailSendingThread sendingThread;
    private final IConvertable<VoteEntity, VoteDTOResponse> voteEntityDTOConverter;
    private final Properties mailProperties;
    private final ScheduledExecutorService executorService;
    private final String SENDER;
    private final String PASSWORD;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
    private static final String SENDER_PROMPT = "mail.sender";
    private static final String PASSWORD_PROMPT = "mail.password";
    private static final String SUBJECT = "WebAppVoting Vote Confirmation";
    private static final String TRANSPORT_PROTOCOL = "mail.transport.protocol";
    private static final String SERVICE_HOST = "mail.host";
    private static final String SMPT_AUTHENTICATION = "mail.smtp.auth";
    private static final String SMPT_PORT = "mail.smtp.port";
    private static final String DEBUG_ON = "mail.debug";
    private static final String SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
    private static final String SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    private static final String SOCKET_FACTORY_FALLBACK = "mail.smtp.socketFactory.fallback";
    private static final String ENABLE_START_TLS = "mail.smtp.starttls.enable";
    private static final int MAX_VOTE_CONFIRMATION_SENDS = 3;
    private static final long INTERVAL_BETWEEN_SHIPMENTS = 10L;

    public EmailSendingService(IGenreService genreService,
                               IArtistService artistService,
                               IEmailSendingDAO emailSendingDAO,
                               IConvertable<VoteEntity, VoteDTOResponse> voteEntityDTOConverter,
                               Properties properties,
                               ScheduledExecutorService executorService,
                               EmailSendingThread emailSendingThread) {
        this.genreService = genreService;
        this.artistService = artistService;
        this.emailSendingDAO = emailSendingDAO;
        this.executorService = executorService;
        this.sendingThread = emailSendingThread;
        this.voteEntityDTOConverter = voteEntityDTOConverter;
        this.mailProperties = properties;
        this.SENDER = PropertiesUtil.get(SENDER_PROMPT);
        this.PASSWORD = PropertiesUtil.get(PASSWORD_PROMPT);

        prepareMailProperties();
    }

    @Override
    public void initializeSendingService() {
        executorService.scheduleWithFixedDelay(sendingThread,
                INTERVAL_BETWEEN_SHIPMENTS,
                INTERVAL_BETWEEN_SHIPMENTS,
                TimeUnit.SECONDS);
    }

    @Override
    public void stopSendingService() {
        this.executorService.shutdown();
    }

    @Override
    public void confirmVote(VoteEntity vote) {
        emailSendingDAO.add(createEmailEntity(vote));
    }

    @Override
    public void send(EmailEntity email) throws MessagingException {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER, PASSWORD);
            }
        };
        Session session = Session.getInstance(mailProperties,
                authenticator);
        MimeMessage message = new MimeMessage(session);
        InternetAddress address = new InternetAddress(SENDER);
        message.setFrom(address);
        message.setRecipients(Message.RecipientType.TO,
                email.getRecipient());
        message.setSubject(email.getTopic());
        message.setText(email.getTextMessage());
        Transport.send(message);
    }

    private void prepareMailProperties() {
        mailProperties.put(TRANSPORT_PROTOCOL,
                PropertiesUtil.get(TRANSPORT_PROTOCOL));
        mailProperties.put(SERVICE_HOST, PropertiesUtil.get(SERVICE_HOST));
        mailProperties.put(SMPT_AUTHENTICATION,
                PropertiesUtil.get(SMPT_AUTHENTICATION));
        mailProperties.put(SMPT_PORT, PropertiesUtil.get(SMPT_PORT));
        mailProperties.put(DEBUG_ON, PropertiesUtil.get(DEBUG_ON));
        mailProperties.put(SOCKET_FACTORY_PORT,
                PropertiesUtil.get(SOCKET_FACTORY_PORT));
        mailProperties.put(SOCKET_FACTORY_CLASS,
                PropertiesUtil.get(SOCKET_FACTORY_CLASS));
        mailProperties.put(SOCKET_FACTORY_FALLBACK,
                PropertiesUtil.get(SOCKET_FACTORY_FALLBACK));
        mailProperties.put(ENABLE_START_TLS,
                PropertiesUtil.get(ENABLE_START_TLS));
    }

    private EmailEntity createEmailEntity(VoteEntity vote) {
        VoteDTOResponse voteDTO = voteEntityDTOConverter.convert(vote);
        String messageText = createVoteConfirmationText(voteDTO);
        String recipient = vote.getEmail();
        return new EmailEntity(vote, recipient, SUBJECT,
                messageText, MAX_VOTE_CONFIRMATION_SENDS, EmailStatus.WAITING);
    }

    private String createVoteConfirmationText(VoteDTOResponse vote) {
        return "Thank you for submitting your vote!\n" +
                getFormattedGenreNames(vote) +
                getFormattedArtistName(vote) +
                getFormattedAbout(vote) +
                "Vote date: " +
                vote.getCreationTime().format(formatter);
    }

    private String getFormattedGenreNames(VoteDTOResponse voteDTORequest) {
        StringBuilder message = new StringBuilder();
        message.append("Your genre vote:\n");
        List<Long> genreIDs = voteDTORequest.getGenreIds();
        for (int i = 0; i < genreIDs.size(); i++) {
            message.append(i + 1)
                    .append(". ")
                    .append(this.genreService.get(genreIDs.get(i)).getTitle())
                    .append("\n");
        }
        return message.toString();
    }

    private String getFormattedArtistName(VoteDTOResponse voteDTORequest) {
        StringBuilder message = new StringBuilder();
        message.append("Your artist vote:\n");
        long artistID = voteDTORequest.getArtistId();
        message.append("1. ")
                .append(this.artistService.get(artistID).getName())
                .append("\n");
        return message.toString();
    }

    private String getFormattedAbout(VoteDTOResponse voteDTORequest) {
        return "Your about text:\n" +
                voteDTORequest.getAbout() +
                "\n";
    }
}