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

    private static final String GET_ALL = "SELECT id, name FROM app.artist";
    private static final String GET = "SELECT id, name FROM app.artist " +
            "WHERE id = ?";

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

    private ArtistDTO get(ResultSet artist) throws SQLException {
        return new ArtistDTO(
                artist.getInt("id"),
                artist.getString("name"));
    }
}