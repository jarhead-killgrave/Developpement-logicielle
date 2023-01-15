package model;

import junit.framework.*;
import model.cards.Hand;
import model.cards.Deck;
import model.cards.FactoryDeck;
import model.evaluation.BlackjackCardEvaluator;
import model.participants.Dealer;

public class DealerTest extends TestCase{

    
    /**
     * Test method isPlayer()
     */
    public void testIsPlayer() {
        Dealer dealer = new Dealer("Dealer", new BlackjackCardEvaluator());
        assertFalse(dealer.isPlayer());
    }


    /**
     * Test method isPlayer() with basic constructor
     */
    public void testIsPlayerBasicConstructor() {
        Dealer dealer = new Dealer("name", new Hand() , new BlackjackCardEvaluator(), true, true);
        assertFalse(dealer.isPlayer());
    }

    /**
     * Test method takeCardsUntil()
     */
    public void testTakeCardsUntil() {

        Dealer dealer = new Dealer("Dealer", new BlackjackCardEvaluator());
        Deck deck = FactoryDeck.createDeck32();
        dealer.takeCardsUntil(deck, 17);
        assertTrue(dealer.getHandValue() >= 17);

        dealer = new Dealer("Dealer", new BlackjackCardEvaluator());

        dealer.takeCardsUntil(deck, 0);
        assertTrue(dealer.getHandValue() >= 0);

        dealer = new Dealer("Dealer", new BlackjackCardEvaluator());

        dealer.takeCardsUntil(deck, 21);
        assertTrue(dealer.getHandValue() >= 21);

        dealer = new Dealer("Dealer", new BlackjackCardEvaluator());

        dealer.takeCardsUntil(deck, 10);
        assertTrue(dealer.getHandValue() >= 10);

    }
}
