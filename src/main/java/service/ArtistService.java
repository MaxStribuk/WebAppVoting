package service;

import dao.api.IArtistDAO;
import dto.ArtistDTO;
import service.api.IArtistService;

import java.util.List;

public class ArtistService implements IArtistService {

    private final IArtistDAO dataSource;

    public ArtistService(IArtistDAO dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<ArtistDTO> getAll() {
        return dataSource.getAll();
    }

    @Override
    public boolean exists(int id) {
        return dataSource.exists(id);
    }

    @Override
    public ArtistDTO get(int id) {
        return dataSource.get(id);
    }

    @Override
    public void add(String artist) {
        dataSource.add(artist);
    }

    @Override
    public void update(int id, String artist) {
        if (dataSource.exists(id)) {
            dataSource.update(id, artist);
        } else {
            throw new IllegalArgumentException("No artist updated for id " + id);
        }
    }

    @Override
    public void delete(int id) {
        if (dataSource.exists(id)) {
            dataSource.delete(id);
        } else {
            throw new IllegalArgumentException("No artist deleted for id " + id);
        }
    }
}