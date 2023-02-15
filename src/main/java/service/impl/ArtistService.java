package service.impl;

import dao.api.IArtistDAO;
import dao.entity.ArtistEntity;
import dto.request.ArtistDTORequest;
import dto.response.ArtistDTOResponse;
import service.api.IArtistService;
import service.api.IConvertable;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistService implements IArtistService {

    private final IArtistDAO artistDAO;
    private final IConvertable<ArtistDTORequest, ArtistEntity> artistDTOEntityConverter;
    private final IConvertable<ArtistEntity, ArtistDTOResponse> artistEntityDTOConverter;

    public ArtistService(IArtistDAO artistDAO,
                         IConvertable<ArtistDTORequest, ArtistEntity> artistDTOEntityConverter,
                         IConvertable<ArtistEntity, ArtistDTOResponse> artistEntityDTOConverter) {
        this.artistDAO = artistDAO;
        this.artistDTOEntityConverter = artistDTOEntityConverter;
        this.artistEntityDTOConverter = artistEntityDTOConverter;
    }

    @Override
    public List<ArtistDTOResponse> getAll() {
        return artistDAO.getAll()
                .stream()
                .map(artistEntityDTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ArtistDTOResponse get(long id) {
        return artistEntityDTOConverter.convert(artistDAO.get(id));
    }

    @Override
    public void add(ArtistDTORequest artist) {
        validate(artist);
        artistDAO.add(artistDTOEntityConverter.convert(artist));
    }

    @Override
    public void update(long id, long version, ArtistDTORequest artist) {
        validate(artist);
        ArtistEntity artistEntity = artistDAO.get(id);
        if (artistEntity == null) {
            throw new IllegalArgumentException("No artist updated for id " + id);
        }
        if (version == artistEntity.getVersion()) {
            artistEntity.setArtist(artist.getName());
            artistDAO.update(artistEntity);
        } else {
            throw new IllegalArgumentException("Version was specified incorrectly");
        }
    }

    @Override
    public void delete(long id, long version) {
        ArtistEntity artistEntity = artistDAO.get(id);
        if (artistEntity == null) {
            throw new IllegalArgumentException("No artist deleted for id " + id);
        }
        if (version == artistEntity.getVersion()) {
            artistDAO.delete(id);
        } else {
            throw new IllegalArgumentException("Version was specified incorrectly");
        }
    }

    private void validate(ArtistDTORequest artist) {
        if (artist == null
                || artist.getName() == null
                || artist.getName().isBlank()) {
            throw new IllegalArgumentException("Artist cannot be null");
        }
    }
}