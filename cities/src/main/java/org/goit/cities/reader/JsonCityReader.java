package org.goit.cities.reader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.goit.cities.model.City;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonCityReader {

    public static final String FILE_NAME = "cities_ukraine.json";

    private String path;

    public JsonCityReader(String path) {
        this.path = path;
    }

    public List<City> read(File jsonFile) throws IOException {

        List<City> cities = new ArrayList<>();

        FileReader reader = new FileReader(jsonFile);

            cities = getCities(reader);

        reader.close();
        return cities;
    }

    public List<City> read(String json){

        StringReader reader = new StringReader(json);
        List<City> cities = getCities(reader);

        return cities;
    }

    private static List<City> getCities(Reader reader) {
        Gson gson = new Gson();

        List<City> cities = new ArrayList<>();
        Type typeOfT = TypeToken.getParameterized(List.class, City.class).getType();
        cities = gson.fromJson(reader, typeOfT);

        return cities;
    }

    public String getPath() {
        return path;
    }

}
