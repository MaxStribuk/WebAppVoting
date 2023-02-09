package service.converters;

import dao.entity.ArtistEntity;
import dto.ArtistDTO;
import service.api.IConvertable;

public class ArtistEntityDTOConverter
        implements IConvertable <ArtistEntity, ArtistDTO> {

    @Override
    public ArtistDTO convert(ArtistEntity artist) {
        if (artist == null) {
            throw new IllegalArgumentException("the artist with the specified id " +
                    "does not exist");
        }
        return new ArtistDTO(
            artist.getId(),
            artist.getArtist()
        );
    }
}