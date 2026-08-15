package org.goit.cities.reader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.goit.cities.model.City;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonCityReader {

    public static final String fileName = "cities_ukraine.json";

    public List<City> read(){
        Gson gson = new Gson();

        List<City> cities = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName)) {
            Type typeOfT = TypeToken.getParameterized(List.class, City.class).getType();
            cities = gson.fromJson(reader, typeOfT);

        } catch (IOException e) {
            //e.printStackTrace();// ignore. use empty list
        }
         return cities;
    }
}
