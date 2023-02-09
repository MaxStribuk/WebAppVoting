package dao.database;

import dao.api.IGenreDAO;
import dao.entity.GenreEntity;
import dao.util.ConnectionManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenreDBDAO implements IGenreDAO {

    private final ConnectionManager connectionManager;

    public GenreDBDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<GenreEntity> getAll() {
        List<GenreEntity> genres;
        EntityManager entityManager = connectionManager.getEntityManager();
        entityManager.getTransaction().begin();

        CriteriaQuery<GenreEntity> query = entityManager.getCriteriaBuilder()
                .createQuery(GenreEntity.class);
        Root<GenreEntity> root = query.from(GenreEntity.class);
        CriteriaQuery<GenreEntity> all = query.select(root);
        TypedQuery<GenreEntity> allQuery = entityManager.createQuery(all);
        genres = allQuery.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return genres;
    }

    @Override
    public boolean exists(long id) {
        return get(id) != null;
    }

    @Override
    public GenreEntity get(long id) {
        EntityManager entityManager = connectionManager.getEntityManager();
        entityManager.getTransaction().begin();

        GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return genreEntity;
    }

    public void add(GenreEntity genre) {
        EntityManager entityManager = connectionManager.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(genre);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(GenreEntity genre) {
        EntityManager entityManager = connectionManager.getEntityManager();
        entityManager.getTransaction().begin();

        GenreEntity genreEntity = entityManager.find(GenreEntity.class, genre.getId());
        genreEntity.setTitle(genre.getTitle());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = connectionManager.getEntityManager();
        entityManager.getTransaction().begin();

        GenreEntity genre = entityManager.find(GenreEntity.class, id);
        entityManager.remove(genre);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}