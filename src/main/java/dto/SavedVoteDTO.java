package dto;

import java.time.LocalDateTime;

public class SavedVoteDTO {
    private VoteDTO voteDTO;
    private LocalDateTime createDataTime;

    public SavedVoteDTO(VoteDTO voteDTO, LocalDateTime localDateTime) {
        this.voteDTO = voteDTO;
        this.createDataTime = localDateTime;
    }

    public SavedVoteDTO(VoteDTO voteDTO) {
        this.voteDTO = voteDTO;
        createDataTime =LocalDateTime.now();
    }

    public VoteDTO getVoteDTO() {
        return voteDTO;
    }

    public LocalDateTime getCreateDataTime() {
        return createDataTime;
    }
}
