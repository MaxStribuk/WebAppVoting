package dao;

import dao.api.IGenreDAO;
import dto.GenreDTO;

import java.util.List;

public class GenreDAO implements IGenreDAO {

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

    public GenreDAO() {
    }

    @Override
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
