package service.factories;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageFactory {

    private static volatile MessageFactory instance;
    private static final Properties AUTHENTICATION_PROPERTIES = new Properties();
    private static final Properties TRANSPORT_PROPERTIES = new Properties();
    private final String SENDER_ADDRESS;
    private final String SENDER_PASSWORD;

    private MessageFactory() {
        loadProperties();
        SENDER_ADDRESS = AUTHENTICATION_PROPERTIES.getProperty("sender");
        SENDER_PASSWORD = AUTHENTICATION_PROPERTIES.getProperty("password");
    }

    private void loadProperties() {
        try (InputStream resourceAsStream = MessageFactory.class
                .getClassLoader()
                .getResourceAsStream("mailservice.properties")) {
            TRANSPORT_PROPERTIES.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load mailservice.properties");
        }

        try (InputStream resourceAsStream = MessageFactory.class
                .getClassLoader()
                .getResourceAsStream("mailauthentication.properties")) {
            AUTHENTICATION_PROPERTIES.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " +
                    "mailauthentication.properties");
        }
    }
    public static MessageFactory getInstance() {
        if (instance == null) {
            synchronized (MessageFactory.class) {
                if (instance == null) {
                    instance = new MessageFactory();
                }
            }
        }
        return instance;
    }
    public MimeMessage getMessage() {
        Authenticator authenticator = new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_ADDRESS,
                        SENDER_PASSWORD);
            }
        };
        Session session = Session.getInstance(TRANSPORT_PROPERTIES,
                authenticator);

        return new MimeMessage(session);
    }

    public String getSender() {
        return SENDER_ADDRESS;
    }
}
