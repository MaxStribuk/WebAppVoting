package dao.util;

import dao.api.IConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ConnectionManager implements IConnection {

    private final EntityManagerFactory sessionFactory;

    public ConnectionManager(EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.sessionFactory.createEntityManager();
    }


    @Override
    public void close() throws Exception {
        this.sessionFactory.close();
    }
}