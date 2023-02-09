package service.converters;

import dao.entity.ArtistEntity;
import dto.ArtistDTO;
import service.api.IConvertable;

public class ArtistDTOEntityConverter
        implements IConvertable<ArtistDTO, ArtistEntity> {

    @Override
    public ArtistEntity convert(ArtistDTO artist) {
        return new ArtistEntity(
                artist.getId(),
                artist.getName()
        );
    }
}