package service.api;

import dto.request.GenreDTORequest;
import dto.response.GenreDTOResponse;

import java.util.List;

public interface IGenreService {

    List<GenreDTOResponse> getAll();

    GenreDTOResponse get(long id);

    boolean exists(long id);

    void add(GenreDTORequest genre);

    void delete(long id);

    void update(Long id, GenreDTORequest genre);
}