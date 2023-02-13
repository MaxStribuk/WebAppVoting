package service.api;

import dto.request.ArtistDTORequest;
import dto.response.ArtistDTOResponse;

import java.util.List;

public interface IArtistService {

    List<ArtistDTOResponse> getAll();

    ArtistDTOResponse get(long id);

    boolean exists(long id);

    void add(ArtistDTORequest artist);

    void update(long id, ArtistDTORequest artist);

    void delete(long id);
}