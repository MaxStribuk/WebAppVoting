package service.api;

import dto.request.ArtistDTORequest;
import dto.response.ArtistDTOResponse;

import java.util.List;

public interface IArtistService {

    List<ArtistDTOResponse> getAll();

    ArtistDTOResponse get(long id);

    void add(ArtistDTORequest artist);

    void update(long id, long version, ArtistDTORequest artist);

    void delete(long id, long version);
}