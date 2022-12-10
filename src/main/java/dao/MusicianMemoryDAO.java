package dao;

import dao.api.IMusicianDAO;
import dto.MusicianDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicianMemoryDAO implements IMusicianDAO {
    private final Map<Integer, MusicianDTO> musicians;

    public MusicianMemoryDAO() {
        musicians = new HashMap<>();
        musicians.put(1, new MusicianDTO(1, "Taylor Swift"));
        musicians.put(2, new MusicianDTO(2, "Prince"));
        musicians.put(3, new MusicianDTO(3, "Elvis Presley"));
        musicians.put(4, new MusicianDTO(4, "Eminem"));
    }

    @Override
    public List<MusicianDTO> getAll() {
        return new ArrayList<>(musicians.values());
    }

    @Override
    public boolean exists(int id) {
        return musicians.containsKey(id);
    }

    @Override
    public MusicianDTO geMusicianById(int id) {
        return musicians.get(id);
    }
}
