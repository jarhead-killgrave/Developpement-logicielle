package model;

import junit.framework.*;
import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import model.evaluation.AceValue;
import model.evaluation.BlackjackCardEvaluator;

public class BlackjackCardEvaluatorTest extends TestCase{

    /**
     * Test constructor with the default value of the ace.
     */
    public void testConstructorDefaultAceValue() {
        BlackjackCardEvaluator evaluator = new BlackjackCardEvaluator();
        assertEquals(AceValue.ELEVEN, evaluator.getAceValue());
    }

    /**
     * Test constructor with a given value of the ace.
     */
    public void testConstructorGivenAceValue() {
        BlackjackCardEvaluator evaluator = new BlackjackCardEvaluator(AceValue.ONE);
        assertEquals(AceValue.ONE, evaluator.getAceValue());
    }

    /**
     * Test evaluate with a card with a number rank.
     */
    public void testEvaluateNumberRank() {
        BlackjackCardEvaluator evaluator = new BlackjackCardEvaluator();
        
        Card card1 = new Card(Rank.TWO, Suit.CLUBS);

        Card card2 = new Card(Rank.THREE, Suit.DIAMONDS);

        Card card3 = new Card(Rank.FOUR, Suit.HEARTS);

        Card card4 = new Card(Rank.FIVE, Suit.SPADES);

        Card card5 = new Card(Rank.JACK, Suit.CLUBS);

        Card card6 = new Card(Rank.QUEEN, Suit.DIAMONDS);

        Card card7 = new Card(Rank.KING, Suit.HEARTS);

        Card card8 = new Card(Rank.ACE, Suit.SPADES);



        assertEquals(2, evaluator.evaluate(card1));

        assertEquals(3, evaluator.evaluate(card2));

        assertEquals(4, evaluator.evaluate(card3));

        assertEquals(5, evaluator.evaluate(card4));

        assertEquals(10, evaluator.evaluate(card5));

        assertEquals(10, evaluator.evaluate(card6));

        assertEquals(10, evaluator.evaluate(card7));

        assertEquals(11, evaluator.evaluate(card8));
    }
    
}
