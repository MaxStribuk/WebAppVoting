package service.impl;

import dao.api.IGenreDAO;
import dao.entity.GenreEntity;
import dto.request.GenreDTORequest;
import dto.response.GenreDTOResponse;
import service.api.IConvertable;
import service.api.IGenreService;

import java.util.List;
import java.util.stream.Collectors;

public class GenreService implements IGenreService {

    private final IGenreDAO genreDAO;
    private final IConvertable<GenreDTORequest, GenreEntity> genreDTOEntityConverter;
    private final IConvertable<GenreEntity, GenreDTOResponse> genreEntityDTOConverter;

    public GenreService(IGenreDAO genreDAO,
                        IConvertable<GenreDTORequest, GenreEntity> genreDTOEntityConverter,
                        IConvertable<GenreEntity, GenreDTOResponse> genreEntityDTOConverter) {
        this.genreDAO = genreDAO;
        this.genreDTOEntityConverter = genreDTOEntityConverter;
        this.genreEntityDTOConverter = genreEntityDTOConverter;
    }

    @Override
    public List<GenreDTOResponse> getAll() {
        return genreDAO.getAll()
                .stream()
                .map(genreEntityDTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDTOResponse get(long id) {
        return genreEntityDTOConverter.convert(
                genreDAO.get(id));
    }

    @Override
    public boolean exists(long id) {
        return genreDAO.exists(id);
    }

    @Override
    public void add(GenreDTORequest genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null");
        } else {
            boolean isDuplicate = isDuplicate(genre.getTitle());
            if (isDuplicate) {
                throw new IllegalArgumentException("This genre has already " +
                        "been added");
            } else {
                genreDAO.add(genreDTOEntityConverter.convert(genre));
            }
        }
    }

    @Override
    public void update(Long id, GenreDTORequest genre) {
        boolean isDuplicate = isDuplicate(genre.getTitle());
        if (genreDAO.exists(id) && !isDuplicate) {
            genreDAO.update(id, genreDTOEntityConverter.convert(genre));
        } else {
            throw new IllegalArgumentException("No genre updated for id " +
                    id + " or genre has already been added");
        }
    }

    @Override
    public void delete(long id) {
        if (genreDAO.exists(id)) {
            genreDAO.delete(id);
        } else {
            throw new IllegalArgumentException("No genre deleted for id " + id);
        }
    }

    private boolean isDuplicate(String title) {
        return title != null && getAll().stream()
                .map(GenreDTOResponse::getTitle)
                .anyMatch(title::equalsIgnoreCase);
    }
}