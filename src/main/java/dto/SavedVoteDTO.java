package dto;

import java.time.LocalDateTime;

public class SavedVoteDTO {
    private VoteDTO voteDTO;
    private LocalDateTime creationTime;

    public SavedVoteDTO(VoteDTO voteDTO, LocalDateTime localDateTime) {
        this.voteDTO = voteDTO;
        this.creationTime = localDateTime;
    }

    public SavedVoteDTO(VoteDTO voteDTO) {
        this.voteDTO = voteDTO;
        creationTime = LocalDateTime.now();
    }

    public VoteDTO getVoteDTO() {
        return voteDTO;
    }

    public LocalDateTime getCreateDataTime() {
        return creationTime;
    }
}
