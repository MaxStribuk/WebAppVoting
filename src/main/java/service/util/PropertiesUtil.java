package service.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();
    private static final String SENDER = "sender";
    private static final String PASSWORD = "password";
    String senderAddress = (String) PROPERTIES.get(SENDER);
    String senderPassword = (String) PROPERTIES.get(PASSWORD);
    private static final Authenticator authenticator = new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            try {
                return new PasswordAuthentication(sender,password);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream resourceAsStream = dao.util.PropertiesUtil.class
                .getClassLoader()
                .getResourceAsStream("mailservice.properties")) {
            PROPERTIES.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load mailservice.properties");
        }
    }

    static Properties get() {
        return PROPERTIES;
    }
}
