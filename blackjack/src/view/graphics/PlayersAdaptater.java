package view.graphics;

import model.participants.Player;
import model.participants.Players;
import observer.Listenable;
import observer.Listener;

import javax.swing.table.AbstractTableModel;

/**
 * <b>
 * Adapter that permits to adapt the listenablePlayers to the JTable.
 * </b>
 *
 * @version 1.0
 */
public class PlayersAdaptater extends AbstractTableModel implements Listener {

    /**
     * The listenablePlayers of the game.
     */
    private final Players listenablePlayers;

    /**
     * The columns of the table.
     */
    private final String[] columns = {"Name", "Money", "Bet"};

    /**
     * Creates a new adapter with the given listenablePlayers.
     *
     * @param listenablePlayers The listenablePlayers of the game.
     */
    public PlayersAdaptater(Players listenablePlayers) {
        this.listenablePlayers = listenablePlayers;
        this.listenablePlayers.addListener("updatePlayer", this);
        this.listenablePlayers.addListener("sortPlayers", this);
    }

    /**
     * Returns the number of rows of the table.
     *
     * @return The number of rows of the table.
     */
    @Override
    public int getRowCount() {
        return listenablePlayers.size();
    }

    /**
     * Returns the number of columns of the table.
     *
     * @return The number of columns of the table.
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Returns the name of the column at the given index.
     *
     * @param columnIndex The index of the column.
     * @return The name of the column at the given index.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    /**
     * Returns the value at the given row and column.
     *
     * @param rowIndex    The index of the row.
     * @param columnIndex The index of the column.
     * @return The value at the given row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = listenablePlayers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return player.getName();
            case 1:
                return player.getMoney();
            case 2:
                return player.getBet();
            default:
                return null;
        }
    }

    /**
     * Returns the class of the column at the given index.
     *
     * @param columnIndex The index of the column.
     * @return The class of the column at the given index.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public void notify(Listenable listenable, String property, Object... args) {
        if (property.equals("updatePlayer")) {
            Player player = (Player) args[0];
            int index = this.listenablePlayers.indexOf(player);
            this.fireTableRowsUpdated(index, index);
        }
        if (property.equals("sortPlayers")) {
            this.fireTableDataChanged();
        }
    }


}
