package org.goit.cities.processor;

import org.goit.cities.model.City;
import org.goit.cities.reader.JsonCityReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<City> allowedCites;
    private List<City> usedCites;
    private List<Player> players;

    private Integer currentPlayerIndex;

    private String prevWord;

    public Game() {
        this.allowedCites = new ArrayList<>();
        this.usedCites = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public Game(List<City> allowedCites, List<City> usedCites, List<Player> players) {
        this.allowedCites = allowedCites;
        this.usedCites = usedCites;
        this.players = players;
    }

    public List<City> getAllowedCites() {
        return allowedCites;
    }

    public void setAllowedCites(List<City> allowedCites) {
        this.allowedCites = allowedCites;
    }

    public List<City> getUsedCites() {
        return usedCites;
    }

    public void setUsedCites(List<City> usedCites) {
        this.usedCites = usedCites;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }


    public String getPrevWord() {
        return prevWord;
    }

    public void setPrevWord(String prevWord) {
        this.prevWord = prevWord;
    }

    public Integer getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(Integer currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public void caclulateStep(String newWord){
        Player currentPlayer = getPlayers().get(currentPlayerIndex);

        Integer count = currentPlayer.getCount();
        currentPlayer.setCount(count++);
        setPrevWord(newWord);

        if(currentPlayerIndex < getPlayers().size() - 1)
            currentPlayerIndex++ ;
        else
            currentPlayerIndex = 0 ;
    }

    public static Game initGame() {
        Game game = new Game();
        JsonCityReader reader = new JsonCityReader();
        game.setAllowedCites(
                reader.read(new File(JsonCityReader.FILE_NAME))
        );

        //case with only 3 cities in the list
//        game.setAllowedCites(
//                reader.read("[\n" +
//                        "  {\n" +
//                        "    \"name\": \"Бахчисарай\"\n" +
//                        "  },\n" +
//                        "  {\n" +
//                        "    \"name\": \"Інкерман\"\n" +
//                        "  },\n" +
//                        "  {\n" +
//                        "    \"name\": \"Білогірськ\"\n" +
//                        "  }" +
//                        "  ]" )
//        );

        Player player = new Player(0, true);
        game.setPlayers(List.of(
                player,
                new Player(0, false)
        ));// todo move initialization of players
        game.setCurrentPlayerIndex(0);
        return game;
    }
}
