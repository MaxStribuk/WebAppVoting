package service.converters;

import dao.entity.GenreEntity;
import dao.entity.VoteEntity;
import dto.SavedVoteDTO;
import dto.VoteDTO;
import service.api.IConvertable;

import java.util.stream.Collectors;

public class VoteEntitySavedDTOConverter
        implements IConvertable<VoteEntity, SavedVoteDTO> {

    @Override
    public SavedVoteDTO convert(VoteEntity voteEntity) {
        if (voteEntity == null) {
            throw new IllegalArgumentException("the vote with the specified id " +
                    "does not exist");
        }
        return new SavedVoteDTO(
                new VoteDTO(
                        voteEntity.getArtist().getId(),
                        voteEntity.getGenres()
                                .stream()
                                .map(GenreEntity::getId)
                                .collect(Collectors.toList()),
                        voteEntity.getAbout(),
                        voteEntity.getEmail()
                ), voteEntity.getCreationTime()
        );
    }
}