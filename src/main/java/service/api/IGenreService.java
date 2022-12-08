package service.api;

import java.util.List;

public interface IGenreService {

    List<String> getAllGenres();
    boolean exists(String genre);
}
