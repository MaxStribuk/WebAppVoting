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
    public ArtistDTO getArtistById(int id) {
        return dataSource.get(id);
    }

    @Override
    public boolean exists(int id) {
        return dataSource.exists(id);
    }
}