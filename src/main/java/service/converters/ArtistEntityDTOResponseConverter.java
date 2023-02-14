package service.converters;

import dao.entity.ArtistEntity;
import dto.response.ArtistDTOResponse;
import service.api.IConvertable;

public class ArtistEntityDTOResponseConverter
        implements IConvertable<ArtistEntity, ArtistDTOResponse> {

    @Override
    public ArtistDTOResponse convert(ArtistEntity artist) {
        if (artist == null) {
            throw new IllegalArgumentException("the artist with the specified id " +
                    "does not exist");
        }
        return new ArtistDTOResponse(
                artist.getId(),
                artist.getArtist(),
                artist.getVersion()
        );
    }
}