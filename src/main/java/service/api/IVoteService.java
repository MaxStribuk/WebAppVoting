package service.api;

import dto.response.VoteDTOResponse;
import dto.request.VoteDTORequest;

import java.util.List;

public interface IVoteService {

    List<VoteDTOResponse> getAll();

    void save(VoteDTORequest vote);
}