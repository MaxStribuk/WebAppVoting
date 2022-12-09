package service.api;

import dao.MusicianDAO;
import dto.MusicianDTO;

import java.util.List;

public interface IMusicianService {

    List<MusicianDTO> getAllMusicians();
    boolean exists(MusicianDTO musician);
}
