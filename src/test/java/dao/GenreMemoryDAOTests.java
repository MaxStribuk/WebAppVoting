package dao;

import dao.api.IGenreDAO;
import dao.factories.ArtistDAOSingleton;
import dao.factories.GenreDAOSingleton;
import dto.ArtistDTO;
import dto.GenreDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenreMemoryDAOTests {

    private IGenreDAO dao;
    private List<GenreDTO> expected;

    @BeforeAll
    public void setup() {
        dao = GenreDAOSingleton.getInstance();
        expected = List.of(
                new GenreDTO(1, "Pop"),
                new GenreDTO(2, "Rap"),
                new GenreDTO(3, "Techno"),
                new GenreDTO(4, "Dubstep"),
                new GenreDTO(5, "Jazz"),
                new GenreDTO(6, "Classic Rock"),
                new GenreDTO(7, "Country"),
                new GenreDTO(8, "Hard Rock"),
                new GenreDTO(9, "Blues"),
                new GenreDTO(10, "Hip Hop")
        );
    }

    @Test
    @DisplayName("Testing if getAll method of GenreDAO returns the correct list: ")
    public void testGetAll() {

        List<GenreDTO> actual = dao.getAll();

        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    @DisplayName("Testing if exists method of GenreDAO returns correct boolean values: ")
    public void testExists() {
        assertTrue(dao.exists(1));
        assertTrue(dao.exists(4));
        assertTrue(dao.exists(9));

        assertFalse(dao.exists(0));
        assertFalse(dao.exists(11));
        assertFalse(dao.exists(-10));
    }

    @Test
    @DisplayName("Testing if get method of GenreDAO returns correct GenreDTO objects: ")
    public void testGet() {
        assertEquals(expected.get(0), dao.get(1));
        assertEquals(expected.get(3), dao.get(4));
        assertEquals(expected.get(9), dao.get(10));

        assertNotEquals(new GenreDTO(6, "Rock'N'Roll"), dao.get(6));
        assertNotEquals(new GenreDTO(1, "Pop Music"), dao.get(1));
    }
}
