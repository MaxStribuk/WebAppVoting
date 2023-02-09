package service.converters;

import dao.entity.ArtistEntity;
import dao.entity.GenreEntity;
import dao.entity.VoteEntity;
import dto.SavedVoteDTO;
import dto.VoteDTO;
import service.api.IConvertable;

import java.util.stream.Collectors;

public class VoteSavedDTOEntityConverter
        implements IConvertable<SavedVoteDTO, VoteEntity> {

    @Override
    public VoteEntity convert(SavedVoteDTO savedVoteDTO) {
        VoteDTO voteDTO = savedVoteDTO.getVoteDTO();
        return new VoteEntity(
                new ArtistEntity(voteDTO.getArtistId()),
                voteDTO.getGenreIds()
                        .stream()
                        .map(GenreEntity::new)
                        .collect(Collectors.toList()),
                voteDTO.getAbout(),
                savedVoteDTO.getCreateDataTime(),
                voteDTO.getEmail()
        );
    }
}