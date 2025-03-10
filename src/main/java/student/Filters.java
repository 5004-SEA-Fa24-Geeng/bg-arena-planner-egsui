package student;

/**
 * A utility class for filtering BoardGame objects based on various attributes.
 */
public final class Filters {
    /**
     * Private constructor for Filters to prevent instantiation.
     */
    private Filters() {
    }

    /**
     * A public static filter method for filtering boardgames.
     *
     * @param game   the board game
     * @param column column of board games
     * @param op     operator for operations
     * @param value  value for operations
     * @return true if matching the filter, else false
     */
    public static boolean filter(BoardGame game, GameData column,
                                 Operations op, String value) {
        switch (column) {
            case NAME:
                return filterString(game.getName(), op, value);
            case MAX_PLAYERS:
                return filterNumber(game.getMaxPlayers(), op, value);
            case MIN_PLAYERS:
                return filterNumber(game.getMinPlayers(), op, value);
            case MIN_TIME:
                return filterNumber(game.getMinPlayTime(), op, value);
            case MAX_TIME:
                return filterNumber(game.getMaxPlayTime(), op, value);
            case RANK:
                return filterNumber(game.getRank(), op, value);
            case RATING:
                return filterNumber(game.getRating(), op, value);
            case DIFFICULTY:
                return filterNumber(game.getDifficulty(), op, value);
            case YEAR:
                return filterNumber(game.getYearPublished(), op, value);
            default:
                return false;
        }
    }

    /**
     * A private static method filterString for string filter operations.
     * Only available for column NAME.
     *
     * @param gameData the attribute of board game
     * @param op       the operator for operation
     * @param value    the value for operation
     * @return true if matching the filter, else false
     */
    private static boolean filterString(String gameData, Operations op, String value) {
        // Case-insensitive handling and Removing spaces in gameData
        String lowerGameDataWoSPaces = gameData.toLowerCase().replaceAll(" ", "");
        // Case-insensitive handling
        String lowerValue = value.toLowerCase();
        switch (op) {
            case CONTAINS:
                return lowerGameDataWoSPaces.contains(lowerValue);
            case EQUALS:
                return lowerGameDataWoSPaces.equals(lowerValue);
            case NOT_EQUALS:
                return !lowerGameDataWoSPaces.equals(lowerValue);
            case GREATER_THAN_EQUALS:
                return lowerGameDataWoSPaces.compareTo(lowerValue) >= 0;
            case LESS_THAN_EQUALS:
                return lowerGameDataWoSPaces.compareTo(lowerValue) <= 0;
            case GREATER_THAN:
                return lowerGameDataWoSPaces.compareTo(lowerValue) > 0;
            case LESS_THAN:
                return lowerGameDataWoSPaces.compareTo(lowerValue) < 0;
            default:
                return false;
        }
    }

    /**
     * A private static method filterNumber for number filter operations.
     *
     * @param gameData the attribute of board game
     * @param op       the operator for operation
     * @param value    the value for operation
     * @return true if matching the filter, else false
     */
    private static boolean filterNumber(double gameData, Operations op, String value) {
        double val;
        try {
            val = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return false; // Return false to indicate the comparison is not valid
        }
        switch (op) {
            case EQUALS:
                return gameData == val;
            case NOT_EQUALS:
                return gameData != val;
            case GREATER_THAN_EQUALS:
                return gameData >= val;
            case LESS_THAN_EQUALS:
                return gameData <= val;
            case GREATER_THAN:
                return gameData > val;
            case LESS_THAN:
                return gameData < val;
            default:
                return false;
        }
    }
}
