package org.goit.cities.frame;

import org.goit.cities.processor.Game;
import org.goit.cities.processor.Gamiable;
import org.goit.cities.processor.WordFinder;
import org.goit.cities.reader.JsonCityReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame implements Gamiable {

    public static final String COMPUTER_LABEL = "Комп'ютер";
    public static final String NEXT_STEP = "Зробити хід";
    public static final String ENTER_CITY_NAME = "Введіть назву міста";

    private Game game;

    public GameFrame() {
        setTitle("Міста");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        WordFinder wordFinder = new WordFinder();

        JTextField field = new JTextField(20);
        field.setBackground(Color.WHITE);

        JLabel label = new JLabel(ENTER_CITY_NAME);

        JLabel computerLabel = new JLabel(COMPUTER_LABEL);

        JButton nextButton = new JButton(NEXT_STEP);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredValue = field.getText();
                if(!wordFinder.checkWord(enteredValue, game))
                    field.setBackground(Color.RED);
                else{
                    field.setBackground(Color.WHITE);
                    game.caclulateStep(enteredValue);
                    field.setText("");
                    if(!game.getPlayers().get(game.getCurrentPlayerIndex()).getHuman()){
                        String newWord = wordFinder.findNext(enteredValue, game);
                        computerLabel.setText(COMPUTER_LABEL + " : " + newWord);
                        game.caclulateStep(newWord);
                    }
                }
            }
        });

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


        add(mainPanel);
    }

    @Override
    public void acceptGame(Game game) {
        this.game = game;
    }
}
