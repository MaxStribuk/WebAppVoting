package dao.util;

import dao.api.IConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionManager implements IConnection {

    private final EntityManagerFactory sessionFactory;

    public ConnectionManager() {
        sessionFactory = Persistence.createEntityManagerFactory(
                "org.hibernate.voting.jpa", PropertiesUtil.get());
    }

    @Override
    public EntityManager getEntityManager() {
        return sessionFactory.createEntityManager();
    }


    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}