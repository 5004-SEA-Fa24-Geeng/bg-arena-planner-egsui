package student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;
import static java.lang.Math.min;

/**
 * A public GameList class implements IGameList.
 * Stores and modifies data in games list.
 */
public class GameList implements IGameList {
    /** A set of names of games.*/
    private Set<String> listOfGames;

    /**
     * Constructor for the GameList.
     */
    public GameList() {
        // Instantiates a Set to store names of games.
        this.listOfGames = new TreeSet<>(String::compareTo);
    }

    /**
     * Get list version of Games.
     * @return  list version of Games
     */
    @Override
    public List<String> getGameNames() {
        return List.copyOf(this.listOfGames);
    }

    /**
     * Clear the list of games.
     */
    @Override
    public void clear() {
        this.listOfGames = new TreeSet<>(String::compareTo);
    }

    /**
     * Get the size of games list.
     * @return  the size of games list
     */
    @Override
    public int count() {
        return this.listOfGames.size();
    }

    /**
     * Save a file consists of list of games.
     * @param filename The name of the file to save the list to
     */
    @Override
    public void saveGame(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String name : this.getGameNames()) {
                writer.write(name);
                writer.newLine(); // Ensures each name is on a new line
            }
            System.out.println("Games saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving games to file: " + e.getMessage());
        }
    }

    /**
     * Adds a single game or multiple games to the games list.
     * @param str      the string to parse and add games to the list
     * @param filtered the filtered list to use as a basis for adding
     * @throws IllegalArgumentException if the argument is illegal
     */
    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {
        List<BoardGame> filteredList = filtered.toList();
        List<String> filteredLowerList = filteredList.stream()
                .map(game -> game.getName().replaceAll(" ", "").toLowerCase()).toList();
        String lowerStr = str.replaceAll(" ", "").toLowerCase();
        if (str.matches("\\d+")) { // Single number case
            int index = Integer.parseInt(str) - 1;
            if (index >= 0 && index < filteredList.size()) {
                this.listOfGames.add(filteredList.get(index).getName());
            } else {
                throw new IllegalArgumentException("Index out of range: " + str);
            }
        } else if (str.matches("\\d+-\\d+")) { // Range case
            String[] range = str.split("-");
            int start = Integer.parseInt(range[0]);
            int end = min(Integer.parseInt(range[1]), filteredList.size());

            if (start > end) {
                throw new IllegalArgumentException("Invalid input: " + str); // Ignore invalid ranges like "49-20"
            }

            for (int i = start; i <= end; i++) {
                int index = i - 1;
                if (index >= 0 && index < filteredList.size()) {
                    this.listOfGames.add(filteredList.get(index).getName());
                }
            }
        } else if (filteredLowerList.contains(lowerStr)) {
            int index = filteredLowerList.indexOf(lowerStr);
            this.listOfGames.add(filteredList.get(index).getName());
        } else if (lowerStr.equals(ADD_ALL)) {
            for (BoardGame boardGame : filteredList) {
                this.listOfGames.add(boardGame.getName());
            }
        } else {
            throw new IllegalArgumentException("Invalid input: " + str);
        }
    }

    /**
     * Removes a single game or multiple games from the games list.
     * @param str The string to parse and remove games from the list.
     * @throws IllegalArgumentException if the argument is illegal
     */
    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        String lowerStr = str.replaceAll(" ", "").toLowerCase();
        List<String> gamesList = this.getGameNames();
        List<String> gamesLowerList = this.getGameNames().stream()
                .map(game -> game.replaceAll(" ", "").toLowerCase()).toList();
        if (str.matches("\\d+")) { // Single number case
            int index = Integer.parseInt(str) - 1;
            if (index >= 0 && index < gamesList.size()) {
                this.listOfGames.remove(gamesList.get(index));
            } else {
                throw new IllegalArgumentException("Index out of range: " + str);
            }
        } else if (str.matches("\\d+-\\d+")) { // Range case
            String[] range = str.split("-");
            int start = Integer.parseInt(range[0]);
            int end = min(Integer.parseInt(range[1]), gamesList.size());

            if (start > end) {
                throw new IllegalArgumentException("Invalid input: " + str); // Ignore invalid ranges like "49-20"
            }

            for (int i = start; i <= end; i++) {
                int index = i - 1;
                if (index >= 0 && index < gamesList.size()) {
                    this.listOfGames.remove(gamesList.get(index));
                }
            }
        } else if (gamesLowerList.contains(lowerStr)) {
            int index = gamesLowerList.indexOf(lowerStr);
            this.listOfGames.remove(gamesList.get(index));
        } else if (lowerStr.equals(ADD_ALL)) {
            for (String game : gamesList) {
                this.listOfGames.remove(game);
            }
        } else {
            throw new IllegalArgumentException("Invalid input: " + str);
        }
        this.listOfGames.remove(str);
    }


}
