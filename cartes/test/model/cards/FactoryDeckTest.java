package model.cards;

import junit.framework.*;

import java.util.List;

import model.card.Card;
import model.card.Rank;
import model.card.Suit;

public class FactoryDeckTest extends TestCase{
    /**
     * Method under test: {@link FactoryDeck#createDeck52()}
     */
    public void testCreateDeck52() {
        List<Card> cards = FactoryDeck.createDeck52().getCards();
        assertEquals(48, cards.size());
        Card getResult = cards.get(46);
        assertEquals(Suit.SPADES, getResult.getSuit());
        assertEquals(Rank.KING, getResult.getRank());
        Card getResult1 = cards.get(1);
        assertEquals(Suit.CLUBS, getResult1.getSuit());
        assertEquals(Rank.THREE, getResult1.getRank());
        Card getResult2 = cards.get(0);
        assertEquals(Suit.CLUBS, getResult2.getSuit());
        assertEquals(Rank.TWO, getResult2.getRank());
        Card getResult3 = cards.get(47);
        assertEquals(Suit.SPADES, getResult3.getSuit());
        assertEquals(Rank.ACE, getResult3.getRank());
        Card getResult4 = cards.get(2);
        assertEquals(Suit.CLUBS, getResult4.getSuit());
        assertEquals(Rank.FOUR, getResult4.getRank());
        Card getResult5 = cards.get(45);
        assertEquals(Suit.SPADES, getResult5.getSuit());
        assertEquals(Rank.QUEEN, getResult5.getRank());
    }

    /**
     * Method under test: {@link FactoryDeck#createDeck32()}
     */
    public void testCreateDeck32() {
        List<Card> cards = FactoryDeck.createDeck32().getCards();
        assertEquals(28, cards.size());
        Card getResult = cards.get(26);
        assertEquals(Suit.SPADES, getResult.getSuit());
        assertEquals(Rank.KING, getResult.getRank());
        Card getResult1 = cards.get(1);
        assertEquals(Suit.CLUBS, getResult1.getSuit());
        assertEquals(Rank.EIGHT, getResult1.getRank());
        Card getResult2 = cards.get(0);
        assertEquals(Suit.CLUBS, getResult2.getSuit());
        assertEquals(Rank.SEVEN, getResult2.getRank());
        Card getResult3 = cards.get(27);
        assertEquals(Suit.SPADES, getResult3.getSuit());
        assertEquals(Rank.ACE, getResult3.getRank());
        Card getResult4 = cards.get(2);
        assertEquals(Suit.CLUBS, getResult4.getSuit());
        assertEquals(Rank.NINE, getResult4.getRank());
        Card getResult5 = cards.get(25);
        assertEquals(Suit.SPADES, getResult5.getSuit());
        assertEquals(Rank.QUEEN, getResult5.getRank());
    }
}

