package org.goit.cities.processor;

import org.goit.cities.model.City;
import org.goit.cities.reader.JsonCityReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<City> allowedCites;
    private List<City> usedCites;
    private List<Player> players;

    private Integer currentPlayerIndex;

    private String prevWord;

    private JsonCityReader reader;

    public Game(JsonCityReader reader) {
        this.allowedCites = new ArrayList<>();
        this.usedCites = new ArrayList<>();
        this.players = new ArrayList<>();
        this.reader = reader;
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
        currentPlayer.setCount(++count);
        setPrevWord(newWord);

        if(currentPlayerIndex < getPlayers().size() - 1)
            currentPlayerIndex++ ;
        else
            currentPlayerIndex = 0 ;
    }

    public Game initGame() throws IOException {
        this.allowedCites = new ArrayList<>();
        this.usedCites = new ArrayList<>();
        this.players = new ArrayList<>();

        setAllowedCites(
                reader.read(new File(reader.getPath()))
        );

        Player player = new Player(0, true);
        setPlayers(List.of(
                player,
                new Player(0, false)
        ));//  move initialization of players
        setCurrentPlayerIndex(0);
        return this;
    }
}
