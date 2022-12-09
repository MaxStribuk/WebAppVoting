package dao;

import dao.api.MusicianDAO;
import dto.GenreDTO;
import dto.MusicianDTO;

import java.util.List;

public class MusicianMemoryDAO implements MusicianDAO {

    private static volatile MusicianMemoryDAO instance = null;
    private final List<MusicianDTO> musicians = List.of(
            new MusicianDTO(1,"Taylor Swift"),
            new MusicianDTO(2,"Prince"),
            new MusicianDTO(3,"Elvis Presley"),
            new MusicianDTO(4,"Eminem")
                    );

    private MusicianMemoryDAO() {
    }

    public static MusicianMemoryDAO getInstance() {
        if (instance == null) {
            synchronized (MusicianMemoryDAO.class) {
                if (instance == null) {
                    instance = new MusicianMemoryDAO();
                }
            }
        }
        return instance;
    }
    @Override
    public List<MusicianDTO> getAllMusicians() {
        return musicians;
    }

    @Override
    public boolean exists(String musician) {
        List<MusicianDTO> musicianDTOS = getAllMusicians();
        for (MusicianDTO musicianDTO: musicianDTOS) {
            if(musician.equals(musicianDTO.getMusician())){
                return true;
            }
        }
        return false;
    }
}
