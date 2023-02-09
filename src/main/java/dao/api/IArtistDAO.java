package dao.api;

import dao.entity.ArtistEntity;

import java.util.List;

public interface IArtistDAO {

    List<ArtistEntity> getAll();

    boolean exists(long id);

    ArtistEntity get(long id);

    void add(ArtistEntity artist);

    void update(ArtistEntity artist);

    void delete(long id);
}