package service;

import dao.api.MusicianDAO;
import service.api.IMusicianService;

import java.util.List;

public class MusicianService implements IMusicianService {

    private final MusicianDAO dataSource;

    public MusicianService(MusicianDAO dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getAllMusicians() {
        return dataSource.getAllMusicians();
    }

    @Override
    public boolean exists(String musician) {
        return dataSource.exists(musician);
    }
}
