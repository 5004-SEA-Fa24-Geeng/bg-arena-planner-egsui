package student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Math.min;
import static org.apache.commons.lang3.math.NumberUtils.max;

public class GameList implements IGameList {
    Set<String> listOfGames;
    /**
     * Constructor for the GameList.
     */
    public GameList() {
        // throw new UnsupportedOperationException("Unimplemented constructor 'GameList'");
        this.listOfGames = new TreeSet<>(String::compareTo);
    }

    @Override
    public List<String> getGameNames() {
        // TODO Auto-generated method stub
        // list version of Games
        return List.copyOf(this.listOfGames);
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        this.listOfGames = new HashSet<>();
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        return this.listOfGames.size();
    }

    @Override
    public void saveGame(String filename) {
        // TODO Auto-generated method stub
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

    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        List<BoardGame> filteredList = filtered.toList();
        List<String> filteredLowerList = filteredList.stream()
                .map(game -> game.getName().replaceAll(" ", "").toLowerCase()).toList();
        String lower_str = str.replaceAll(" ", "").toLowerCase();
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
        } else if (filteredLowerList.contains(lower_str)) {
            int index = filteredLowerList.indexOf(lower_str);
            this.listOfGames.add(filteredList.get(index).getName());
        } else if (lower_str.equals("all")) {
            for (BoardGame boardGame : filteredList) {
                this.listOfGames.add(boardGame.getName());
            }
        } else {
            throw new IllegalArgumentException("Invalid input: " + str);
        }
    }

    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        String lower_str = str.replaceAll(" ", "").toLowerCase();
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
        } else if (gamesLowerList.contains(lower_str)) {
            int index = gamesLowerList.indexOf(lower_str);
            this.listOfGames.remove(gamesList.get(index));
        } else if (lower_str.equals("all")) {
            for (String game : gamesList) {
                this.listOfGames.remove(game);
            }
        } else {
            throw new IllegalArgumentException("Invalid input: " + str);
        }
        this.listOfGames.remove(str);
    }


}
