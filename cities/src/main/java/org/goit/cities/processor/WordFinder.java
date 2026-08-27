package org.goit.cities.processor;

import org.apache.commons.lang3.StringUtils;
import org.goit.cities.model.City;

import java.util.List;
import java.util.Optional;

public class WordFinder {

    private Game game;

    public WordFinder(Game game) {
        this.game = game;
    }

    public boolean checkWord(String currentWord){
        if(StringUtils.isEmpty(currentWord)) return false;
        Character lastchar = getLastCharacter(game.getPrevWord());

        //check first letter is equal with last letter prev word
        if(game.getPrevWord() != null)
            if(lastchar != null && currentWord.toLowerCase().charAt(0) != lastchar) return false;

        //check does current word in base
        Optional<City> anycity = game.getAllowedCities().stream()
                .filter(city -> city.getName().toLowerCase().equals(currentWord.replaceAll("'","’").toLowerCase()))
                .findAny();


        if(anycity.isPresent()){
        // check if word already used
            Optional<City> usedCity = game.getUsedCities().stream()
                    .filter(city -> city.getName().toLowerCase().equals(currentWord.replaceAll("'","’").toLowerCase()))
                    .findAny();
            if(usedCity.isEmpty())
                game.getUsedCities().add(anycity.get());
            return usedCity.isEmpty();// ok if word exist and not used
        }

        return anycity.isPresent();// in case false, word does not exist
    }


    public String findNext(String currentWord){
        Character lastchar = getLastCharacter(currentWord);
        if (lastchar == null) return null;


        String anycity = null;

        for (City city: game.getAllowedCities()){
            if(city.getName().toLowerCase().charAt(0) == lastchar)
                if(!game.getUsedCities().contains(city)){
                    anycity = city.getName();
                    game.getUsedCities().add(city);
                    break;
                }

        }

        return anycity;
    }

    private static Character getLastCharacter(String word) {
        if(StringUtils.isEmpty(word)) return null;

        char lastchar =  word.toLowerCase().charAt(word.length()-1);
        if(lastchar == 'ь' || lastchar == 'и') {// exclude 'ь' and 'и' letters from search new city
            if (word.length() == 1) return null;
            lastchar =  word.toLowerCase().charAt(word.length()-2);
        }
        return lastchar;
    }

}
