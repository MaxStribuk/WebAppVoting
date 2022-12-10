package dao;

import dao.api.IMusicianDAO;
import dto.MusicianDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicianMemoryDAO implements IMusicianDAO {
    private final List<MusicianDTO> musicians;

    public MusicianMemoryDAO() {
        musicians = new ArrayList<>();
        musicians.add(new MusicianDTO(1,"Taylor Swift"));
        musicians.add(new MusicianDTO(2,"Prince"));
        musicians.add(new MusicianDTO(3,"Elvis Presley"));
        musicians.add(new MusicianDTO(4,"Eminem"));
    }
    @Override
    public List<MusicianDTO> getAll() {
        return Collections.unmodifiableList(musicians);
    }

    @Override
    public boolean exists(MusicianDTO musician) {
        return musicians.contains(musician);
    }
}
