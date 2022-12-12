package service.api;

import dto.ArtistDTO;

import java.util.List;

public interface IArtistService {

    List<ArtistDTO> getAll();

    ArtistDTO getArtistById(int id);

    boolean exists(int id);
}