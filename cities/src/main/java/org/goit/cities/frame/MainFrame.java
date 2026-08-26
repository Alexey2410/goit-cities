package org.goit.cities.frame;

import org.goit.cities.processor.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

public class MainFrame extends JFrame {

    public static final String CHILDERN_GAME_INTITATION = "Вітаємо вас у грі дитинства і всіх розумників!";
    public static final String COMFIRMATION = "OK";
    public static final String MAIN_TILE = "Вітаємо";

    public static final String ERROR_LOAD_TILE = "Помилка загрузки";
    public static final String INFO_MESSAGE = "Файл зі списком міст не знайдено або невірний формат.";
    public static final String CLOSE_BUTTON = "Закрить";

    private GameFrame gameFrame;

    private Supplier<GameFrame> supplier;

    public MainFrame(Supplier<GameFrame> supplier){

        this.supplier = supplier;

        setTitle(MAIN_TILE);
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel(CHILDERN_GAME_INTITATION);

        JButton openButton = new JButton(COMFIRMATION);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame = (GameFrame) supplier.get();
                try {
                    gameFrame.getGame().initGame();

                } catch (IOException ex) {
                    showModal(MainFrame.this);
                    openButton.setEnabled(false);
                    return;
                }
                gameFrame.setVisible(true);

//                openButton.setEnabled(false);//todo enabled after close second window
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

    private void showModal(JFrame parent) {
        JDialog dialog = new JDialog(parent, ERROR_LOAD_TILE, true); // true = modal
        dialog.setSize(350, 100);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new FlowLayout());

        JLabel label = new JLabel(INFO_MESSAGE);
        JButton closeButton = new JButton(CLOSE_BUTTON);
        closeButton.addActionListener(e -> dialog.dispose());

        dialog.add(label);
        dialog.add(closeButton);
        dialog.setVisible(true); // blocks input to parent until closed
    }

}
