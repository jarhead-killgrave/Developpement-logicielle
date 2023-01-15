package model;


import model.cards.Deck;
import model.cards.FactoryDeck;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * <b>
 * Shoe is a container of cards.
 * </b>
 */
public class Shoe extends Deck {

    /**
     * The number of decks in the shoe.
     */
    private final int numberOfDecks;

    /**
     * Creates a new shoe with the given number of decks.
     *
     * @param numberOfDecks The number of decks in the shoe.
     */
    public Shoe(int numberOfDecks) {
        super(new ArrayList<>());
        this.numberOfDecks = numberOfDecks;
        IntStream.range(0, numberOfDecks).forEach(i -> this.addCards(FactoryDeck.createDeck52().getCards()));
    }

    /**
     * Returns the number of decks in the shoe.
     *
     * @return the number of decks in the shoe.
     */
    public int getNumberOfDecks() {
        return numberOfDecks;
    }

}
