package service.api;

import java.util.List;

public interface IMusicianService {

    List<String> getAllMusicians();
    boolean exists(String musician);
}
