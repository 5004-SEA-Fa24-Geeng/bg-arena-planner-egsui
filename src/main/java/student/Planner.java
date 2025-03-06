package student;


import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A public Planner class implements IPlanner.
 * Sets up filters for the board game data.
 */
public class Planner implements IPlanner {
    /** A set of all games.*/
    private final Set<BoardGame> games;
    /** A set of filtered games.*/
    private Set<BoardGame> filteredGames;

    /**
     * Constructor for Planner.
     * @param games board games
     */
    public Planner(Set<BoardGame> games) {
        this.games = games;
        this.filteredGames = games;
    }

    /**
     * A method getFilteredGamesCount to get the size of filteredGames.
     * @return  size of filteredGames
     */
    public int getFilteredGamesCount() {
        return filteredGames.size();
    }

    /**
     * Assumes the results are sorted in ascending order, and that the steam is sorted by the name
     * of the board game (GameData.NAME).
     *
     * @param filter The filter to apply to the board games.
     * @return A stream of board games that match the filter.
     */
    @Override
    public Stream<BoardGame> filter(String filter) {
        return filterByFilters(filter).stream().sorted(Sorts.getSortType(GameData.NAME, true));
    }

    /**
     * A private method for processing the string of filters.
     * @param filter  string of filter(s)
     * @return  a set of filtered board games
     */
    private Set<BoardGame> filterByFilters(String filter) {
        Set<BoardGame> result = this.filteredGames;
        String[] filters = filter.split(",");
        for (String f : filters) {
            Operations operator = Operations.getOperatorFromStr(f);
            if (operator == null) {
                return result;
            }
            f = f.replaceAll(" ", "");      // remove spaces
            String[] parts = f.split(operator.getOperator());
            if (parts.length != 2) {
                return result;
            }
            GameData column;
            try {
                column = GameData.fromString(parts[0]);
            } catch (IllegalArgumentException e) {
                continue;
            }

            String value;
            try {
                value = parts[1].trim();
            } catch (IllegalArgumentException e) {
                return result;
            }
            result = result.stream()
                    .filter(game -> Filters.filter(game, column, operator, value))
                    .collect(Collectors.toSet());

        }
        this.filteredGames = result;
        return result;
    }

    /**
     * Filters the board games by the passed in text filter. Assumes the results are sorted in
     * ascending order.
     * @param filter The filter to apply to the board games.
     * @param sortOn The column to sort the results on.
     * @return  a stream of board games
     */
    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        return filterByFilters(filter).stream().sorted(Sorts.getSortType(sortOn, true));
    }

    /**
     * Filters the board games by the passed in text filter.
     * @param filter The filter to apply to the board games.
     * @param sortOn The column to sort the results on.
     * @param ascending Whether to sort the results in ascending order or descending order.
     * @return  a stream of board games
     */
    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        return filterByFilters(filter).stream().sorted(Sorts.getSortType(sortOn, ascending));
    }

    /**
     * Resets the collection to have no filters applied.
     */
    @Override
    public void reset() {
        this.filteredGames = this.games;
    }


}
