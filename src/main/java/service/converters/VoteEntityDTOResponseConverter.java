package service.converters;

import dao.entity.GenreEntity;
import dao.entity.VoteEntity;
import dto.response.VoteDTOResponse;
import service.api.IConvertable;

import java.util.stream.Collectors;

public class VoteEntityDTOResponseConverter
        implements IConvertable<VoteEntity, VoteDTOResponse> {

    @Override
    public VoteDTOResponse convert(VoteEntity voteEntity) {
        if (voteEntity == null) {
            throw new IllegalArgumentException("the vote with the specified id " +
                    "does not exist");
        }
        return new VoteDTOResponse(
                voteEntity.getArtist().getId(),
                voteEntity.getGenres()
                        .stream()
                        .map(GenreEntity::getId)
                        .collect(Collectors.toList()),
                voteEntity.getAbout(),
                voteEntity.getEmail(),
                voteEntity.getCreationTime()
        );
    }
}