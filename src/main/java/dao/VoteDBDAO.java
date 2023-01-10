package dao;

import dao.api.IGenreDAO;
import dao.api.IVoteDAO;
import dao.util.ConnectionManager;
import dto.SavedVoteDTO;
import dto.VoteDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VoteDBDAO implements IVoteDAO {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS app.votes( " +
            "  id BIGSERIAL, " +
            "  artist_id INT NOT NULL, " +
            "  about TEXT NOT NULL, " +
            "  creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL, " +
            "  CONSTRAINT pk_vote_id PRIMARY KEY (id) " +
            ");";
    private static final String CREATE_JOIN_TABLE = "CREATE TABLE IF NOT EXISTS app.votes_genres( " +
            "  vote_id BIGINT NOT NULL, " +
            "  genre_id INT NOT NULL, " +
            "  CONSTRAINT fk_vote_id FOREIGN KEY (vote_id) " +
            "  REFERENCES app.votes (id), " +
            "  CONSTRAINT fk_genre_id FOREIGN KEY (genre_id) " +
            "  REFERENCES app.genres (id), " +
            "  CONSTRAINT unique_vote UNIQUE(vote_id, genre_id) " +
            ");";

    private static final String GENRE_NUM_CHECK = "CREATE OR REPLACE FUNCTION " +
            "check_vote_genres_count() RETURNS TRIGGER AS $$ " +
            "DECLARE " +
            "    var_vote_id INTEGER; " +
            "    vote_genres_count INTEGER; " +
            "BEGIN " +
            "    var_vote_id := NEW.vote_id; " +
            "    SELECT COUNT(*) INTO vote_genres_count FROM app.votes_genres " +
            "WHERE vote_id = var_vote_id; " +
            "    IF vote_genres_count > 5 THEN " +
            "        RAISE EXCEPTION 'A vote can have a maximum of 5 genres.'; " +
            "    ELSIF vote_genres_count < 3 THEN " +
            "        RAISE EXCEPTION 'A vote must have at least 3 genres.'; " +
            "    END IF; " +
            "    RETURN NEW; " +
            "END; " +
            "$$ LANGUAGE plpgsql;";

    private static final String GENRE_NUM_TRIGGER = "CREATE TRIGGER check_vote_genres_count " +
            "AFTER INSERT OR UPDATE ON app.votes_genres " +
            "FOR EACH ROW " +
            "EXECUTE FUNCTION check_vote_genres_count();";

    private static final String SELECT_ALL = "SELECT id, artist_id, about, creation_time " +
            "FROM app.votes; ;";
    private static final String SELECT_GENRES = "SELECT vg.genre_id AS id " +
            "FROM app.votes AS v " +
            "  INNER JOIN app.votes_genres AS vg " +
            "  ON v.id = vg.vote_id " +
            "WHERE v.id = ?;";
    private static final String SAVE_VOTE = "INSERT INTO app.votes (" +
            "artist_id, about, creation_time) " +
            "VALUES (?, ?, ?);";
    private static final String SAVE_GENRE_VOTE = "INSERT INTO app.votes_genres (" +
            "vote_id, genre_id) " +
            "VALUES (2, 2), (2, 3), (2, 4);";


    public VoteDBDAO() {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement create = connection.prepareStatement(CREATE_TABLE);
             PreparedStatement createJoin = connection.prepareStatement(CREATE_JOIN_TABLE);
             PreparedStatement createGenreFunction = connection.prepareStatement(GENRE_NUM_CHECK);
             PreparedStatement createGenreTrigger = connection.prepareStatement(GENRE_NUM_TRIGGER)) {

            connection.setAutoCommit(false);

            create.execute();
            createJoin.execute();
            //createGenreFunction.execute();
            //createGenreTrigger.execute();

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<SavedVoteDTO> getAll() {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement getAll = connection.prepareStatement(SELECT_ALL);
             PreparedStatement getGenres = connection.prepareStatement(SELECT_GENRES);
             ResultSet voteResults = getAll.executeQuery()) {

            List<SavedVoteDTO> votes = new ArrayList<>();
            int id;
            int artistID;
            LocalDateTime time;
            String about;
            VoteDTO vote;

            while (voteResults.next()) {
                id = getID(voteResults);
                getGenres.setInt(1, id);
                List<Integer> genreIDs = new ArrayList<>();
                try (ResultSet genreResults = getGenres.executeQuery()) {
                    while (genreResults.next()) {
                        genreIDs.add(getGenreID(genreResults));
                    }
                }
                artistID = getArtistID(voteResults);
                time = getTime(voteResults);
                about = getAbout(voteResults);
                vote = new VoteDTO(artistID, genreIDs, about);
                votes.add(new SavedVoteDTO(vote, time));
            }
            return votes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(SavedVoteDTO vote) {
        try (Connection connection = ConnectionManager.open()) {

            connection.setAutoCommit(false);

            try (PreparedStatement saveVote = connection.prepareStatement(SAVE_VOTE,
                    Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement saveGenreVote = connection.prepareStatement(SAVE_GENRE_VOTE)) {

                VoteDTO innerVote = vote.getVoteDTO();
                saveVote.setInt(1, innerVote.getArtistId());
                saveVote.setString(2, innerVote.getAbout());
                saveVote.setObject(3, vote.getCreateDataTime());
                saveVote.execute();

                List<Integer> genres = innerVote.getGenreIds();
                int voteID;
                try (ResultSet generatedID = saveVote.getGeneratedKeys()) {
                    if (generatedID.next()) {
                        voteID = generatedID.getInt(1);
                    } else {
                        throw new IllegalArgumentException("Failed to save the vote");
                    }
                }

                for (int i = 0; i < genres.size(); i++) {
                    saveGenreVote.setInt(1, voteID);
                    saveGenreVote.setInt(2, genres.get(i));
                    saveGenreVote.execute();
                }

                connection.commit();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        IGenreDAO genre_dao = new GenreDBDAO();
        IVoteDAO dao = new VoteDBDAO();
        dao.save(new SavedVoteDTO(new VoteDTO(1, List.of(1,2,3,4), "NoText"), LocalDateTime.now()));
    }
    private int getID(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(1);
    }

    private int getGenreID(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(1);
    }
    private int getArtistID(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(2);
    }

    private String getAbout(ResultSet resultSet) throws SQLException {
        return resultSet.getString(3);
    }

    private LocalDateTime getTime(ResultSet resultSet) throws SQLException {
        return resultSet.getObject(4, LocalDateTime.class);
    }
}
