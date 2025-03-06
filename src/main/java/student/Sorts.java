package student;

import java.util.Comparator;

public class Sorts {
    public static Comparator<BoardGame> getSortType(GameData sortOn, boolean asc) {
        switch (sortOn) {
            case MAX_PLAYERS:
                return (o1, o2) -> {
                    int compare = o1.getMaxPlayers() - o2.getMaxPlayers();
                    return asc ? compare : -compare;
                };
            case MIN_PLAYERS:
                return (o1, o2) -> {
                    int compare = o1.getMinPlayers() - o2.getMinPlayers();
                    return asc ? compare : -compare;
                };
            case MAX_TIME:
                return (o1, o2) -> {
                    int compare = o1.getMaxPlayTime() - o2.getMaxPlayTime();
                    return asc ? compare : -compare;
                };
            case MIN_TIME:
                return (o1, o2) -> {
                    int compare = o1.getMinPlayTime() - o2.getMinPlayTime();
                    return asc ? compare : -compare;
                };
            case RANK:
                return (o1, o2) -> {
                    int compare = o1.getRank() - o2.getRank();
                    return asc ? compare : -compare;
                };
            case RATING:
                return (o1, o2) -> {
                    int compare = (int) Math.signum(o1.getRating() - o2.getRating());
                    return asc ? compare : -compare;
                };
            case DIFFICULTY:
                return (o1, o2) -> {
                    int compare = (int) Math.signum(o1.getDifficulty() - o2.getDifficulty());
                    return asc ? compare : -compare;
                };
            case YEAR:
                return (o1, o2) -> {
                    int compare = o1.getYearPublished() - o2.getYearPublished();
                    return asc ? compare : -compare;
                };
            default:
                // NAME
                return (o1, o2) -> {
                    int compare = o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                    return asc ? compare : -compare;
                };
        }

    }
}
