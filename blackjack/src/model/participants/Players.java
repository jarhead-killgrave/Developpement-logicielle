package model.participants;

import observer.Listenable;
import observer.ListenableObject;
import observer.Listener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <b>
 * Players permits to manage a list of players of the game.
 * </b>
 * 
 * @version 1.0
 */
public class Players extends ListenableObject implements Listener, Iterable<Player> {

    /**
     * The players of the game.
     */
    private final List<Player> playerList;

    /**
     * Creates a new list of players with the given players.
     *
     * @param players The players of the game.
     * @throws NullPointerException if the players is null.
     */
    public Players(List<Player> players) {
        super("addPlayer", "removePlayer", "clearPlayers", "sortPlayers", "resetPlayers", "updatePlayer");
        if (players == null) {
            throw new NullPointerException("The players cannot be null.");
        }
        this.playerList = players;
    }

    /**
     * Creates a new list of players with the given players.
     *
     * @param playerList The players of the game.
     * @throws NullPointerException if the players is null.
     */
    public Players(Players playerList) {
        this(new ArrayList<>(playerList.getPlayerList()));
    }

    /**
     * Creates a new list of players.
     */
    public Players() {
        this(new ArrayList<>());
    }

    /**
     * Returns the player at the given index.
     *
     * @param index The index of the player.
     * @return The player at the given index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Player get(int index) {
        return this.playerList.get(index);
    }

    /**
     * Returns the number of players.
     *
     * @return The number of players.
     */
    public int size() {
        return this.playerList.size();
    }

    /**
     * Adds the given player to the list.
     *
     * @param player The player to add.
     * @return True if the player was added.
     * @throws NullPointerException if the player is null.
     */
    public boolean add(Player player) {
        if (player == null) {
            throw new NullPointerException("The player cannot be null.");
        }
        player.addListener(this);
        boolean added = this.playerList.add(player);
        super.notifyListeners("addPlayer", player);
        return added;
    }

    /**
     * Returns the players of the game.
     *
     * @return The players of the game.
     */
    public List<Player> getPlayerList() {
        return this.playerList;
    }

    /**
     * Removes the given player from the list.
     *
     * @param player The player to remove.
     * @return True if the player was removed.
     * @throws NullPointerException if the player is null.
     */
    public boolean remove(Player player) {
        if (player == null) {
            throw new NullPointerException("The player cannot be null.");
        }
        boolean removed = this.playerList.remove(player);
        if (removed) {
            player.removeListener(this);
        }
        super.notifyListeners("removePlayer", player);
        return removed;
    }

    /**
     * Index of the given player.
     *
     * @param player The player to find.
     * @return The index of the given player.
     */
    public int indexOf(Player player) {
        return this.playerList.indexOf(player);
    }

    /**
     * Sorts the players by their money.
     */
    public void sortByMoney() {
        this.playerList.sort(Comparator.comparingDouble(Player::getMoney));
    }

    /**
     * Sorts the players by their name.
     */
    public void sortByName() {
        this.sort(Comparator.comparing(Player::getName));
    }

    /**
     * Sorts the players by their bet.
     */
    public void sortByBet() {
        this.sort(Comparator.comparingDouble(Player::getBet));
    }

    public void sort(Comparator<? super Player> c) {
        this.playerList.sort(c);
        super.notifyListeners("sortPlayers");
    }

    public boolean removeIf(Predicate<? super Player> filter) {
        boolean removed = false;
        for (Player player : this.playerList) {
            if (filter.test(player)) {
                this.remove(player);
            }
        }

        return removed;
    }

    public Stream<Player> stream() {
        return this.playerList.stream();
    }

    public Stream<Player> parallelStream() {
        return this.playerList.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super Player> action) {
        this.playerList.forEach(action);
    }

    /**
     * Resets all the players.
     */
    public void reset() {
        this.forEach(Player::reset);
        super.notifyListeners("resetPlayers");
    }

    @Override
    public void notify(Listenable listenable, String event, Object... args) {
        super.notifyListeners("updatePlayer", listenable);
    }


    @Override
    public Iterator<Player> iterator() {
        return this.playerList.iterator();
    }

}