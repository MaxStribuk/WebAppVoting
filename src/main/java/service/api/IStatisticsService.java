package service.api;

import java.util.List;
import java.util.Map;

public interface IStatisticsService {

    Map<String, Integer> getArtistVotes();
    Map<String, Integer> getGenreVotes();
    List<UserMessage> getUserMessages();
    String getStringValue();
}
