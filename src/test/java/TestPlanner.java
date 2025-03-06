import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import student.BoardGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import student.Planner;
import student.IPlanner;
import student.GameData;


/**
 * JUnit test for the Planner class.
 * 
 * Just a sample test to get you started, also using
 * setup to help out. 
 */
public class TestPlanner {
    static Set<BoardGame> games;

    @BeforeAll
    public static void setup() {
        games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));
    }

    @Test
    public void testGetFilteredGamesCount() {
        Planner planner = new Planner(games);
        planner.filter("name==Tucano", GameData.NAME, false);
        assertEquals(1, planner.getFilteredGamesCount());
    }

    @Test
    public void testFilterByNameContains() {
         IPlanner planner = new Planner(games);
         // filter CONTAINS 'Go'
         List<String> filtered2 = planner.filter("name ~= Go").map(BoardGame::getName).toList();
         assertEquals(4, filtered2.size());
         assertEquals("[Go, Go Fish, golang, GoRami]", filtered2.toString());
    }

    @Test
    public void testFilterByNameEquals() {
        IPlanner planner = new Planner(games);
        // filter EQUALS 'Go'
        List<BoardGame> filtered = planner.filter("name == Go").toList();
        assertEquals(1, filtered.size());
        assertEquals("Go", filtered.get(0).getName());
    }

    @Test
    public void testFilterByNameNotEquals() {
        IPlanner planner = new Planner(games);
        // filter NOT_EQUALS to '17 days'
        List<String> filtered3 = planner.filter("name != 17 days").map(BoardGame::getName).toList();
        assertEquals(7, filtered3.size());
        assertEquals("[Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered3.toString());
    }

    @Test
    public void testFilterByNameGreaterThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN_EQUALS, name >= 'G'
        List<String> filtered4 = planner.filter("name >= G").map(BoardGame::getName).toList();
        assertEquals(6, filtered4.size());
        assertEquals("[Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered4.toString());
    }

    @Test
    public void testFilterByNameLessThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN_EQUALS, name <= 'Go'
        List<String> filtered5 = planner.filter("name <= Go").map(BoardGame::getName).toList();
        assertEquals(3, filtered5.size());
        assertEquals("[17 days, Chess, Go]", filtered5.toString());
    }

    @Test
    public void testFilterByNameGreaterThan() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN, name > 'golang'
        List<String> filtered6 = planner.filter("name > golang").map(BoardGame::getName).toList();
        assertEquals(3, filtered6.size());
        assertEquals("[GoRami, Monopoly, Tucano]", filtered6.toString());
    }

    @Test
    public void testFilterByNameLessThan() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN, name < 'C'
        List<String> filtered7 = planner.filter("name < C").map(BoardGame::getName).toList();
        assertEquals(1, filtered7.size());
        assertEquals("[17 days]", filtered7.toString());
    }

    @Test
    public void testDefaultFilterByName() {
        IPlanner planner = new Planner(games);
        // default filter
        List<String> filtered8 = planner.filter("name >>> t").map(BoardGame::getName).toList();
        assertEquals(8, filtered8.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered8.toString());
    }


    @Test
    public void testFilterByMaxPlayersEquals() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("maxPlayers == 10").map(BoardGame::getName).toList();
        assertEquals(2, filtered.size());
        assertEquals("[Go Fish, Monopoly]", filtered.toString());
    }
    @Test
    public void testFilterByMaxPlayersNotEquals() {
        IPlanner planner = new Planner(games);
        // filter NOT_EQUALS
        List<String> filtered2 = planner.filter("maxPlayers != 10").map(BoardGame::getName).toList();
        assertEquals(6, filtered2.size());
        assertEquals("[17 days, Chess, Go, golang, GoRami, Tucano]", filtered2.toString());
    }
    @Test
    public void testFilterByMaxPlayersGreaterThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN_EQUALS
        List<String> filtered3 = planner.filter("maxPlayers >= 10").map(BoardGame::getName).toList();
        assertEquals(3, filtered3.size());
        assertEquals("[Go Fish, Monopoly, Tucano]", filtered3.toString());
    }
    @Test
    public void testFilterByMaxPlayersLessThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN_EQUALS
        List<String> filtered4 = planner.filter("maxPlayers <= 10").map(BoardGame::getName).toList();
        assertEquals(7, filtered4.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly]", filtered4.toString());
    }
    @Test
    public void testFilterByMaxPlayersGreaterThan() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN
        List<String> filtered5 = planner.filter("maxPlayers > 10").map(BoardGame::getName).toList();
        assertEquals(1, filtered5.size());
        assertEquals("[Tucano]", filtered5.toString());
    }

    @Test
    public void testFilterByMaxPlayersLessThan() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN
        List<String> filtered6 = planner.filter("maxPlayers < 10").map(BoardGame::getName).toList();
        assertEquals(5, filtered6.size());
        assertEquals("[17 days, Chess, Go, golang, GoRami]", filtered6.toString());
    }

    @Test
    public void testDefaultFilterByMaxPlayers() {
        IPlanner planner = new Planner(games);
        // default filter
        List<String> filtered7 = planner.filter("maxPlayers !!! 10").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());
    }

    @Test
    public void testFilterByMinPlayersEquals() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("minPlayers == 10").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Tucano]", filtered.toString());
    }

    @Test
    public void testFilterByMinPlayersNotEquals() {
        IPlanner planner = new Planner(games);
        // filter NOT_EQUALS
        List<String> filtered2 = planner.filter("minPlayers != 2").map(BoardGame::getName).toList();
        assertEquals(4, filtered2.size());
        assertEquals("[17 days, GoRami, Monopoly, Tucano]", filtered2.toString());
    }

    @Test
    public void testFilterByMinPlayersGreaterThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN_EQUALS
        List<String> filtered3 = planner.filter("minPlayers >= 2").map(BoardGame::getName).toList();
        assertEquals(7, filtered3.size());
        assertEquals("[Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered3.toString());
    }

    @Test
    public void testFilterByMinPlayersLessThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN_EQUALS
        List<String> filtered4 = planner.filter("minPlayers <= 2").map(BoardGame::getName).toList();
        assertEquals(5, filtered4.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang]", filtered4.toString());
    }

    @Test
    public void testFilterByMinPlayersGreaterThan() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN
        List<String> filtered5 = planner.filter("minPlayers > 2").map(BoardGame::getName).toList();
        assertEquals(3, filtered5.size());
        assertEquals("[GoRami, Monopoly, Tucano]", filtered5.toString());
    }

    @Test
    public void testFilterByMinPlayersLessThan() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN
        List<String> filtered6 = planner.filter("minPlayers < 2").map(BoardGame::getName).toList();
        assertEquals(1, filtered6.size());
        assertEquals("[17 days]", filtered6.toString());
    }

    @Test
    public void testDefaultFilterByMinPlayers() {
        IPlanner planner = new Planner(games);
        // default filter
        List<String> filtered7 = planner.filter("minPlayers !!! 2").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());
    }

    @Test
    public void testFilterByMinPlaytimeEquals() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("minPlaytime == 20").map(BoardGame::getName).toList();
        assertEquals(2, filtered.size());
        assertEquals("[Go Fish, Monopoly]", filtered.toString());
    }

    @Test
    public void testFilterByMinPlaytimeNotEquals() {
        IPlanner planner = new Planner(games);
        // filter NOT_EQUALS
        List<String> filtered2 = planner.filter("minPlaytime != 20").map(BoardGame::getName).toList();
        assertEquals(6, filtered2.size());
        assertEquals("[17 days, Chess, Go, golang, GoRami, Tucano]", filtered2.toString());
    }

    @Test
    public void testFilterByMinPlaytimeGreaterThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN_EQUALS
        List<String> filtered3 = planner.filter("minPlaytime >= 60").map(BoardGame::getName).toList();
        assertEquals(2, filtered3.size());
        assertEquals("[17 days, Tucano]", filtered3.toString());
    }

    @Test
    public void testFilterByMinPlaytimeLessThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN_EQUALS
        List<String> filtered4 = planner.filter("minPlaytime <= 20").map(BoardGame::getName).toList();
        assertEquals(3, filtered4.size());
        assertEquals("[Chess, Go Fish, Monopoly]", filtered4.toString());
    }

    @Test
    public void testFilterByMinPlaytimeGreaterThan() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN
        List<String> filtered5 = planner.filter("minPlaytime > 60").map(BoardGame::getName).toList();
        assertEquals(1, filtered5.size());
        assertEquals("[17 days]", filtered5.toString());
    }

    @Test
    public void testFilterByMinPlaytimeLessThan() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN
        List<String> filtered6 = planner.filter("minPlaytime < 20").map(BoardGame::getName).toList();
        assertEquals(1, filtered6.size());
        assertEquals("[Chess]", filtered6.toString());
    }

    @Test
    public void testDefaultFilterByMinPlaytime() {
        IPlanner planner = new Planner(games);
        // default filter
        List<String> filtered7 = planner.filter("minPlaytime >>> 2").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());
    }

    @Test
    public void testFilterByMaxPlaytimeEquals() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("maxPlaytime == 120").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Go Fish]", filtered.toString());
    }

    @Test
    public void testFilterByMaxPlaytimeNotEquals() {
        IPlanner planner = new Planner(games);
        // filter NOT_EQUALS
        List<String> filtered2 = planner.filter("maxPlaytime != 120").map(BoardGame::getName).toList();
        assertEquals(7, filtered2.size());
        assertEquals("[17 days, Chess, Go, golang, GoRami, Monopoly, Tucano]", filtered2.toString());
    }

    @Test
    public void testFilterByMaxPlaytimeGreaterThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN_EQUALS
        List<String> filtered3 = planner.filter("maxPlaytime >= 120").map(BoardGame::getName).toList();
        assertEquals(2, filtered3.size());
        assertEquals("[Go Fish, Monopoly]", filtered3.toString());
    }

    @Test
    public void testFilterByMaxPlaytimeLessThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN_EQUALS
        List<String> filtered4 = planner.filter("maxPlaytime <= 40").map(BoardGame::getName).toList();
        assertEquals(2, filtered4.size());
        assertEquals("[Chess, Go]", filtered4.toString());
    }

    @Test
    public void testFilterByMaxPlaytimeGreaterThan() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN
        List<String> filtered5 = planner.filter("maxPlaytime > 120").map(BoardGame::getName).toList();
        assertEquals(1, filtered5.size());
        assertEquals("[Monopoly]", filtered5.toString());
    }

    @Test
    public void testFilterByMaxPlaytimeLessThan() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN
        List<String> filtered6 = planner.filter("maxPlaytime < 40").map(BoardGame::getName).toList();
        assertEquals(2, filtered6.size());
        assertEquals("[Chess, Go]", filtered6.toString());
    }

    @Test
    public void testDefaultFilterByMaxPlaytime() {
        IPlanner planner = new Planner(games);
        // default filter
        List<String> filtered7 = planner.filter("maxPlaytime >>> 120").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());
    }

    @Test
    public void testFilterByRankEquals() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("rank == 100").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Go]", filtered.toString());
    }

    @Test
    public void testFilterByRankNotEquals() {
        IPlanner planner = new Planner(games);
        // filter NOT_EQUALS
        List<String> filtered2 = planner.filter("rank != 100").map(BoardGame::getName).toList();
        assertEquals(7, filtered2.size());
        assertEquals("[17 days, Chess, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered2.toString());
    }

    @Test
    public void testFilterByRankGreaterThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN_EQUALS
        List<String> filtered3 = planner.filter("rank >= 700").map(BoardGame::getName).toList();
        assertEquals(2, filtered3.size());
        assertEquals("[Chess, Monopoly]", filtered3.toString());
    }

    @Test
    public void testFilterByRankLessThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN_EQUALS
        List<String> filtered4 = planner.filter("rank <= 200").map(BoardGame::getName).toList();
        assertEquals(2, filtered4.size());
        assertEquals("[Go, Go Fish]", filtered4.toString());
    }

    @Test
    public void testFilterByRankGreaterThan() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN
        List<String> filtered5 = planner.filter("rank > 700").map(BoardGame::getName).toList();
        assertEquals(1, filtered5.size());
        assertEquals("[Monopoly]", filtered5.toString());
    }

    @Test
    public void testFilterByRankLessThan() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN
        List<String> filtered6 = planner.filter("rank < 200").map(BoardGame::getName).toList();
        assertEquals(1, filtered6.size());
        assertEquals("[Go]", filtered6.toString());
    }

    @Test
    public void testDefaultFilterByRank() {
        IPlanner planner = new Planner(games);
        // default filter
        List<String> filtered7 = planner.filter("rank >>> 190").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());
    }

    @Test
    public void testFilterByRatingEquals() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("rating == 8.0").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Tucano]", filtered.toString());
    }

    @Test
    public void testFilterByRatingNotEquals() {
        IPlanner planner = new Planner(games);
        // filter NOT_EQUALS
        List<String> filtered2 = planner.filter("rating != 8.0").map(BoardGame::getName).toList();
        assertEquals(7, filtered2.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly]", filtered2.toString());
    }

    @Test
    public void testFilterByRatingGreaterThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN_EQUALS
        List<String> filtered3 = planner.filter("rating >= 8.5").map(BoardGame::getName).toList();
        assertEquals(4, filtered3.size());
        assertEquals("[17 days, Chess, golang, GoRami]", filtered3.toString());
    }

    @Test
    public void testFilterByRatingLessThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN_EQUALS
        List<String> filtered4 = planner.filter("rating <= 5").map(BoardGame::getName).toList();
        assertEquals(1, filtered4.size());
        assertEquals("[Monopoly]", filtered4.toString());
    }

    @Test
    public void testFilterByRatingGreaterThan() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN
        List<String> filtered5 = planner.filter("rating > 8.5").map(BoardGame::getName).toList();
        assertEquals(3, filtered5.size());
        assertEquals("[17 days, Chess, golang]", filtered5.toString());
    }

    @Test
    public void testFilterByRatingLessThan() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN
        List<String> filtered6 = planner.filter("rating < 5.5").map(BoardGame::getName).toList();
        assertEquals(1, filtered6.size());
        assertEquals("[Monopoly]", filtered6.toString());
    }

    @Test
    public void testDefaultFilterByRating() {
        IPlanner planner = new Planner(games);
        // default filter
        List<String> filtered7 = planner.filter("rating >>> 0").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());
    }

    @Test
    public void testFilterByDifficultyEquals() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("difficulty == 8.0").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Go]", filtered.toString());
    }

    @Test
    public void testFilterByDifficultyNotEquals() {
        IPlanner planner = new Planner(games);
        // filter NOT_EQUALS
        List<String> filtered2 = planner.filter("difficulty != 8.0").map(BoardGame::getName).toList();
        assertEquals(7, filtered2.size());
        assertEquals("[17 days, Chess, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered2.toString());
    }

    @Test
    public void testFilterByDifficultyGreaterThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN_EQUALS
        List<String> filtered3 = planner.filter("difficulty >= 8").map(BoardGame::getName).toList();
        assertEquals(3, filtered3.size());
        assertEquals("[17 days, Chess, Go]", filtered3.toString());
    }

    @Test
    public void testFilterByDifficultyLessThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN_EQUALS
        List<String> filtered4 = planner.filter("difficulty <= 5").map(BoardGame::getName).toList();
        assertEquals(3, filtered4.size());
        assertEquals("[Go Fish, GoRami, Monopoly]", filtered4.toString());
    }

    @Test
    public void testFilterByDifficultyGreaterThan() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN
        List<String> filtered5 = planner.filter("difficulty > 8").map(BoardGame::getName).toList();
        assertEquals(2, filtered5.size());
        assertEquals("[17 days, Chess]", filtered5.toString());
    }

    @Test
    public void testFilterByDifficultyLessThan() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN
        List<String> filtered6 = planner.filter("difficulty < 5").map(BoardGame::getName).toList();
        assertEquals(2, filtered6.size());
        assertEquals("[Go Fish, Monopoly]", filtered6.toString());
    }

    @Test
    public void testDefaultFilterByDifficulty() {
        IPlanner planner = new Planner(games);
        // default filter
        List<String> filtered7 = planner.filter("difficulty >>> 8").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());
    }

    @Test
    public void testFilterByYearPublishedEquals() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("year == 2000").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Go]", filtered.toString());
    }

    @Test
    public void testFilterByYearPublishedNotEquals() {
        IPlanner planner = new Planner(games);
        // filter NOT_EQUALS
        List<String> filtered2 = planner.filter("year != 2000").map(BoardGame::getName).toList();
        assertEquals(7, filtered2.size());
        assertEquals("[17 days, Chess, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered2.toString());
    }

    @Test
    public void testFilterByYearPublishedGreaterThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN_EQUALS
        List<String> filtered3 = planner.filter("year >= 2005").map(BoardGame::getName).toList();
        assertEquals(3, filtered3.size());
        assertEquals("[17 days, Chess, Monopoly]", filtered3.toString());
    }

    @Test
    public void testFilterByYearPublishedLessThanOrEquals() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN_EQUALS
        List<String> filtered4 = planner.filter("year <= 2002").map(BoardGame::getName).toList();
        assertEquals(3, filtered4.size());
        assertEquals("[Go, Go Fish, GoRami]", filtered4.toString());
    }

    @Test
    public void testFilterByYearPublishedGreaterThan() {
        IPlanner planner = new Planner(games);
        // filter GREATER_THAN
        List<String> filtered5 = planner.filter("year > 2005").map(BoardGame::getName).toList();
        assertEquals(2, filtered5.size());
        assertEquals("[Chess, Monopoly]", filtered5.toString());
    }

    @Test
    public void testFilterByYearPublishedLessThan() {
        IPlanner planner = new Planner(games);
        // filter LESS_THAN
        List<String> filtered6 = planner.filter("year < 2002").map(BoardGame::getName).toList();
        assertEquals(2, filtered6.size());
        assertEquals("[Go, Go Fish]", filtered6.toString());
    }

    @Test
    public void testDefaultFilterByYearPublished() {
        IPlanner planner = new Planner(games);
        // default filter
        List<String> filtered7 = planner.filter("year ! 2000").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());
    }

    @Test
    public void testInvalidFilter() {
        IPlanner planner = new Planner(games);
        // filter with invalid column name
        List<String> filtered = planner.filter("invalidColumnName > 1").map(BoardGame::getName).toList();
        assertEquals(8, filtered.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered.toString());
    }

    @Test
    public void testEmptyFilter() {
        IPlanner planner = new Planner(games);
        // filter with empty column name
        List<String> filtered = planner.filter("").map(BoardGame::getName).toList();
        assertEquals(8, filtered.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered.toString());
    }

    @Test
    public void testChainedFilters() {
        IPlanner planner = new Planner(games);
        // filter CONTAINS 'Go' and maxPlayers >=5, MinPlaytime >= 40
        List<String> filtered = planner.filter("name~=go, maxPlayers>=5, minPlaytime>=40").map(BoardGame::getName).toList();
        assertEquals(2, filtered.size());
        assertEquals("[golang, GoRami]", filtered.toString());
    }

    @Test
    public void testFiltersWithSpaces() {
        IPlanner planner = new Planner(games);
        // filter with space
        List<String> filtered = planner.filter("  name ~= go  ,  maxPlayers >= 5  , minPlaytime >= 40  ").map(BoardGame::getName).toList();
        assertEquals(2, filtered.size());
        assertEquals("[golang, GoRami]", filtered.toString());
    }

    @Test
    public void testFilterSortNameDesc() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("name~=go, maxPlayers>=2, minPlaytime>=20", GameData.NAME, false).map(BoardGame::getName).toList();
        assertEquals(4, filtered.size());
        assertEquals("[GoRami, golang, Go Fish, Go]", filtered.toString());
    }

    @Test
    public void testFilterByMinPlayersAsc() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("", GameData.MIN_PLAYERS).map(BoardGame::getName).toList();
        assertEquals(8, filtered.size());
        assertEquals("[17 days, golang, Go, Go Fish, Chess, GoRami, Monopoly, Tucano]", filtered.toString());
    }

    @Test
    public void testFilterByMinPlayersDesc() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("", GameData.MIN_PLAYERS, false).map(BoardGame::getName).toList();
        assertEquals(8, filtered.size());
        assertEquals("[Tucano, GoRami, Monopoly, golang, Go, Go Fish, Chess, 17 days]", filtered.toString());
    }

    @Test
    public void testFilterReset() {
        Planner planner = new Planner(games);
        planner.filter("name==Tucano", GameData.NAME, false);
        assertEquals(1, planner.getFilteredGamesCount());
        planner.reset();
        assertEquals(8, planner.getFilteredGamesCount());
    }

}