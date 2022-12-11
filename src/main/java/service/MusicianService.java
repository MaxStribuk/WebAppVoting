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
    public MusicianDTO getMusicianById(int id) {
        return dataSource.get(id);
    }

    @Override
    public boolean exists(int id) {
        return dataSource.exists(id);
    }
}
