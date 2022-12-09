package service;

import dao.api.IMusicianDAO;
import service.api.IMusicianService;

import java.util.List;

public class MusicianService implements IMusicianService {

    private final IMusicianDAO dataSource;

    public MusicianService(IMusicianDAO dataSource) {
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
