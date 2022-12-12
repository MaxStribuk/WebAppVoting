package service.api;

import dto.MusicianDTO;

import java.util.List;

public interface IMusicianService {

    List<MusicianDTO> getAll();

    MusicianDTO getMusicianById(int id);

    boolean exists(int id);
}
