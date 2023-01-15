package model.cards;

import model.card.Card;

import java.util.List;

/**
 * <b>
 *     Mock of the ListOfCards class.
 * </b>
 */
public class ListOfCardsMock extends ListOfCards {

    /**
     * Creates a new list of cards with the given cards.
     *
     * @param cards The cards in the list.
     * @throws NullPointerException if the cards is null.
     */
    public ListOfCardsMock(List<Card> cards) {
        super(cards);
    }

    /**
     * Creates a new list of cards.
     */
    public ListOfCardsMock() {
        super();
    }

}
