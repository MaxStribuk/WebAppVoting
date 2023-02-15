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
        return genreEntityDTOConverter.convert(genreDAO.get(id));
    }

    @Override
    public void add(GenreDTORequest genre) {
        validate(genre);
        boolean isDuplicate = isDuplicate(genre.getTitle());
        if (isDuplicate) {
            throw new IllegalArgumentException("This genre has already been added");
        } else {
            genreDAO.add(genreDTOEntityConverter.convert(genre));
        }
    }

    @Override
    public void update(long id, long version, GenreDTORequest genre) {
        validate(genre);
        boolean isDuplicate = isDuplicate(genre.getTitle());
        GenreEntity genreEntity = genreDAO.get(id);
        if (genreEntity == null) {
            throw new IllegalArgumentException("No genre updated for id " + id);
        }
        if (isDuplicate) {
            throw new IllegalArgumentException("Genre has already been added" + genre);
        }
        if (version == genreEntity.getVersion()) {
            genreEntity.setTitle(genre.getTitle());
            genreDAO.update(genreEntity);
        } else {
            throw new IllegalArgumentException("Version was specified incorrectly");
        }
    }

    @Override
    public void delete(long id, long version) {
        GenreEntity genreEntity = genreDAO.get(id);
        if (genreEntity == null) {
            throw new IllegalArgumentException("No genre deleted for id " + id);
        }
        if (version == genreEntity.getVersion()) {
            genreDAO.delete(id);
        } else {
            throw new IllegalArgumentException("Version was specified incorrectly");
        }
    }

    private boolean isDuplicate(String title) {
        return getAll().stream()
                .map(GenreDTOResponse::getTitle)
                .anyMatch(title::equalsIgnoreCase);
    }

    private void validate(GenreDTORequest genre) {
        if (genre == null
                || genre.getTitle() == null
                || genre.getTitle().isBlank()) {
            throw new IllegalArgumentException("Genre cannot be null");
        }
    }
}