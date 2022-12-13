package dao;

import dao.api.IArtistDAO;
import dto.ArtistDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistMemoryDAO implements IArtistDAO {
    private final Map<Integer, ArtistDTO> artists;

    public ArtistMemoryDAO() {
        artists = new HashMap<>();
        artists.put(1, new ArtistDTO(1, "Taylor Swift"));
        artists.put(2, new ArtistDTO(2, "Prince"));
        artists.put(3, new ArtistDTO(3, "Elvis Presley"));
        artists.put(4, new ArtistDTO(4, "Eminem"));
    }

    @Override
    public List<ArtistDTO> getAll() {
        return List.copyOf(artists.values());
    }

    @Override
    public boolean exists(int id) {
        return artists.containsKey(id);
    }

    @Override
    public ArtistDTO get(int id) {
        return artists.get(id);
    }
}