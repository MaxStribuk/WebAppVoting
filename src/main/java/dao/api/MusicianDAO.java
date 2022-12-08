package dao.api;

import java.util.List;

public interface MusicianDAO {

    List<String> getAllMusicians();
    boolean exists(String musician);
}
