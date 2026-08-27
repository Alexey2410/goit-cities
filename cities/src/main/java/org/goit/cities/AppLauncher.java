package org.goit.cities;

import org.goit.cities.frame.GameFrame;
import org.goit.cities.frame.MainFrame;
import org.goit.cities.processor.Game;
import org.goit.cities.processor.WordFinder;
import org.goit.cities.reader.JsonCityReader;

import javax.swing.*;

public class AppLauncher {

    public static void main(String args[]) {

        SwingUtilities.invokeLater(() -> {

            String path = args.length > 0 ? args[0] : JsonCityReader.FILE_NAME;//read from args path to json with cities or use default

            JsonCityReader reader = new JsonCityReader(path);
            Game game = new Game(reader);
            WordFinder wordFinder = new WordFinder(game);
            MainFrame mainFrame = new MainFrame(
                    () -> new GameFrame(game, wordFinder)
            );
            mainFrame.setVisible(true);
        });

    }
}

