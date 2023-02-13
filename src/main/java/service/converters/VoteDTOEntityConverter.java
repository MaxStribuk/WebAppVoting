package service.converters;

import dao.entity.ArtistEntity;
import dao.entity.GenreEntity;
import dao.entity.VoteEntity;
import dto.request.VoteDTORequest;
import service.api.IConvertable;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class VoteDTOEntityConverter
        implements IConvertable<VoteDTORequest, VoteEntity> {

    @Override
    public VoteEntity convert(VoteDTORequest vote) {
        return new VoteEntity(
                new ArtistEntity(vote.getArtistId()),
                vote.getGenreIds()
                        .stream()
                        .map(GenreEntity::new)
                        .collect(Collectors.toList()),
                vote.getAbout(),
                LocalDateTime.now(),
                vote.getEmail()
        );
    }
}