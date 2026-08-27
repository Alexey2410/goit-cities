package org.goit.cities.processor;

import org.goit.cities.model.City;
import org.goit.cities.reader.JsonCityReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<City> allowedCities;
    private List<City> usedCities;
    private List<Player> players;

    private Integer currentPlayerIndex;

    private String prevWord;

    private JsonCityReader reader;

    public Game(JsonCityReader reader) {
        this.allowedCities = new ArrayList<>();
        this.usedCities = new ArrayList<>();
        this.players = new ArrayList<>();
        this.reader = reader;
    }

    public Game(List<City> allowedCities, List<City> usedCities, List<Player> players) {
        this.allowedCities = allowedCities;
        this.usedCities = usedCities;
        this.players = players;
    }

    public List<City> getAllowedCities() {
        return allowedCities;
    }

    public void setAllowedCities(List<City> allowedCities) {
        this.allowedCities = allowedCities;
    }

    public List<City> getUsedCities() {
        return usedCities;
    }

    public void setUsedCities(List<City> usedCities) {
        this.usedCities = usedCities;
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

    public void calculateStep(String newWord){
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
        this.allowedCities = new ArrayList<>();
        this.usedCities = new ArrayList<>();
        this.players = new ArrayList<>();
        this.prevWord = null;

        setAllowedCities(
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
