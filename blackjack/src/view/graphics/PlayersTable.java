package view.graphics;

import model.participants.Players;

import javax.swing.*;

/**
 * <b>
 * This class represents a listenablePlayers table.
 * </b>
 *
 * @version 1.0
 */
public class PlayersTable extends JTable {


    /**
     * Creates a new listenablePlayers table with the given listenablePlayers.
     *
     * @param listenablePlayers The listenablePlayers of the game.
     */
    public PlayersTable(Players listenablePlayers) {
        super(new PlayersAdaptater(listenablePlayers));
    }


}
