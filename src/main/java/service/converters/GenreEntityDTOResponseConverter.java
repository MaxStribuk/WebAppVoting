package service.converters;

import dao.entity.GenreEntity;
import dto.response.GenreDTOResponse;
import service.api.IConvertable;

public class GenreEntityDTOResponseConverter
        implements IConvertable<GenreEntity, GenreDTOResponse> {

    @Override
    public GenreDTOResponse convert(GenreEntity genre) {
        if (genre == null) {
            throw new IllegalArgumentException("the genre with the specified id " +
                    "does not exist");
        }
        return new GenreDTOResponse(
                genre.getId(),
                genre.getTitle(),
                genre.getVersion()
        );
    }
}
