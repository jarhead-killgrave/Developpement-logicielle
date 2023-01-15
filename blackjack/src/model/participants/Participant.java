package model.participants;

import model.Constants;
import model.card.Card;
import model.cards.Deck;
import model.cards.Hand;
import model.evaluation.BlackjackCardEvaluator;
import observer.Listenable;
import observer.ListenableObject;
import observer.Listener;

/**
 * <b>
 * A participant is a person or a computer that impact the process of a game.
 * </b>
 *
 * <p>
 * Globally, we can distinguish two types of participants: players and dealers.
 * A player is a person who plays a game. A player can be a human or a computer.
 * A dealer is a person who deals the cards of a game. Currently, the dealer is always a computer.
 * </p>
 *
 * @version 1.0
 */
public abstract class Participant extends ListenableObject implements Listener {


    /**
     * The name of the participant.
     */
    private final String name;

    /**
     * The hand of cards of the participant.
     */
    private final Hand hand;

    /**
     * The evaluator of the participant.
     */
    private final BlackjackCardEvaluator evaluator;


    /**
     * Boolean that indicates if the participant can play.
     */
    private boolean canPlay;

    /**
     * Boolean that indicates if the participant is playing.
     */
    private boolean isHisTurn;

    /**
     * The value of the hand of the participant.
     */
    private int handValue;


    /**
     * Creates a new participant with the given name.
     *
     * @param name      The name of the participant.
     * @param hand      The hand of cards of the participant.
     * @param evaluator The evaluator of the participant.
     * @param canPlay   Boolean that indicates if the participant can play.
     * @throws NullPointerException if the name or the hand is null.
     * @throws NullPointerException if the
     */
    protected Participant(String name, Hand hand, BlackjackCardEvaluator evaluator, boolean canPlay, boolean isHisTurn) {
        super("canPlay", "isHisTurn", "handValue");
        if (name == null) {
            throw new NullPointerException("The name of the participant cannot be null.");
        }

        if (hand == null) {
            throw new NullPointerException("The hand of the participant cannot be null.");
        }

        if (evaluator == null) {
            throw new NullPointerException("The evaluator of the participant cannot be null.");
        }

        this.name = name;
        this.hand = hand;
        this.isHisTurn = isHisTurn;
        this.handValue = hand.evaluate(evaluator);
        this.canPlay = this.handValue < Constants.BLACKJACK_VALUE && canPlay;
        this.evaluator = evaluator;
        this.hand.addListener("addCard", this);
        this.hand.addListener("removeCard", this);
        this.hand.addListener("clearCards", this);
    }

    /**
     * Creates a new participant with the given name.
     *
     * @param name      The name of the participant.
     * @param evaluator The evaluator of the participant.
     */
    protected Participant(String name, BlackjackCardEvaluator evaluator) {
        this(name, new Hand(), evaluator, true, false);
    }


    /**
     * Returns the name of the participant.
     *
     * @return the name of the participant.
     */
    public String getName() {
        return this.name;
    }


    /**
     * Returns the hand of cards of the participant.
     *
     * @return the hand of cards of the participant.
     */
    public Hand getHand() {
        return this.hand;
    }

    /**
     * Checks if the participant can play.
     *
     * @return true if the participant can play, false otherwise.
     */
    public boolean canPlay() {
        return this.canPlay;
    }

    /**
     * Update if the participant can play.
     *
     * @param canPlay if the participant can play.
     */
    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
        super.notifyListeners("canPlay", this.canPlay);
    }


    /**
     * Returns true if the participant can play, false otherwise.
     *
     * @return true if the participant can play, false otherwise.
     */
    public boolean isHisTurn() {
        return this.isHisTurn;
    }

    /**
     * Sets the canPlay attribute of the participant.
     *
     * @param hisTurn The canPlay attribute of the participant.
     */
    public void setHisTurn(boolean hisTurn) {
        this.isHisTurn = hisTurn;
        super.notifyListeners("isHisTurn", hisTurn);
    }


    /**
     * Clears the hand of the participant.
     */
    public void clearHand() {
        this.getHand().clear();
        //super.notifyListeners("handValue");
    }

    /**
     * Resets the participant.
     */
    public void reset() {
        this.clearHand();
        this.setCanPlay(true);
        this.setHisTurn(false);
    }

    /**
     * Evaluates the player's hand.
     *
     * @return the value of the hand
     * @throws NullPointerException if the card evaluator is null.
     */
    public int getHandValue() {

        return this.handValue;
    }

    /**
     * Takes the top card of the deck and adds it to the hand of the participant.
     *
     * @param deck The deck of cards.
     */
    public void takeCard(Deck deck) {
        this.getHand().addCard(deck.deal());
        //super.notifyListeners("handValue");
    }


    /**
     * Returns true if the participant is a player, false otherwise.
     */
    public abstract boolean isPlayer();

    /**
     * Returns true if the participant is busted, false otherwise.
     *
     * @return true if the participant is busted, false otherwise.
     */
    public boolean isBusted() {
        return this.getHandValue() > Constants.BLACKJACK_VALUE;
    }

    /**
     * Checks if the participant has a blackjack.
     */
    public boolean hasBlackjack() {
        return this.getHandValue() == Constants.BLACKJACK_VALUE;
    }

    @Override
    public void notify(Listenable listenable, String operation, Object... data) {

        switch (operation) {
            case "addCard":
                this.handValue += this.evaluator.evaluate((Card) data[0]);
                break;
            case "removeCard":
                this.handValue -= this.evaluator.evaluate((Card) data[0]);
                break;
            case "clearCards":
                this.handValue = 0;
                break;
        }
        this.setCanPlay(this.handValue < Constants.BLACKJACK_VALUE && this.canPlay);
        super.notifyListeners("handValue");
    }

    @Override
    public String toString() {
        // For each attribute of the class, we add the name of the attribute and its value to the string.
        String sb = "Name: " + this.getName() + "\n" +
                "Hand: " + this.getHand() + "\n" +
                "Can play: " + this.canPlay() + "\n" +
                "Is his turn: " + this.isHisTurn() + "\n" +
                "Hand value: " + this.getHandValue() + "\n";
        return sb;
    }

}
