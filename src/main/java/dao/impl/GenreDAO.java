package dao.impl;

import dao.api.IGenreDAO;
import dao.entity.GenreEntity;
import dao.util.ConnectionManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenreDAO implements IGenreDAO {

    private final ConnectionManager connectionManager;

    public GenreDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<GenreEntity> getAll() {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaQuery<GenreEntity> query = entityManager.getCriteriaBuilder()
                    .createQuery(GenreEntity.class);
            Root<GenreEntity> root = query.from(GenreEntity.class);
            CriteriaQuery<GenreEntity> all = query.select(root);
            TypedQuery<GenreEntity> allQuery = entityManager.createQuery(all);
            List<GenreEntity> genres = allQuery.getResultList();

            entityManager.getTransaction().commit();
            return genres;
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
    public GenreEntity get(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);

            entityManager.getTransaction().commit();
            return genreEntity;
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

    public void add(GenreEntity genre) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(genre);
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
    public void update(GenreEntity genre) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            if (genre == null) {
                throw new IllegalArgumentException("Genre with this id was not found in the database");
            }
            entityManager.merge(genre);

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

            GenreEntity genre = entityManager.find(GenreEntity.class, id);
            if (genre == null) {
                throw new IllegalArgumentException("Genre with this id was not found in the database");
            }
            entityManager.remove(genre);

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