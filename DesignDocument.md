# Board Game Arena Planner Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram 

Place your class diagrams below. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

### Provided Code

Provide a class diagram for the provided code as you read through it.  For the classes you are adding, you will create them as a separate diagram, so for now, you can just point towards the interfaces for the provided code diagram.
```mermaid
---
BGArenaPlanner
---
classDiagram
    direction TB
    class BGArenaPlanner {
        - static final String DEFAULT_COLLECTION
        - BGArenaPlanner()
        + static void main(String[] args)
    }

    class IPlanner {
        + filter(String filter) Stream~BoardGame~
        + filter(String filter, GameData sortOn) Stream~BoardGame~
        + filter(String filter, GameData sortOn, boolean ascending) Stream~BoardGame~
    }

    class Planner {
        + Planner(Set~BoardGame~ games)
        + filter(String filter) Stream~BoardGame~
        + filter(String filter, GameData sortOn) Stream~BoardGame~
        + filter(String filter, GameData sortOn, boolean ascending) Stream~BoardGame~
        + reset() void
    }

    class IGameList {
        + String ADD_ALL
        + List~String~ getGameNames()
        + clear() void
        + count() int
        + saveGame(String filename) void
        + addToList(String str, Stream~BoardGame~ filtered) void
        + removeFromList(String str) void
    }

    class GameList {
        + GameList()
        + getGameNames() List~String~
        + clear() void
        + count() int
        + saveGame(String filename) void
        + addToList(String str, Stream~BoardGame~ filtered) void
        + removeFromList(String str) void
    }

    class ConsoleApp {
        - static final Scanner IN
        - static final String DEFAULT_FILENAME
        - static final Random RND
        - Scanner current
        - final IGameList gameList
        - final IPlanner planner
        + ConsoleApp(IGameList gameList, IPlanner planner)
        + start() void
        - randomNumber() void
        - processHelp() void
        - processFilter() void
        - static printFilterStream(Stream~BoardGame~ games, GameData sortON) void
        - processListCommands() void
        - printCurrentList() void
        - nextCommand() ConsoleText
        - remainder() String
        - static getInput(String format, Object... args) String
        - static printOutput(String format, Object... output) void
    }

    class ConsoleText {
        WELCOME, HELP, INVALID, GOODBYE, PROMPT,NO_FILTER, NO_GAMES_LIST,
        FILTERED_CLEAR, LIST_HELP, FILTER_HELP,INVALID_LIST, EASTER_EGG,
        CMD_EASTER_EGG, CMD_EXIT, CMD_HELP, CMD_QUESTION, CMD_FILTER,
        CMD_LIST,CMD_SHOW, CMD_ADD, CMD_REMOVE, CMD_CLEAR, CMD_SAVE,
        CMD_OPTION_ALL, CMD_SORT_OPTION, CMD_SORT_OPTION_DIRECTION_ASC,
        CMD_SORT_OPTION_DIRECTION_DESC
        - static final Properties CTEXT
        + toString() String
        + fromString(String str) ConsoleText
    }

    class GamesLoader {
        - static final String DELIMITER
        - GamesLoader()
        + static loadGamesFile(String filename) Set~BoardGame~
        - static toBoardGame(String line, Map~GameData, Integer~ columnMap) BoardGame
        - static processHeader(String header) Map~GameData, Integer~
    }

    class BoardGame {
        - final String name
        - final int id
        - final int minPlayers
        - final int maxPlayers
        - final int maxPlayTime
        - final int minPlayTime
        - final double difficulty
        - final int rank
        - final double averageRating
        - final int yearPublished
        + BoardGame(String name, int id, int minPlayers, int maxPlayers, int minPlayTime, int maxPlayTime, double difficulty, int rank, double averageRating, int yearPublished)
        + getName() String
        + getId() int
        + getMinPlayers() int
        + getMaxPlayers() int
        + getMaxPlayTime() int
        + getMinPlayTime() int
        + getDifficulty() double
        + getRank() int
        + getRating() double
        + getYearPublished() int
        + toStringWithInfo(GameData col) String
        + toString() String
        + equals(Object obj) boolean
        + hashCode() int
        + static main(String[] args) void
    }

    class GameData {
        - final columnName String
        NAME("objectname"), ID("objectid") ,
        RATING("average"), DIFFICULTY("avgweight") ,
        RANK("rank"), MIN_PLAYERS("minplayers"), MAX_PLAYERS("maxplayers") ,
        MIN_TIME("minplaytime"), MAX_TIME("maxplaytime"), YEAR("yearpublished")
        + GameData(String columnName)
        + getColumnName() String
        + static fromColumnName(String columnName) GameData
        + static fromString(String name) GameData
    }

    class Operations {
        - final String operator
        EQUALS("=="), NOT_EQUALS("!="), GREATER_THAN("&gt;"), LESS_THAN("&lt;"), GREATER_THAN_EQUALS("&gt;=") ,
        LESS_THAN_EQUALS("&lt;="), CONTAINS("~=")
        + Operations(String operator)
        + getOperator() String
        + static fromOperator(String operator) Operations
        + static getOperatorFromStr(String str) Operations
    }

    <<interface>> IPlanner
    <<interface>> IGameList
    <<enum>> ConsoleText
    <<enum>> GameData
    <<enum>> Operations

    BGArenaPlanner o-- ConsoleApp
    BGArenaPlanner --> IGameList
    ConsoleApp o-- IPlanner
    ConsoleApp o-- IGameList
    BGArenaPlanner --> IPlanner
    BGArenaPlanner --> GamesLoader
    IGameList <|.. GameList : implements
    IPlanner <|.. Planner : implements
    IPlanner --> Operations : uses
    ConsoleApp *-- ConsoleText
    BoardGame <-- GamesLoader : creates
    GamesLoader --> GameData : uses
    ConsoleApp --> GameData : uses
    BoardGame --> GameData : uses
    Planner o-- BoardGame

```


### Your Plans/Design

Create a class diagram for the classes you plan to create. This is your initial design, and it is okay if it changes. Your starting points are the interfaces. 
```mermaid
---
BGArenaPlanner
---
classDiagram
    class Planner {

    }
    class Filter {
        - String field
        - Operations operator
        - int value
        + getField() String
        + getOperator() Operations
        + getValue() int
        + doFilter(Stream~BoardGame~) Stream~BoardGame~
    }
    class Sort {
        - String sortBy
        - String inOrder
        + getSortBy() String
        + getInOrder() String
        + doSort(Stream~BoardGame~) Stream~BoardGame~
    }

    Planner o-- Filter
    Planner o-- Sort
```




## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test Filter constructor
2. Test Filter.getField() returns corresponding value
3. Test Filter.getOperator() returns corresponding value
4. Test Filter.getValue() returns corresponding value
5. Test Filter.doFilter() in happy path that returns corresponding value (one and multiple filters)
6. Test Filter.doFilter() with empty input
7. Test Filter.doFilter() without valid return value
8. Test Filter.doFilter() with duplicate filters
9. Test Filter.doFilter() with invalid filter fields
10. Test Sort constructor
11. Test Sort.getSortBy() returns corresponding value
12. Test Sort.getInOrder() returns corresponding value
13. Test Sort.doSort() in happy path that returns corresponding value (with or without sortBy, inOrder)
14. Test Sort.doSort() with empty input
15. Test Sort.doSort() without valid return value
16. Test Sort.doSort() with duplicate arguments
17. Test Sort.doSort() with invalid field value




## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

For the final design, you just need to do a single diagram that includes both the original classes and the classes you added. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.




## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning to information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 
