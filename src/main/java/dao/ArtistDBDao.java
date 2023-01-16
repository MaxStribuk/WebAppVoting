package dao;

import dao.api.IArtistDAO;
import dao.util.ConnectionManager;
import dto.ArtistDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDBDao implements IArtistDAO {

    private static final String GET_ALL = "SELECT id, name FROM app.artist;";
    private static final String GET = "SELECT id, name FROM app.artist " +
            "WHERE id = ?;";
    private static final String ADD = "INSERT INTO app.artist (name) VALUES (?);";
    private static final String UPDATE = "UPDATE app.artist SET name=? WHERE id=?;";
    private static final String COUNT_VOTES = "SELECT COUNT(id) AS count FROM app.votes " +
            "GROUP BY artist_id HAVING artist_id=?;";
    private static final String DELETE = "DELETE FROM app.artist WHERE id=?;";

    @Override
    public List<ArtistDTO> getAll() {
        try (Connection conn = ConnectionManager.open();
             PreparedStatement stmt = conn.prepareStatement(GET_ALL,
                     ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
             ResultSet artists = stmt.executeQuery()) {

            List<ArtistDTO> artistDTOs = new ArrayList<>();
            while (artists.next()) {
                artistDTOs.add(get(artists));
            }
            return artistDTOs;
        } catch (SQLException e) {
            throw new RuntimeException("database query error");
        }
    }

    @Override
    public boolean exists(int id) {
        try {
            get(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public ArtistDTO get(int id) {
        try (Connection conn = ConnectionManager.open();
             PreparedStatement stmt = conn.prepareStatement(GET,
                     ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {

            stmt.setInt(1, id);
            try (ResultSet artist = stmt.executeQuery()) {
                if (artist.first()) {
                    return get(artist);
                } else {
                    throw new IllegalArgumentException("artist with the" +
                            " specified id does not exist");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("database query error");
        }
    }

    @Override
    public void add(String artist) {
        try (Connection conn = ConnectionManager.open();
             PreparedStatement statement = conn.prepareStatement(ADD)) {
            statement.setString(1, artist);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, String artist) {
        try (Connection conn = ConnectionManager.open();
             PreparedStatement statement = conn.prepareStatement(UPDATE)) {
            statement.setString(1, artist);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = ConnectionManager.open();
             PreparedStatement statement = conn.prepareStatement(COUNT_VOTES)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.getInt("count") == 0) {
                    try (PreparedStatement delStatement = conn.prepareStatement(DELETE)) {
                        delStatement.setInt(1, id);
                        delStatement.executeUpdate();
                    }
                } else {
                    throw new IllegalArgumentException("artist can't be deleted: he has votes");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArtistDTO get(ResultSet artist) throws SQLException {
        return new ArtistDTO(
                artist.getInt("id"),
                artist.getString("name"));
    }
}