package dao;

import dao.api.IMusicianDAO;
import dto.MusicianDTO;

import java.util.List;

public class MusicianDAO implements IMusicianDAO {
    private final List<MusicianDTO> musicians = List.of(
            new MusicianDTO(1,"Taylor Swift"),
            new MusicianDTO(2,"Prince"),
            new MusicianDTO(3,"Elvis Presley"),
            new MusicianDTO(4,"Eminem")
                    );

    public MusicianDAO() {
    }
    @Override
    public List<MusicianDTO> getAllMusicians() {
        return musicians;
    }

    @Override
    public boolean exists(String musician) {
        for (MusicianDTO musicianDTO: musicians) {
            if(musician.equals(musicianDTO.getMusician())){
                return true;
            }
        }
        return false;
    }
}
