package model.evaluation;

import model.card.Card;
import model.card.CardEvaluator;
import model.card.Rank;

/**
 * <b>
 * BlackjackCardEvaluator is a class that evaluates the value of a card in the game of Blackjack.
 * </b>
 *
 * <p>
 * The value of a card in the game of Blackjack is determined by the rank of the card.
 * If the rank of the card is a number, the value of the card is the number.
 * If the rank of the card is a face card, the value of the card is 10.
 * If the rank of the card is an ace, the value of the card is 11 or 1.
 * </p>
 */
public class BlackjackCardEvaluator implements CardEvaluator {

    /**
     * The value of the ace.
     */
    private final AceValue aceValue;

    /**
     * The constructor of the BlackjackCardEvaluator.
     *
     * @param aceValue The value of the ace.
     */
    public BlackjackCardEvaluator(AceValue aceValue) {
        this.aceValue = aceValue;
    }

    /**
     * The constructor of the BlackjackCardEvaluator.
     */
    public BlackjackCardEvaluator() {
        this(AceValue.ELEVEN);
    }

    /**
     * Returns the value of the ace.
     *
     * @return the value of the ace.
     */
    public AceValue getAceValue() {
        return this.aceValue;
    }


    @Override
    public int evaluate(Card card) {
        if (card.getRank() == Rank.ACE) {
            return aceValue.getValue();
        }
        return Math.min(card.getRank().getValue(), 10);
    }
}
