package service;

import dao.api.IGenreDAO;
import dao.entity.GenreEntity;
import dto.GenreDTO;
import service.api.IConvertable;
import service.api.IGenreService;

import java.util.List;
import java.util.stream.Collectors;

public class GenreService implements IGenreService {

    private final IGenreDAO dataSource;
    private final IConvertable<GenreDTO, GenreEntity> genreDTOEntityConverter;
    private final IConvertable<GenreEntity,GenreDTO> genreEntityDTOConverter;

    public GenreService(IGenreDAO dataSource,
                        IConvertable<GenreDTO, GenreEntity> genreDTOEntityConverter,
                        IConvertable<GenreEntity, GenreDTO> genreEntityDTOConverter) {
        this.dataSource = dataSource;
        this.genreDTOEntityConverter = genreDTOEntityConverter;
        this.genreEntityDTOConverter = genreEntityDTOConverter;
    }

    @Override
    public List<GenreDTO> getAll() {
        return dataSource.getAll()
                .stream()
                .map(genreEntityDTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDTO get(long id) {
        return genreEntityDTOConverter.convert(
                dataSource.get(id));
    }

    @Override
    public boolean exists(long id) {
        return dataSource.exists(id);
    }

    @Override
    public void add(GenreDTO genre) {
        boolean isDuplicate = isDuplicate(genre.getTitle());
        if (isDuplicate) {
            throw new IllegalArgumentException("This genre has already " +
                    "been added");
        } else {
            dataSource.add(genreDTOEntityConverter.convert(genre));
        }
    }

    @Override
    public void update(GenreDTO genre) {
        long id = genre.getId();
        boolean isDuplicate = isDuplicate(genre.getTitle());
        if (dataSource.exists(id) && !isDuplicate) {
            dataSource.update(genreDTOEntityConverter.convert(genre));
        } else {
            throw new IllegalArgumentException("No genre updated for id " +
                    id + " or genre has already been added");
        }
    }

    @Override
    public void delete(long id) {
        if (dataSource.exists(id)) {
            dataSource.delete(id);
        } else {
            throw new IllegalArgumentException("No genre deleted for id " + id);
        }
    }

    private boolean isDuplicate(String genre){
        return getAll().stream()
                .map(GenreDTO::getTitle)
                .anyMatch(nameGenre -> nameGenre.equalsIgnoreCase(genre));
    }
}