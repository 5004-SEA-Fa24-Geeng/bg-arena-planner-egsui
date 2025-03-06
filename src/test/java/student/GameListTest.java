package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameListTest {
    public Set<BoardGame> games;

    @BeforeEach
    void setUp() {
        games = new HashSet<>();
        games.add(new BoardGame("Name", 1, 5, 10, 30,60,
                1,2,3,2023));
        games.add(new BoardGame("Name2", 2, 5, 10, 30,60,
                1,2,3,2023));
    }

    @Test
    void getGameNames() {

    }

    @Test
    void clear() {
    }

    @Test
    void count() {
    }

    @Test
    void saveGame() {
    }

    @Test
    void addToList() {
    }

    @Test
    void testAddSingleGameToListByIndex() {
        // String str, Stream<BoardGame> filtered
        IGameList list1 = new GameList();
        list1.addToList("1", games.stream());
        assertEquals(1, list1.count());
        System.out.println(list1.getGameNames());
    }

    @Test
    void addRangeOfGamesToList() {
        IGameList list1 = new GameList();
        list1.addToList("1-3", games.stream());
        // asserts
    }

    @Test
    void removeFromList() {
    }
}