package org.goit.cities.processor;

import org.apache.commons.lang3.StringUtils;
import org.goit.cities.model.City;

import java.util.List;
import java.util.Optional;

public class WordFinder {


    public boolean checkWord(String currentWord, Game game){
        Character lastchar = getLastCharacter(game.getPrevWord());

        //check first letter is equal with last letter prev word
        if(game.getPrevWord() != null)
            if(lastchar != null && currentWord.toLowerCase().charAt(0) != lastchar) return false;

        //check does current word in base
        Optional<City> anycity = game.getAllowedCites().stream()
                .filter(city -> city.getName().equals(currentWord))
                .findAny();


        if(anycity.isPresent()){
        // check if word already used
            Optional<City> usedCity = game.getUsedCites().stream()
                    .filter(city -> city.getName().equals(currentWord))
                    .findAny();
            if(usedCity.isEmpty())
                game.getUsedCites().add(anycity.get());
            return usedCity.isEmpty();// ok if word exist and not used
        }

        return anycity.isPresent();// in case false, word does not exist
    }


    public String findNext(String currentWord, Game game){
        Character lastchar = getLastCharacter(currentWord);
        if (lastchar == null) return null;


        String anycity = null;

        for (City city: game.getAllowedCites()){
            if(city.getName().toLowerCase().charAt(0) == lastchar)
                if(!game.getUsedCites().contains(city)){
                    anycity = city.getName();
                    game.getUsedCites().add(city);
                    break;
                }

        }

        return anycity;
    }

    private static Character getLastCharacter(String currentWord) {
        if(StringUtils.isEmpty(currentWord)) return null;

        char lastchar =  currentWord.toLowerCase().charAt(currentWord.length()-1);
        if(lastchar == 'ь' || lastchar == 'и') {// exclude 'ь' and 'и' letters from search new city
            if (currentWord.length() == 1) return null;
            lastchar =  currentWord.toLowerCase().charAt(currentWord.length()-2);
        }
        return lastchar;
    }

}
