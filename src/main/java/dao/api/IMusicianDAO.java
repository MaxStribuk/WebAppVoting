package dao.api;

import dto.MusicianDTO;

import java.util.List;

public interface IMusicianDAO {

    List<MusicianDTO> getAllMusicians();
    boolean exists(String musician);
}
