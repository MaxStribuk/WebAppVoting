package service.api;

import dto.MusicianDTO;

import java.util.List;

public interface IMusicianService {

    List<MusicianDTO> getAll();
    boolean exists(MusicianDTO musician);
}
