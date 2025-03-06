package student;


import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Planner implements IPlanner {
    private final Set<BoardGame> games;
    private Set<BoardGame> filteredGames;

    public Planner(Set<BoardGame> games) {
        this.games = games;
        this.filteredGames = games;
    }

    @Override
    public Stream<BoardGame> filter(String filter) {
        return filterByFilters(filter).stream().sorted(Sorts.getSortType(GameData.NAME, true));
    }

    private Set<BoardGame> filterByFilters(String filter) {
        Set<BoardGame> result = this.filteredGames;
        String[] filters =  filter.split(",");
        for (String f : filters) {
            Operations operator = Operations.getOperatorFromStr(f);
            if (operator == null) {
                return result;
            }
            // remove spaces
            f = f.replaceAll(" ", "");
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

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        return filterByFilters(filter).stream().sorted(Sorts.getSortType(sortOn, true));
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        return filterByFilters(filter).stream().sorted(Sorts.getSortType(sortOn, ascending));
    }

    @Override
    public void reset() {
        this.filteredGames = this.games;
    }


}
