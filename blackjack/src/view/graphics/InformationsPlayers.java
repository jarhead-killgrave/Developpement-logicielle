package view.graphics;

import model.participants.Players;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 * Frame that show the informations of the players during the game.
 * </b>
 *
 * @version 1.0
 */
public class InformationsPlayers extends JFrame {

    /**
     * The listenablePlayers of the game.
     */
    private final Players listenablePlayers;

    /**
     * Creates a new frame with the given listenablePlayers.
     *
     * @param listenablePlayers The listenablePlayers of the game.
     */
    public InformationsPlayers(Players listenablePlayers) {
        super("Informations ListenablePlayers");
        this.listenablePlayers = listenablePlayers;
        this.initComponents();
    }

    private void initComponents() {

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(new PlayersTable(listenablePlayers));
        this.add(scrollPane);
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);

        // Add 3 buttons to the frame.
        JPanel buttonsPanel = new JPanel();
        JButton button = new JButton("Sort by name");
        button.addActionListener(e -> this.listenablePlayers.sortByName());
        buttonsPanel.add(button);
        button = new JButton("Sort by Money");
        button.addActionListener(e -> this.listenablePlayers.sortByMoney());
        buttonsPanel.add(button);
        button = new JButton("Sort by Bet");
        button.addActionListener(e -> this.listenablePlayers.sortByBet());
        buttonsPanel.add(button);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.pack();

    }


}
