package dao.database;

import dao.api.IVoteDAO;
import dao.entity.ArtistEntity;
import dao.entity.GenreEntity;
import dao.entity.VoteEntity;
import dao.factories.ConnectionSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class VoteDBDAO implements IVoteDAO {

    @Override
    public List<VoteEntity> getAll() {
        List<VoteEntity> votes;
        EntityManager entityManager = ConnectionSingleton.getInstance().getEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteEntity> query = criteriaBuilder
                .createQuery(VoteEntity.class);
        Root<VoteEntity> root = query.from(VoteEntity.class);
        root.fetch("genres");
        CriteriaQuery<VoteEntity> all = query.select(root);
        all.distinct(true);
        TypedQuery<VoteEntity> allQuery = entityManager.createQuery(all);
        votes = allQuery.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return votes;
    }

    @Override
    public void save(VoteEntity vote) {
        EntityManager entityManager = ConnectionSingleton.getInstance().getEntityManager();
        entityManager.getTransaction().begin();


        ArtistEntity artist = entityManager.find(ArtistEntity.class, vote.getArtist().getId());
        vote.setArtist(artist);
        List<GenreEntity> genres = vote.getGenres();
        vote.setGenres(new ArrayList<>());
        genres.stream()
                .map(GenreEntity::getId)
                .forEach(id -> vote.getGenres().add(entityManager.find(GenreEntity.class, id)));
        entityManager.persist(vote);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}