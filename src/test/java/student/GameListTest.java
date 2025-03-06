package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class GameListTest {
    @TempDir
    static Path tempDir;

    public Set<BoardGame> games;

    @BeforeEach
    void setUp() {
        games = new TreeSet<BoardGame>(Sorts.getSortType(GameData.NAME, true));
        games.add(new BoardGame("Name", 1, 5, 10, 30, 60,
                1, 15, 3, 2023));
        games.add(new BoardGame("Name2", 2, 5, 10, 30, 60,
                1, 21, 3, 2011));
        games.add(new BoardGame("Name3", 3, 10, 20, 100, 200,
                10, 1, 9, 2000));
    }

    @Test
    void testGetGameNames() {
        IGameList list1 = new GameList();
        list1.addToList("1", games.stream());
        assertEquals("[Name]", list1.getGameNames().toString());
        list1.addToList("2", games.stream());
        assertEquals("[Name, Name2]", list1.getGameNames().toString());
    }

    @Test
    void testClear() {
        IGameList list1 = new GameList();
        list1.addToList("all", games.stream());
        assertEquals(3, list1.count());
        list1.clear();
        assertEquals(0, list1.count());
    }

    @Test
    void testCount() {
        IGameList list1 = new GameList();
        list1.addToList("1", games.stream());
        assertEquals(1, list1.count());
        list1.addToList("2-3", games.stream());
        assertEquals(3, list1.count());
    }

    @Test
    void testSaveGame() throws IOException {
        // get the path of the games_list.txt
        Path gamesList = tempDir.resolve("games_list.txt");
        IGameList list1 = new GameList();
        list1.addToList("all", games.stream());
        list1.saveGame(gamesList.toString());       // Use the correct path
        // Expected result
        String expectedResult = String.join("\n", "Name", "Name2", "Name3");
        // Read file content
        String actualResult = Files.readString(gamesList).trim();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testAddSingleGameToListByName() {
        IGameList list1 = new GameList();
        list1.addToList("Name2", games.stream());
        assertEquals(1, list1.count());
        assertEquals("[Name2]", list1.getGameNames().toString());
    }

    @Test
    void testAddSingleGameToListByIndex() {
        IGameList list1 = new GameList();
        list1.addToList("1", games.stream());
        assertEquals(1, list1.count());
        assertEquals("[Name]", list1.getGameNames().toString());
        list1.addToList("2", games.stream());
        assertEquals(2, list1.count());
        assertEquals("[Name, Name2]", list1.getGameNames().toString());
    }

    @Test
    void testAddRangeOfGamesToListByIndex() {
        IGameList list1 = new GameList();
        list1.addToList("1-2", games.stream());
        assertEquals(2, list1.count());
        assertEquals("[Name, Name2]", list1.getGameNames().toString());
    }

    @Test
    void testAddAllGamesToList() {
        IGameList list1 = new GameList();
        list1.addToList("all", games.stream());
        assertEquals(3, list1.count());
        assertEquals("[Name, Name2, Name3]", list1.getGameNames().toString());
    }

    @Test
    void testRemoveSingleGameFromListByName() {
        IGameList list1 = new GameList();
        list1.addToList("Name2", games.stream());
        list1.removeFromList("Name2");
        assertEquals(0, list1.count());
    }

    @Test
    void testRemoveSingleGameFromListByIndex() {
        IGameList list1 = new GameList();
        list1.addToList("Name2", games.stream());
        list1.removeFromList("1");
        assertEquals(0, list1.count());
    }

    @Test
    void testRemoveRangeOfGamesFromListByIndex() {
        IGameList list1 = new GameList();
        list1.addToList("1-3", games.stream());
        list1.removeFromList("1-2");
        assertEquals(1, list1.count());
    }

    @Test
    void testRemoveAllGamesFromList() {
        IGameList list1 = new GameList();
        list1.addToList("all", games.stream());
        list1.removeFromList("all");
        assertEquals(0, list1.count());
    }
}