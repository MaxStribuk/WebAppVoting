package dao.api;

import dto.MusicianDTO;

import java.util.List;

public interface MusicianDAO {

    List<MusicianDTO> getAllMusicians();
    boolean exists(String musician);
}
