package dao.impl;
import dao.api.IArtistDAO;
import dao.entity.ArtistEntity;
import dao.util.ConnectionManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArtistDAO implements IArtistDAO {

    private final ConnectionManager connectionManager;

    public ArtistDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<ArtistEntity> getAll() {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaQuery<ArtistEntity> query = entityManager.getCriteriaBuilder()
                    .createQuery(ArtistEntity.class);
            Root<ArtistEntity> root = query.from(ArtistEntity.class);
            CriteriaQuery<ArtistEntity> all = query.select(root);
            TypedQuery<ArtistEntity> allQuery = entityManager.createQuery(all);
            List<ArtistEntity> artists = allQuery.getResultList();

            entityManager.getTransaction().commit();
            return artists;
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
    public ArtistEntity get(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            ArtistEntity artistEntity = entityManager.find(ArtistEntity.class, id);

            entityManager.getTransaction().commit();
            return artistEntity;
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
    public void add(ArtistEntity artist) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(artist);

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
    public void update(ArtistEntity artist) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            if (artist == null) {
                throw new IllegalArgumentException("Artist with this id was not found in the database");
            }
            entityManager.merge(artist);

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
    public void delete(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            ArtistEntity artist = entityManager.find(ArtistEntity.class, id);
            if (artist == null) {
                throw new IllegalArgumentException("Artist with this id was not found in the database");
            }
            entityManager.remove(artist);

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