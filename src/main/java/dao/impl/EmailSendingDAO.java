package dao.impl;

import dao.api.IEmailSendingDAO;
import dao.entity.EmailEntity;
import dao.entity.EmailStatus;
import dao.util.ConnectionManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmailSendingDAO implements IEmailSendingDAO {

    private final ConnectionManager connectionManager;

    private static final int NUMBER_EMAILS_TO_SEND = 10;

    public EmailSendingDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void add(EmailEntity email) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(email);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<EmailEntity> getUnsent() {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EmailEntity> query = criteriaBuilder.createQuery(EmailEntity.class);
            Root<EmailEntity> root = query.from(EmailEntity.class);
            CriteriaQuery<EmailEntity> unsentEmails = query
                    .select(root)
                    .where(criteriaBuilder.and(
                            criteriaBuilder.gt(root.get("departures"), 0),
                            criteriaBuilder.equal(root.get("status"), EmailStatus.WAITING)
                    ))
                    .orderBy(criteriaBuilder.asc(root.get("id")));
            TypedQuery<EmailEntity> unsentEmailsQuery = entityManager.createQuery(unsentEmails);
            List<EmailEntity> emails = unsentEmailsQuery
                    .setFirstResult(0)
                    .setMaxResults(NUMBER_EMAILS_TO_SEND)
                    .getResultList();

            entityManager.getTransaction().commit();
            return emails;
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void update(EmailEntity email) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            EmailEntity emailEntity = entityManager.find(EmailEntity.class, email.getId());
            if (emailEntity == null) {
                throw new IllegalArgumentException(
                        "Email with this id was not found in the database");
            }
            emailEntity.setVote(email.getVote());
            emailEntity.setRecipient(email.getRecipient());
            emailEntity.setTopic(email.getTopic());
            emailEntity.setTextMessage(email.getTextMessage());
            emailEntity.setDepartures(email.getDepartures());
            emailEntity.setStatus(email.getStatus());

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}