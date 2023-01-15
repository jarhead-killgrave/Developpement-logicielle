package model.card;

/**
 * <b>
 * Classic card comparator permits to compare two cards according to the natural order of classic playing cards.
 * </b>
 *
 * <p>
 * The natural order of classic playing cards is defined by the following rules:
 * </p>
 * <ol>
 *     <li>
 *         The cards are ordered by their suit. So the order is: clubs, diamonds, hearts, spades.
 *     </li>
 *     <li>
 *         The cards of the same suit are ordered by their value. So the order is: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A.
 *         The order of the values is the same as the order of the ranks.
 *     </li>
 * </ol>
 * <p>
 *     So we use a classic card evaluator to evaluate the value of the cards.
 * </p>
 *
 * @version 1.0
 */
public class ClassicCardComparator implements CardComparator {

    /**
     * The card evaluator to evaluate the cards.
     */
    private final CardEvaluator cardEvaluator;

    /**
     * Creates a new classic card comparator with the given card evaluator.
     *
     * @param cardEvaluator The card evaluator to evaluate the cards.
     * @throws NullPointerException if the card evaluator is null.
     */
    public ClassicCardComparator(CardEvaluator cardEvaluator) {
        if (cardEvaluator == null) {
            throw new NullPointerException("The card evaluator cannot be null.");
        }
        this.cardEvaluator = cardEvaluator;
    }

    /**
     * Returns the evaluator of the cards.
     *
     * @return the evaluator of the cards.
     */
    @Override
    public CardEvaluator getCardEvaluator() {
        return this.cardEvaluator;
    }

    /**
     * Compares two cards according to the natural order of classic playing cards.
     *
     * @param card1 The first card to compare.
     * @param card2 The second card to compare.
     * @return A negative integer, zero, or a positive integer as the first card is less than, equal to, or greater than the second card.
     */
    @Override
    public int compare(Card card1, Card card2) {
        // Compare the suits of the cards (the order is: clubs, diamonds, hearts, spades).
        int result = String.valueOf(card1.getSuit().getLetter()).compareTo(String.valueOf(card2.getSuit().getLetter()));

        // If the suits are equal, compare the values of the cards (the order is: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A).
        if (result == 0) {
            result = cardEvaluator.evaluate(card1) - cardEvaluator.evaluate(card2);
        }

        return result;
    }
}
