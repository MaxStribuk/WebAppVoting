package service.api;

import dto.ArtistDTO;

import java.util.List;

public interface IArtistService {

    List<ArtistDTO> getAll();

    boolean exists(int id);

    ArtistDTO get(int id);

    void add(String artist);

    void update(int id, String artist);

    void delete(int id);
}