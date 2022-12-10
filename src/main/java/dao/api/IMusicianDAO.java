package dao.api;

import dto.MusicianDTO;

import java.util.List;

public interface IMusicianDAO {

    List<MusicianDTO> getAll();

    boolean exists(int id);

    MusicianDTO geMusicianById(int id);
}
