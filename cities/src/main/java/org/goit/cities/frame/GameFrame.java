package org.goit.cities.frame;

import org.apache.commons.lang3.StringUtils;
import org.goit.cities.processor.Game;
import org.goit.cities.processor.Gamiable;
import org.goit.cities.processor.Player;
import org.goit.cities.processor.WordFinder;
import org.goit.cities.reader.JsonCityReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameFrame extends JFrame implements Gamiable {

    public static final String COMPUTER_LABEL = "Комп'ютер";
    public static final String NEXT_STEP = "Зробити хід";
    public static final String ENTER_CITY_NAME = "Введіть назву міста";
    public static final String END_GAME_MODAL_TITLE = "Гра завершена";
    public static final String END_GAME_INFO_LABEL = "";
    public static final String BUTTON_CLOSE = "Закрити";

    public static final String END_WORD = "здаюсь";

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
                if(StringUtils.isNotEmpty(enteredValue) && END_WORD.toLowerCase().equals(enteredValue.toLowerCase()))  { showFinishDialog(GameFrame.this, game, false); return;}
                if(!wordFinder.checkWord(enteredValue, game))
                    field.setBackground(Color.RED);
                else{
                    field.setBackground(Color.WHITE);
                    game.caclulateStep(enteredValue);
                    field.setText("");
                    if(!game.getPlayers().get(game.getCurrentPlayerIndex()).getHuman()){
                        String newWord = wordFinder.findNext(enteredValue, game);
                        if(newWord == null){ showFinishDialog(GameFrame.this, game, true); return;}
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

    private static void showFinishDialog(JFrame parent, Game game, boolean human) {
        JDialog dialog = new JDialog(parent, END_GAME_MODAL_TITLE, true);
        dialog.setSize(200, 200);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new FlowLayout());

        StringBuffer buf = new StringBuffer();
        game.getPlayers().stream()
                .filter(Player::getHuman)
                .forEach(player -> buf.append("Гравець зробив " + player.getCount() + " ходів "));
        StringBuffer compbuf = new StringBuffer();
        game.getPlayers().stream()
                .filter(pl -> !pl.getHuman())
                .forEach(player -> compbuf.append("Комп'ютер зробив " + player.getCount() + "ходів "));

        JLabel label1 = new JLabel("Гра завершена.");
        JLabel label2 = new JLabel(
                buf.toString()
        );
        JLabel label3 = new JLabel(
                compbuf.toString()
        );
        JLabel label4 = new JLabel(
                "Переміг " + (human? "Гравець": "Комп'ютер")
        );
        JButton closeButton = new JButton(BUTTON_CLOSE);
        closeButton.addActionListener(e -> {
            dialog.dispose();
            parent.dispose();
        });

        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.dispose();
                dialog.dispose();
            }
        });

        dialog.add(label1);
        dialog.add(label2);
        dialog.add(label3);
        dialog.add(label4);
        dialog.add(closeButton);
        dialog.setVisible(true);
    }

}
