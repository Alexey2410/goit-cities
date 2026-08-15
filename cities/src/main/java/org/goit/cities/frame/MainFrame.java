package org.goit.cities.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public static final String CHILDERN_GAME_INTITATION = "Вітаємо вас у грі дитинства і всіх розумників!";
    public static final String COMFIRMATION = "OK";
    public static final String MAIN_TILE = "Вітаємо";

    public MainFrame(){
        setTitle(MAIN_TILE);
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel(CHILDERN_GAME_INTITATION);

        JButton openButton = new JButton(COMFIRMATION);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame second = new GameFrame();
                second.setVisible(true);
            }
        });
//        panel.setLayout(new GridLayout(1, 2));

//        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.add(label, BorderLayout.CENTER);
        topPanel.add(openButton, BorderLayout.EAST);

//        panel.add(label);
//        panel.add(openButton);

//        add(panel);
        add(topPanel);
    }

}
