package org.goit.cities;

import org.goit.cities.frame.MainFrame;
import org.goit.cities.reader.JsonCityReader;

import javax.swing.*;
import java.awt.*;

public class CitiesGameUI {

    public static void main(String args[]) {

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });

    }
}

