package service.converters;

import dao.entity.GenreEntity;
import dto.GenreDTO;
import service.api.IConvertable;

public class GenreEntityDTOConverter
        implements IConvertable<GenreEntity, GenreDTO> {

    @Override
    public GenreDTO convert(GenreEntity genre) {
        if (genre == null) {
            throw new IllegalArgumentException("the genre with the specified id " +
                    "does not exist");
        }
        return new GenreDTO(
            genre.getId(),
            genre.getTitle()
        );
    }
}
