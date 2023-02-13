package dao.impl;

import dao.api.IVoteDAO;
import dao.entity.ArtistEntity;
import dao.entity.GenreEntity;
import dao.entity.VoteEntity;
import dao.util.ConnectionManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class VoteDAO implements IVoteDAO {

    private final ConnectionManager connectionManager;

    public VoteDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<VoteEntity> getAll() {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<VoteEntity> query = criteriaBuilder
                    .createQuery(VoteEntity.class);
            Root<VoteEntity> root = query.from(VoteEntity.class);
            root.fetch("genres");
            CriteriaQuery<VoteEntity> all = query.select(root);
            all.distinct(true);
            TypedQuery<VoteEntity> allQuery = entityManager.createQuery(all);
            List<VoteEntity> votes = allQuery.getResultList();

            entityManager.getTransaction().commit();
            return votes;
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
    public void save(VoteEntity vote) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();

            ArtistEntity artist = entityManager.find(ArtistEntity.class, vote.getArtist().getId());
            vote.setArtist(artist);
            List<GenreEntity> genres = vote.getGenres();
            vote.setGenres(new ArrayList<>());
            final EntityManager finalEntityManager = entityManager;
            genres.stream()
                    .map(GenreEntity::getId)
                    .forEach(id -> vote.getGenres().add(finalEntityManager.find(GenreEntity.class, id)));
            entityManager.persist(vote);

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