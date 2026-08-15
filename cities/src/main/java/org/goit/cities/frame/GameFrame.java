package org.goit.cities.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame {

    public static final String COMPUTER_LABEL = "Комп'ютер";
    public static final String NEXT_STEP = "Зробити хід";
    public static final String ENTER_CITY_NAME = "Введіть назву міста";

    public GameFrame() {
        setTitle("Міста");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField field = new JTextField(20);

        JLabel label = new JLabel(ENTER_CITY_NAME);

//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
//        topPanel.add(field, BorderLayout.CENTER);
//        topPanel.add(label, BorderLayout.EAST);

//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel computerLabel = new JLabel(COMPUTER_LABEL);

        JButton nextButton = new JButton(NEXT_STEP);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                GameFrame second = new GameFrame();
//                second.setVisible(true);
            }
        });
//        bottomPanel.add(nextButton, BorderLayout.CENTER);
//        bottomPanel.add(computerLabel, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0; gbc.weighty = 0;
        mainPanel.add(field, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1; gbc.weighty = 0;
        mainPanel.add(label, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0; gbc.weighty = 1;
        mainPanel.add(nextButton, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1; gbc.weighty = 1;
        mainPanel.add(computerLabel, gbc);

//        mainPanel.add(topPanel);
//        mainPanel.add(Box.createRigidArea(new Dimension(0, 1)));
//        mainPanel.add(bottomPanel);

        add(mainPanel);
    }

}
