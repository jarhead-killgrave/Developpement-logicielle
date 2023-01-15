package model.cards;

import junit.framework.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import vue.graphic.components.HandComponent;

public class DeckTest extends TestCase {
    /**
     * Method under test: {@link Deck#Deck(List)}
     */
    @Test
    public void testConstructor() {
        assertTrue((new Deck(new ArrayList<>())).getCards().isEmpty());
    }

    /**
     * Method under test: {@link Deck#Deck(List)}
     * Failure case: The cards is null.
     */
    public void testConstructor2() {
        try {
            new Deck(null);
            fail("The cards cannot be null.");
        } catch (NullPointerException e) {
            assertEquals("The cards cannot be null.", e.getMessage());
        }
    }

    /**
     * Method under test: {@link Deck#deal()}
     */
    public void testDeal() {
        Deck createDeck32Result = FactoryDeck.createDeck32();
        createDeck32Result.deal();
        assertEquals(27, createDeck32Result.getCards().size());
    }

    /**
     * Method under test: {@link Deck#deal()}
     * Failure case: The deck is empty(i.e. there is no card to deal so an exception is thrown IllegalStateException).
     */
    public void testDeal2() {
        try {
            (new Deck(new ArrayList<>())).deal();
            fail("The deck is empty.");
        } catch (IllegalStateException e) {
            assertEquals("The deck is empty.", e.getMessage());
        }
    }

    /**
     * Method under test: {@link Deck#deal(int)}
     */
    public void testDeal3() {
        Deck createDeck32Result = FactoryDeck.createDeck32();
        assertEquals(10, createDeck32Result.deal(10).size());
        assertEquals(18, createDeck32Result.getCards().size());
    }

    /**
     * Method under test: {@link Deck#deal(int)}
     * Failure case: The deck is empty(i.e. there is no card to deal so an exception is thrown IllegalStateException).
     */
    public void testDeal4() {
        try {
            (new Deck(new ArrayList<>())).deal(10);
            fail("The deck is empty.");
        } catch (IllegalStateException e) {
            assertEquals("The deck is empty.", e.getMessage());
        }
    }

    /**
     * Method under test: {@link Deck#deal(int)}
     */
    public void testDeal5() {
        Deck createDeck32Result = FactoryDeck.createDeck32();
        createDeck32Result.addListener("removeCard", new HandComponent(new Hand()));
        assertEquals(10, createDeck32Result.deal(10).size());
        assertEquals(18, createDeck32Result.getCards().size());
    }

    /**
     * Method under test: {@link Deck#dealAll()}
     */
    public void testDealAll() {
        assertEquals(28, FactoryDeck.createDeck32().dealAll().size());
    }

}

