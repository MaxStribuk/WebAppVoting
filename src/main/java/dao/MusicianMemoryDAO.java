package dao;

import dao.api.MusicianDAO;

import java.util.List;

public class MusicianMemoryDAO implements MusicianDAO {

    private static volatile MusicianMemoryDAO instance = null;
    private final List<String> musicians = List.of("Taylor Swift",
            "Prince",
            "Elvis Presley",
            "Eminem");

    private MusicianMemoryDAO() {
    }

    public static MusicianMemoryDAO getInstance() {
        if (instance == null) {
            synchronized (MusicianMemoryDAO.class) {
                if (instance == null) {
                    instance = new MusicianMemoryDAO();
                }
            }
        }
        return instance;
    }
    @Override
    public List<String> getAllMusicians() {
        return musicians;
    }

    @Override
    public boolean exists(String musician) {
        return musicians.contains(musician);
    }
}
