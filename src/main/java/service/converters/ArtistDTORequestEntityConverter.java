package service.converters;

import dao.entity.ArtistEntity;
import dto.request.ArtistDTORequest;
import service.api.IConvertable;

public class ArtistDTORequestEntityConverter
        implements IConvertable<ArtistDTORequest, ArtistEntity> {

    @Override
    public ArtistEntity convert(ArtistDTORequest artist) {
        return new ArtistEntity(artist.getName());
    }
}