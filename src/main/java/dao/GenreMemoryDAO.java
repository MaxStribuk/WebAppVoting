package dao;

import dao.api.GenreDAO;
import dto.GenreDTO;

import java.util.List;

public class GenreMemoryDAO implements GenreDAO {

    private static volatile GenreMemoryDAO instance = null;

    private final List<GenreDTO> genres = List.of(
            new GenreDTO(1,"Pop"),
            new GenreDTO(2,"Rap"),
            new GenreDTO(3,"Techno"),
            new GenreDTO(4,"Dubstep"),
            new GenreDTO(5,"Jazz"),
            new GenreDTO(6,"Classic Rock"),
            new GenreDTO(7,"Country"),
            new GenreDTO(8,"Hard Rock"),
            new GenreDTO(9,"Blues"),
            new GenreDTO(10,"Hip Hop")
    );

    private GenreMemoryDAO() {
    }

    public static GenreMemoryDAO getInstance() {
        if (instance == null) {
            synchronized (GenreMemoryDAO.class) {
                if (instance == null) {
                    instance = new GenreMemoryDAO();
                }
            }
        }
        return instance;
    }

    @Override
    //no list? List.of?
    public List<GenreDTO> getAllGenres() {
        return genres;
    }

    @Override
    public boolean exists(String genre) {
        for (GenreDTO genreDTO : genres) {
            if(genre.equals(genreDTO.getGenre())){
                return true;
            }
        }
        return false;
    }
}
