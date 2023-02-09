package service.converters;

import dao.entity.GenreEntity;
import dto.GenreDTO;
import service.api.IConvertable;

public class GenreDTOEntityConverter
        implements IConvertable<GenreDTO, GenreEntity> {

    @Override
    public GenreEntity convert(GenreDTO genre) {
        return new GenreEntity(
                genre.getId(),
                genre.getTitle()
        );
    }
}