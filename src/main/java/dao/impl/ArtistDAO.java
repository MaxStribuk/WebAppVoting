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
        List<ArtistEntity> artists;
        EntityManager entityManager = connectionManager.getEntityManager();
        entityManager.getTransaction().begin();

        CriteriaQuery<ArtistEntity> query = entityManager.getCriteriaBuilder()
                .createQuery(ArtistEntity.class);
        Root<ArtistEntity> root = query.from(ArtistEntity.class);
        CriteriaQuery<ArtistEntity> all = query.select(root);
        TypedQuery<ArtistEntity> allQuery = entityManager.createQuery(all);
        artists = allQuery.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return artists;
    }

    @Override
    public boolean exists(long id) {
        return get(id) != null;
    }

    @Override
    public ArtistEntity get(long id) {
        EntityManager entityManager = connectionManager.getEntityManager();
        entityManager.getTransaction().begin();

        ArtistEntity artistEntity = entityManager.find(ArtistEntity.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return artistEntity;
    }

    @Override
    public void add(ArtistEntity artist) {
        EntityManager entityManager = connectionManager.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(artist);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(long id, ArtistEntity artist) {
        EntityManager entityManager = connectionManager.getEntityManager();
        entityManager.getTransaction().begin();

        ArtistEntity artistEntity = entityManager.find(ArtistEntity.class, id);
        artistEntity.setArtist(artist.getArtist());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = connectionManager.getEntityManager();
        entityManager.getTransaction().begin();

        ArtistEntity artist = entityManager.find(ArtistEntity.class, id);
        entityManager.remove(artist);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}