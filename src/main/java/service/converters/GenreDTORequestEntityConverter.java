package service.converters;

import dao.entity.GenreEntity;
import dto.request.GenreDTORequest;
import service.api.IConvertable;

public class GenreDTORequestEntityConverter
        implements IConvertable<GenreDTORequest, GenreEntity> {

    @Override
    public GenreEntity convert(GenreDTORequest genre) {
        return new GenreEntity(genre.getTitle());
    }
}