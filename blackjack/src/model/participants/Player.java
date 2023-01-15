package model.participants;

import model.cards.Deck;
import model.cards.Hand;
import model.evaluation.BlackjackCardEvaluator;

/**
 * <b>
 * Player is a participant of the game that can bet.
 * </b>
 *
 * @version 1.0
 */
public class Player extends Participant {

    /**
     * The money of the player.
     */
    private double money;

    /**
     * The bet of the player.
     */
    private double bet;


    /**
     * Creates a new player
     *
     * @param name      The name of the player.
     * @param evaluator The evaluator of the player.
     * @param money     The money of the player.
     * @param bet       The bet of the player.
     * @param hand      The hand of the player.
     * @param canPlay   Boolean that indicates if the player can play.
     */
    public Player(String name, BlackjackCardEvaluator evaluator, int money, int bet, Hand hand, boolean canPlay, boolean isHisTurn) {
        super(name, hand, evaluator, canPlay, isHisTurn);
        this.money = money;
        this.bet = bet;
        super.addOperation("setMoney", "setBet");
    }

    /**
     * Creates a new player with the given name and money.
     *
     * @param name      The name of the player.
     * @param evaluator The evaluator of the player.
     * @param money     The money of the player.
     */
    public Player(String name, BlackjackCardEvaluator evaluator, int money) {
        this(name, evaluator, money, 0, new Hand(), true, false);
    }

    /**
     * Returns the money of the player.
     *
     * @return the money of the player.
     */
    public double getMoney() {
        return this.money;
    }

    /**
     * Updates the money of the player.
     *
     * @param money The new money of the player.
     * @throws IllegalArgumentException if the money is negative.
     */
    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("The money can't be negative.");
        }
        this.money = money;
        super.notifyListeners("setMoney", money);
    }

    /**
     * Increases the money of the player.
     *
     * @param money The money to add.
     * @throws IllegalArgumentException if the money is negative.
     */
    public void increaseMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("You can't add a negative amount of money.");
        }
        this.setMoney(this.getMoney() + money);
    }

    /**
     * Decreases the money of the player.
     *
     * @param money The money to remove.
     * @throws IllegalArgumentException if the money is negative.
     */
    public void decreaseMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("You can't remove a negative amount of money.");
        }
        this.setMoney(this.getMoney() - money);
    }


    /**
     * Returns the bet of the player.
     *
     * @return The bet of the player.
     */
    public double getBet() {
        return bet;
    }

    /**
     * Sets the bet of the player.
     *
     * @param bet The bet of the player.
     * @throws IllegalArgumentException if the bet is negative.
     * @throws IllegalArgumentException if the bet is greater than the money.
     */
    public void setBet(double bet) {
        if (bet < 0) {
            throw new IllegalArgumentException("The bet cannot be negative.");
        }
        if (bet > getMoney()) {
            throw new IllegalArgumentException("The bet cannot be greater than the money.");
        }
        this.bet = bet;
        super.notifyListeners("setBet", bet);
    }

    /**
     * Increases the bet of the player.
     *
     * @param bet The bet to increase.
     * @throws IllegalArgumentException if the bet is negative.
     */
    public void increaseBet(int bet) {
        if (bet < 0) {
            throw new IllegalArgumentException("The bet cannot be negative.");
        }
        setBet(getBet() + bet);
    }

    /**
     * Decreases the bet of the player.
     *
     * @param bet The bet to decrease.
     * @throws IllegalArgumentException if the bet is negative.
     */
    public void decreaseBet(int bet) {
        if (bet < 0) {
            throw new IllegalArgumentException("The bet cannot be negative.");
        }
        setBet(getBet() - bet);
    }

    /**
     * Doubles the bet of the player.
     */
    public void doubleBet() {
        setBet(getBet() * 2);
    }

    /**
     * Returns a string representation of the player.
     *
     * @return A string representation of the player.
     */
    @Override
    public String toString() {
        return "Player{" + "name=" + getName() + ", money=" + getMoney() + ", bet=" + bet + ", hand=" + getHand() + ", canPlay=" + canPlay() + ", isHisTurn=" + isHisTurn() + '}';
    }

    @Override
    public boolean isPlayer() {
        return true;
    }


    /**
     * Hit a card to the player from the given deck.
     *
     * @param deck The deck to hit the card from.
     * @return boolean that indicates if the player can play again.
     * @throws NullPointerException if the deck is null.
     */
    public boolean hit(Deck deck) {
        if (deck == null) {
            throw new NullPointerException("The deck cannot be null.");
        }
        this.takeCard(deck);

        if (this.isBusted()) {
            return this.stand();
        }
        return true;
    }

    /**
     * Double the bet of the player and hit a card to the player from the given deck.
     *
     * @param deck The deck to hit the card from.
     * @return boolean that
     * @throws NullPointerException if the deck is null.
     */
    public boolean doubleHit(Deck deck) {
        if (deck == null) {
            throw new NullPointerException("The deck cannot be null.");
        }
        this.doubleBet();
        this.takeCard(deck);
        return this.stand();
    }

    /**
     * Stand the player
     *
     * @return boolean that indicates if the player can play again.
     */
    public boolean stand() {
        setHisTurn(false);
        setCanPlay(false);
        return false;
    }

    /**
     * The player bets the given amount of money.
     *
     * @param bet The amount of money to bet.
     * @throws IllegalArgumentException if the bet is negative.
     */
    public void placeBet(double bet) {
        if (bet < 0) {
            throw new IllegalArgumentException("The bet cannot be negative.");
        }
        // Set the bet
        setBet(bet);
        // Decrease the money
        this.decreaseMoney(bet);
    }

    /**
     * Resets the player.
     */
    @Override
    public void reset() {
        super.reset();
    }


}
