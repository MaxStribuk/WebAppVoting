package dao.api;

import java.util.List;

public interface GenreDAO {

    List<String> getAllGenres();
    boolean exists(String genre);
}
