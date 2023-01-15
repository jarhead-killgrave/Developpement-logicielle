package model.participants;

import model.cards.Deck;
import model.cards.Hand;
import model.evaluation.BlackjackCardEvaluator;

/**
 * <b>
 * Dealer is a participant of the game that can't bet and that his cards is used to compare with the players.
 * </b>
 *
 * @version 1.0
 */
public class Dealer extends Participant {

    /**
     * Creates a new dealer
     *
     * @param name      The name of the dealer.
     * @param hand      The hand of the dealer.
     * @param evaluator The evaluator of the dealer.
     * @param canPlay   Boolean that indicates if the dealer can play.
     */
    public Dealer(String name, Hand hand, BlackjackCardEvaluator evaluator, boolean canPlay, boolean isHisTurn) {
        super(name, hand, evaluator, canPlay, isHisTurn);
    }

    /**
     * Creates a new dealer with the given name and money.
     *
     * @param name      The name of the dealer.
     * @param evaluator The evaluator of the dealer.
     */
    public Dealer(String name, BlackjackCardEvaluator evaluator) {
        this(name, new Hand(), evaluator, true, false);
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    /**
     * Take cards from the deck until the hand is greater than a specific value.
     *
     * @param deck  The deck of cards.
     * @param value The minimum value of the hand.
     */
    public void takeCardsUntil(Deck deck, int value) {
        while (this.getHandValue() < value) {
            this.takeCard(deck);
        }
    }


}
