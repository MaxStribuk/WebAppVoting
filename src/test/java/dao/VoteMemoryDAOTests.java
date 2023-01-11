package dao;

import dao.api.IVoteDAO;
import dao.factories.DAOType;
import dao.factories.VoteDAOSingleton;
import dto.SavedVoteDTO;
import dto.VoteDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VoteMemoryDAOTests {
    private IVoteDAO dao;
    private List<SavedVoteDTO> expected;

    @BeforeAll
    public void setUp() {
        dao = VoteDAOSingleton.getInstance(DAOType.MEMORY);
        expected = new ArrayList<>();
    }

    @Test
    @DisplayName("Testing if the getAll method of VoteDAO returns the correct list")
    public void testGetAll() {
        assertEquals(expected, dao.getAll());
    }

    @Test
    @DisplayName("Testing the save method of VoteDAO for a single input")
    public void testSave1() {
        SavedVoteDTO vote = new SavedVoteDTO(new VoteDTO(1,
                Collections.singletonList(2), "test vote"));
        dao.save(vote);
        expected.add(vote);
        assertEquals(expected, dao.getAll());
    }

    @Test
    @DisplayName("Testing the save method of VoteDAO for multiple inputs")
    public void testSave2() {
        SavedVoteDTO vote1 = new SavedVoteDTO(new VoteDTO(1,
                Collections.singletonList(2), "test vote 1"));
        SavedVoteDTO vote2 = new SavedVoteDTO(new VoteDTO(3,
                Collections.singletonList(4), "test vote 2"));
        dao.save(vote1);
        dao.save(vote2);
        expected.add(vote1);
        expected.add(vote2);
        assertEquals(expected, dao.getAll());
    }
}
