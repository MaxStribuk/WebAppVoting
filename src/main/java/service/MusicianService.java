package service;

import dao.api.IMusicianDAO;
import dto.MusicianDTO;
import service.api.IMusicianService;

import java.util.List;

public class MusicianService implements IMusicianService {

    private final IMusicianDAO dataSource;

    public MusicianService(IMusicianDAO dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<MusicianDTO> getAll() {
        return dataSource.getAll();
    }

    @Override
    public boolean exists(MusicianDTO musician) {
        return dataSource.exists(musician);
    }
}
