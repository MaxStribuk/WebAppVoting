package service;

import dao.api.IArtistDAO;
import dao.entity.ArtistEntity;
import dto.ArtistDTO;
import service.api.IArtistService;
import service.api.IConvertable;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistService implements IArtistService {

    private final IArtistDAO dataSource;
    private final IConvertable<ArtistDTO, ArtistEntity> artistDTOEntityConverter;
    private final IConvertable<ArtistEntity, ArtistDTO> artistEntityDTOConverter;

    public ArtistService(IArtistDAO dataSource,
                         IConvertable<ArtistDTO, ArtistEntity> artistDTOEntityConverter,
                         IConvertable<ArtistEntity, ArtistDTO> artistEntityDTOConverter) {
        this.dataSource = dataSource;
        this.artistDTOEntityConverter = artistDTOEntityConverter;
        this.artistEntityDTOConverter = artistEntityDTOConverter;
    }

    @Override
    public List<ArtistDTO> getAll() {
        return dataSource.getAll()
                .stream()
                .map(artistEntityDTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(long id) {
        return dataSource.exists(id);
    }

    @Override
    public ArtistDTO get(long id) {
        return artistEntityDTOConverter.convert(
                dataSource.get(id));
    }

    @Override
    public void add(ArtistDTO artist) {
        dataSource.add(
                artistDTOEntityConverter.convert(artist));
    }

    @Override
    public void update(ArtistDTO artist) {
        long id = artist.getId();
        if (dataSource.exists(id)) {
            dataSource.update(
                    artistDTOEntityConverter.convert(artist));
        } else {
            throw new IllegalArgumentException("No artist updated for id " + id);
        }
    }

    @Override
    public void delete(long id) {
        if (dataSource.exists(id)) {
            dataSource.delete(id);
        } else {
            throw new IllegalArgumentException("No artist deleted for id " + id);
        }
    }
}