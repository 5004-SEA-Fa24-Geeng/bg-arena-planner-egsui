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
    public void testFilterName() {
        // filter EQUALS 'Go'
         IPlanner planner = new Planner(games);
         List<BoardGame> filtered = planner.filter("name == Go").toList();
         assertEquals(1, filtered.size());
         assertEquals("Go", filtered.get(0).getName());

         // filter CONTAINS 'Go'
         planner.reset();
         List<String> filtered2 = planner.filter("name ~= Go").map(BoardGame::getName).toList();
         assertEquals(4, filtered2.size());
         assertEquals("[Go, Go Fish, golang, GoRami]", filtered2.toString());

         // filter NOT_EQUALS to '17 days'
         planner.reset();
         List<String> filtered3 = planner.filter("name != 17 days").map(BoardGame::getName).toList();
         assertEquals(7, filtered3.size());
         assertEquals("[Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered3.toString());

         // filter GREATER_THAN_EQUALS, name >= 'G'
         planner.reset();
         List<String> filtered4 = planner.filter("name >= G").map(BoardGame::getName).toList();
         assertEquals(6, filtered4.size());
         assertEquals("[Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered4.toString());

         // filter LESS_THAN_EQUALS, name <= 'Go'
         planner.reset();
         List<String> filtered5 = planner.filter("name <= Go").map(BoardGame::getName).toList();
         assertEquals(3, filtered5.size());
         assertEquals("[17 days, Chess, Go]", filtered5.toString());

         // filter GREATER_THAN, name > 'golang'
         planner.reset();
         List<String> filtered6 = planner.filter("name > golang").map(BoardGame::getName).toList();
         assertEquals(3, filtered6.size());
         assertEquals("[GoRami, Monopoly, Tucano]", filtered6.toString());

         // filter LESS_THAN, name < 'C'
         planner.reset();
         List<String> filtered7 = planner.filter("name < C").map(BoardGame::getName).toList();
         assertEquals(1, filtered7.size());
         assertEquals("[17 days]", filtered7.toString());

         // default filter
         planner.reset();
         List<String> filtered8 = planner.filter("name >>> t").map(BoardGame::getName).toList();
         assertEquals(8, filtered8.size());
         assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered8.toString());

    }

    @Test
    public void testFilterMaxPlayers() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("maxPlayers == 10").map(BoardGame::getName).toList();
        assertEquals(2, filtered.size());
        assertEquals("[Go Fish, Monopoly]", filtered.toString());

        // filter NOT_EQUALS
        planner.reset();
        List<String> filtered2 = planner.filter("maxPlayers != 10").map(BoardGame::getName).toList();
        assertEquals(6, filtered2.size());
        assertEquals("[17 days, Chess, Go, golang, GoRami, Tucano]", filtered2.toString());

        // filter GREATER_THAN_EQUALS
        planner.reset();
        List<String> filtered3 = planner.filter("maxPlayers >= 10").map(BoardGame::getName).toList();
        assertEquals(3, filtered3.size());
        assertEquals("[Go Fish, Monopoly, Tucano]", filtered3.toString());

        // filter LESS_THAN_EQUALS
        planner.reset();
        List<String> filtered4 = planner.filter("maxPlayers <= 10").map(BoardGame::getName).toList();
        assertEquals(7, filtered4.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly]", filtered4.toString());

        // filter GREATER_THAN
        planner.reset();
        List<String> filtered5 = planner.filter("maxPlayers > 10").map(BoardGame::getName).toList();
        assertEquals(1, filtered5.size());
        assertEquals("[Tucano]", filtered5.toString());

        // filter LESS_THAN
        planner.reset();
        List<String> filtered6 = planner.filter("maxPlayers < 10").map(BoardGame::getName).toList();
        assertEquals(5, filtered6.size());
        assertEquals("[17 days, Chess, Go, golang, GoRami]", filtered6.toString());

        // default filter
        planner.reset();
        List<String> filtered7 = planner.filter("maxPlayers !!! 10").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());

    }

    @Test
    public void testFilterMinPlayers() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("minPlayers == 10").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Tucano]", filtered.toString());

        // filter NOT_EQUALS
        planner.reset();
        List<String> filtered2 = planner.filter("minPlayers != 2").map(BoardGame::getName).toList();
        assertEquals(4, filtered2.size());
        assertEquals("[17 days, GoRami, Monopoly, Tucano]", filtered2.toString());

        // filter GREATER_THAN_EQUALS
        planner.reset();
        List<String> filtered3 = planner.filter("minPlayers >= 2").map(BoardGame::getName).toList();
        assertEquals(7, filtered3.size());
        assertEquals("[Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered3.toString());

        // filter LESS_THAN_EQUALS
        planner.reset();
        List<String> filtered4 = planner.filter("minPlayers <= 2").map(BoardGame::getName).toList();
        assertEquals(5, filtered4.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang]", filtered4.toString());

        // filter GREATER_THAN
        planner.reset();
        List<String> filtered5 = planner.filter("minPlayers > 2").map(BoardGame::getName).toList();
        assertEquals(3, filtered5.size());
        assertEquals("[GoRami, Monopoly, Tucano]", filtered5.toString());

        // filter LESS_THAN
        planner.reset();
        List<String> filtered6 = planner.filter("minPlayers < 2").map(BoardGame::getName).toList();
        assertEquals(1, filtered6.size());
        assertEquals("[17 days]", filtered6.toString());

        // default filter
        planner.reset();
        List<String> filtered7 = planner.filter("minPlayers !!! 2").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());

    }

    @Test
    public void testFilterMinTime() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("minPlaytime == 20").map(BoardGame::getName).toList();
        assertEquals(2, filtered.size());
        assertEquals("[Go Fish, Monopoly]", filtered.toString());

        // filter NOT_EQUALS
        planner.reset();
        List<String> filtered2 = planner.filter("minPlaytime != 20").map(BoardGame::getName).toList();
        assertEquals(6, filtered2.size());
        assertEquals("[17 days, Chess, Go, golang, GoRami, Tucano]", filtered2.toString());

        // filter GREATER_THAN_EQUALS
        planner.reset();
        List<String> filtered3 = planner.filter("minPlaytime >= 60").map(BoardGame::getName).toList();
        assertEquals(2, filtered3.size());
        assertEquals("[17 days, Tucano]", filtered3.toString());

        // filter LESS_THAN_EQUALS
        planner.reset();
        List<String> filtered4 = planner.filter("minPlaytime <= 20").map(BoardGame::getName).toList();
        assertEquals(3, filtered4.size());
        assertEquals("[Chess, Go Fish, Monopoly]", filtered4.toString());

        // filter GREATER_THAN
        planner.reset();
        List<String> filtered5 = planner.filter("minPlaytime > 60").map(BoardGame::getName).toList();
        assertEquals(1, filtered5.size());
        assertEquals("[17 days]", filtered5.toString());

        // filter LESS_THAN
        planner.reset();
        List<String> filtered6 = planner.filter("minPlaytime < 20").map(BoardGame::getName).toList();
        assertEquals(1, filtered6.size());
        assertEquals("[Chess]", filtered6.toString());

        // default filter
        planner.reset();
        List<String> filtered7 = planner.filter("minPlaytime >>> 2").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());

    }

    @Test
    public void testFilterMaxTime() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("maxPlaytime == 120").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Go Fish]", filtered.toString());

        // filter NOT_EQUALS
        planner.reset();
        List<String> filtered2 = planner.filter("maxPlaytime != 120").map(BoardGame::getName).toList();
        assertEquals(7, filtered2.size());
        assertEquals("[17 days, Chess, Go, golang, GoRami, Monopoly, Tucano]", filtered2.toString());

        // filter GREATER_THAN_EQUALS
        planner.reset();
        List<String> filtered3 = planner.filter("maxPlaytime >= 120").map(BoardGame::getName).toList();
        assertEquals(2, filtered3.size());
        assertEquals("[Go Fish, Monopoly]", filtered3.toString());

        // filter LESS_THAN_EQUALS
        planner.reset();
        List<String> filtered4 = planner.filter("maxPlaytime <= 40").map(BoardGame::getName).toList();
        assertEquals(2, filtered4.size());
        assertEquals("[Chess, Go]", filtered4.toString());

        // filter GREATER_THAN
        planner.reset();
        List<String> filtered5 = planner.filter("maxPlaytime > 120").map(BoardGame::getName).toList();
        assertEquals(1, filtered5.size());
        assertEquals("[Monopoly]", filtered5.toString());

        // filter LESS_THAN
        planner.reset();
        List<String> filtered6 = planner.filter("maxPlaytime < 40").map(BoardGame::getName).toList();
        assertEquals(2, filtered6.size());
        assertEquals("[Chess, Go]", filtered6.toString());

        // default filter
        planner.reset();
        List<String> filtered7 = planner.filter("maxPlaytime >>> 120").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());

    }

    @Test
    public void testFilterRank() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("rank == 100").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Go]", filtered.toString());

        // filter NOT_EQUALS
        planner.reset();
        List<String> filtered2 = planner.filter("rank != 100").map(BoardGame::getName).toList();
        assertEquals(7, filtered2.size());
        assertEquals("[17 days, Chess, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered2.toString());

        // filter GREATER_THAN_EQUALS
        planner.reset();
        List<String> filtered3 = planner.filter("rank >= 700").map(BoardGame::getName).toList();
        assertEquals(2, filtered3.size());
        assertEquals("[Chess, Monopoly]", filtered3.toString());

        // filter LESS_THAN_EQUALS
        planner.reset();
        List<String> filtered4 = planner.filter("rank <= 200").map(BoardGame::getName).toList();
        assertEquals(2, filtered4.size());
        assertEquals("[Go, Go Fish]", filtered4.toString());

        // filter GREATER_THAN
        planner.reset();
        List<String> filtered5 = planner.filter("rank > 700").map(BoardGame::getName).toList();
        assertEquals(1, filtered5.size());
        assertEquals("[Monopoly]", filtered5.toString());

        // filter LESS_THAN
        planner.reset();
        List<String> filtered6 = planner.filter("rank < 200").map(BoardGame::getName).toList();
        assertEquals(1, filtered6.size());
        assertEquals("[Go]", filtered6.toString());

        // default filter
        planner.reset();
        List<String> filtered7 = planner.filter("rank >>> 190").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());

    }

    @Test
    public void testFilterRating() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("rating == 8.0").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Tucano]", filtered.toString());

        // filter NOT_EQUALS
        planner.reset();
        List<String> filtered2 = planner.filter("rating != 8.0").map(BoardGame::getName).toList();
        assertEquals(7, filtered2.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly]", filtered2.toString());

        // filter GREATER_THAN_EQUALS
        planner.reset();
        List<String> filtered3 = planner.filter("rating >= 8.5").map(BoardGame::getName).toList();
        assertEquals(4, filtered3.size());
        assertEquals("[17 days, Chess, golang, GoRami]", filtered3.toString());

        // filter LESS_THAN_EQUALS
        planner.reset();
        List<String> filtered4 = planner.filter("rating <= 5").map(BoardGame::getName).toList();
        assertEquals(1, filtered4.size());
        assertEquals("[Monopoly]", filtered4.toString());

        // filter GREATER_THAN
        planner.reset();
        List<String> filtered5 = planner.filter("rating > 8.5").map(BoardGame::getName).toList();
        assertEquals(3, filtered5.size());
        assertEquals("[17 days, Chess, golang]", filtered5.toString());

        // filter LESS_THAN
        planner.reset();
        List<String> filtered6 = planner.filter("rating < 5.5").map(BoardGame::getName).toList();
        assertEquals(1, filtered6.size());
        assertEquals("[Monopoly]", filtered6.toString());

        // default filter
        planner.reset();
        List<String> filtered7 = planner.filter("rating >>> 0").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());

    }

    @Test
    public void testFilterDifficulty() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("difficulty == 8.0").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Go]", filtered.toString());

        // filter NOT_EQUALS
        planner.reset();
        List<String> filtered2 = planner.filter("difficulty != 8.0").map(BoardGame::getName).toList();
        assertEquals(7, filtered2.size());
        assertEquals("[17 days, Chess, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered2.toString());

        // filter GREATER_THAN_EQUALS
        planner.reset();
        List<String> filtered3 = planner.filter("difficulty >= 8").map(BoardGame::getName).toList();
        assertEquals(3, filtered3.size());
        assertEquals("[17 days, Chess, Go]", filtered3.toString());

        // filter LESS_THAN_EQUALS
        planner.reset();
        List<String> filtered4 = planner.filter("difficulty <= 5").map(BoardGame::getName).toList();
        assertEquals(3, filtered4.size());
        assertEquals("[Go Fish, GoRami, Monopoly]", filtered4.toString());

        // filter GREATER_THAN
        planner.reset();
        List<String> filtered5 = planner.filter("difficulty > 8").map(BoardGame::getName).toList();
        assertEquals(2, filtered5.size());
        assertEquals("[17 days, Chess]", filtered5.toString());

        // filter LESS_THAN
        planner.reset();
        List<String> filtered6 = planner.filter("difficulty < 5").map(BoardGame::getName).toList();
        assertEquals(2, filtered6.size());
        assertEquals("[Go Fish, Monopoly]", filtered6.toString());

        // default filter
        planner.reset();
        List<String> filtered7 = planner.filter("difficulty >>> 8").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());

    }

    @Test
    public void testFilterYear() {
        // filter EQUALS
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("year == 2000").map(BoardGame::getName).toList();
        assertEquals(1, filtered.size());
        assertEquals("[Go]", filtered.toString());

        // filter NOT_EQUALS
        planner.reset();
        List<String> filtered2 = planner.filter("year != 2000").map(BoardGame::getName).toList();
        assertEquals(7, filtered2.size());
        assertEquals("[17 days, Chess, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered2.toString());

        // filter GREATER_THAN_EQUALS
        planner.reset();
        List<String> filtered3 = planner.filter("year >= 2005").map(BoardGame::getName).toList();
        assertEquals(3, filtered3.size());
        assertEquals("[17 days, Chess, Monopoly]", filtered3.toString());

        // filter LESS_THAN_EQUALS
        planner.reset();
        List<String> filtered4 = planner.filter("year <= 2002").map(BoardGame::getName).toList();
        assertEquals(3, filtered4.size());
        assertEquals("[Go, Go Fish, GoRami]", filtered4.toString());

        // filter GREATER_THAN
        planner.reset();
        List<String> filtered5 = planner.filter("year > 2005").map(BoardGame::getName).toList();
        assertEquals(2, filtered5.size());
        assertEquals("[Chess, Monopoly]", filtered5.toString());

        // filter LESS_THAN
        planner.reset();
        List<String> filtered6 = planner.filter("year < 2002").map(BoardGame::getName).toList();
        assertEquals(2, filtered6.size());
        assertEquals("[Go, Go Fish]", filtered6.toString());

        // default filter
        planner.reset();
        List<String> filtered7 = planner.filter("year ! 2000").map(BoardGame::getName).toList();
        assertEquals(8, filtered7.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered7.toString());

    }
    @Test
    public void testFilterDefault() {
        // filter with invalid column name
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("invalidColumnName > 1").map(BoardGame::getName).toList();
        assertEquals(8, filtered.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered.toString());

        // filter with empty column name
        planner.reset();
        List<String> filtered2 = planner.filter("").map(BoardGame::getName).toList();
        assertEquals(8, filtered2.size());
        assertEquals("[17 days, Chess, Go, Go Fish, golang, GoRami, Monopoly, Tucano]", filtered.toString());
    }

    @Test
    public void testMultipleFilters() {
        // filter CONTAINS 'Go' and maxPlayers > 6
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("name ~= Go, maxPlayers > 6").map(BoardGame::getName).toList();
        assertEquals(2, filtered.size());
        assertEquals("[Go Fish, golang]", filtered.toString());

        // filter CONTAINS 'go', maxPlayers >= 5, minPlaytime >= 40
        planner.reset();
        List<String> filtered2 = planner.filter("name~=go, maxPlayers>=5, minPlaytime>=40").map(BoardGame::getName).toList();
        assertEquals(2, filtered2.size());
        assertEquals("[golang, GoRami]", filtered2.toString());

        // filter CONTAINS 'go', maxPlayers >= 5, minPlaytime >= 40
        planner.reset();
        List<String> filtered3 = planner.filter("name~=go, maxPlayers>=5, minPlaytime>=40").map(BoardGame::getName).toList();
        assertEquals(2, filtered3.size());
        assertEquals("[golang, GoRami]", filtered2.toString());

        // filter invalidColumn > 1, name CONTAINS 'go', maxPlayers >= r(invalid input), minPlaytime >= ss(invalid input)
        planner.reset();
        List<String> filtered4 = planner.filter("invalidColumn > 1, name~=go, maxPlayers>=r, minPlaytime>=ss").map(BoardGame::getName).toList();
        assertEquals(0, filtered4.size());
        assertEquals("[]", filtered4.toString());
    }



}