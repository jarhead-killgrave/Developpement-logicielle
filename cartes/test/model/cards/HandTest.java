package model.cards;

import junit.framework.*;

import java.util.ArrayList;
import java.util.List;

import model.card.Card;
import model.card.CardComparator;
import model.card.CardEvaluator;
import model.card.ClassicCardComparator;
import model.card.ClassicCardEvaluator;
import model.card.Rank;
import model.card.Suit;


public class HandTest extends TestCase {

    /**
     * Method under test: {@link Hand#Hand()}
     * Normal case: Creates a new hand.
     */
    public void testConstructor() {
        assertTrue((new Hand()).getCards().isEmpty());
        assertTrue((new Hand(new ArrayList<>())).getCards().isEmpty());
    }

    /**
     * Method under test: {@link Hand#Hand(List)}
     * Normal case: Creates a new hand with the given cards.
     */
    public void testConstructor2() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.CLUBS));
        cards.add(new Card(Rank.TWO, Suit.CLUBS));
        cards.add(new Card(Rank.THREE, Suit.CLUBS));

        assertEquals(cards, (new Hand(cards)).getCards());
    }

    /**
     * Method under test: {@link Hand#Hand(List)}
     * Failure case: The cards is null.
     */
    public void testConstructor2Null() {
        try {
            new Hand(null);
            fail("The cards cannot be null.");
        } catch (NullPointerException e) {
            assertEquals("The cards cannot be null.", e.getMessage());
        }
    }

    /**
     * Method under test: {@link Hand#evaluate(CardEvaluator)}
     * Normal case: Returns the score of the hand.
     */
    public void testEvaluate() {
        Hand hand = new Hand();
        assertEquals(0, hand.evaluate(new ClassicCardEvaluator()));
    }

    /**
     * Method under test: {@link Hand#evaluate(CardEvaluator)}
     * Failure case: The card evaluator is null.
     */
    public void testEvaluate2() {
        Hand hand = new Hand();
        try {
            hand.evaluate(null);
            fail("The card evaluator cannot be null.");
        } catch (NullPointerException e) {
            assertEquals("The card evaluator cannot be null.", e.getMessage());
        }
    }

    /**
     * Method under test: {@link Hand#evaluate(CardEvaluator)}
     * Normal case: Returns the score of the hand.
     */
    public void testEvaluate3() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS));
        hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
        hand.addCard(new Card(Rank.THREE, Suit.CLUBS));
        assertEquals(18, hand.evaluate(new ClassicCardEvaluator()));
    }


    /**
     * Method under test: {@link Hand#getBestCard(CardComparator)}
     */
    public void testGetBestCard() {
        Hand hand = new Hand();
        assertNull(hand.getBestCard(new ClassicCardComparator(new ClassicCardEvaluator())));
    }

    /**
     * Method under test: {@link Hand#getBestCard(CardComparator)}
     * Failure case: The card comparator is null.
     */
    public void testGetBestCard2() {
        Hand hand = new Hand();
        try {
            hand.getBestCard(null);
            fail("The card comparator cannot be null.");
        } catch (NullPointerException e) {
            assertEquals("The card comparator cannot be null.", e.getMessage());
        }
    }

    /**
     * Method under test: {@link Hand#getBestCard(CardComparator)}
     * Normal case: Returns the best card of the hand.
     */
    public void testGetBestCard3() {
        Hand hand = new Hand();
        CardEvaluator cardEvaluator = new ClassicCardEvaluator();
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS));
        hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
        hand.addCard(new Card(Rank.THREE, Suit.CLUBS));
        assertEquals(new Card(Rank.ACE, Suit.CLUBS), hand.getBestCard(new ClassicCardComparator(cardEvaluator)));
    }

    /**
     * Method under test: {@link Hand#getWorstCard(CardComparator)}
     * Normal case: Returns the worst card of the hand.
     */
    public void testGetWorstCard() {
        Hand hand = new Hand();
        assertNull(hand.getWorstCard(new ClassicCardComparator(new ClassicCardEvaluator())));
    }

    /**
     * Method under test: {@link Hand#getWorstCard(CardComparator)}
     * Failure case: The card comparator is null.
     */
    public void testGetWorstCard2() {
        Hand hand = new Hand();
        try {
            hand.getWorstCard(null);
            fail("The card comparator cannot be null.");
        } catch (NullPointerException e) {
            assertEquals("The card comparator cannot be null.", e.getMessage());
        }
    }

    /**
     * Method under test: {@link Hand#containsPair()}
     * Normal case: Returns true if the hand contains a pair.
     */
    public void testContainsPair() {
        assertFalse((new Hand()).containsPair());
    }

    /**
     * Method under test: {@link Hand#containsPair()}
     */
    public void testContainsPair2() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
        assertFalse(hand.containsPair());
    }

    /**
     * Method under test: {@link Hand#containsPair()}
     */
    public void testContainsPair3() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
        hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
        assertTrue(hand.containsPair());
    }

    /**
     * Method under test: {@link Hand#containsPair()}
     */
    public void testContainsPair4() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
        hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
        hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
        assertFalse(hand.containsPair());
    }
}

