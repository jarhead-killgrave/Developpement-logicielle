package model.card;

import junit.framework.*;

public class CardTest extends TestCase {

    /**
     * Test constructor under normal conditions.
     */
    public void testConstructor() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        assertEquals(Rank.ACE, card.getRank());
        assertEquals(Suit.CLUBS, card.getSuit());
    }

    /**
     * Test constructor with null rank.
     */
    public void testConstructorNullRank() {
        try {
            new Card(null, Suit.CLUBS);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test constructor with null suit.
     */
    public void testConstructorNullSuit() {
        try {
            new Card(Rank.ACE, null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test constructor with null rank and suit.
     */
    public void testConstructorNullRankAndSuit() {
        try {
            new Card(null, null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test constructor with null card.
     */
    public void testConstructorNullCard() {
        try {
            new Card(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test constructor with a card.
     */
    public void testConstructorCard() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(card);
        assertEquals(Rank.ACE, card2.getRank());
        assertEquals(Suit.CLUBS, card2.getSuit());
    }

    /**
     * Test color of the card.
     */
    public void testColor() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        assertEquals("BLACK", card.getColor());
        card = new Card(Rank.ACE, Suit.DIAMONDS);
        assertEquals("RED", card.getColor());
        card = new Card(Rank.ACE, Suit.HEARTS);
        assertEquals("RED", card.getColor());
        card = new Card(Rank.ACE, Suit.SPADES);
        assertEquals("BLACK", card.getColor());
    }

    /**
     * Test the string representation of a card.
     */
    public void testToString() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        assertEquals("( A , ♣ )", card.toString());
    }

    /**
     * Test equality of two cards.
     */
    public void testEquals() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.ACE, Suit.CLUBS);
        assertEquals(card, card2);
    }

    /**
     * Test equality of two cards with different ranks.
     */
    public void testEqualsDifferentRank() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        assertFalse(card.equals(card2));
    }

    /**
     * Test equality of two cards with different suits.
     */
    public void testEqualsDifferentSuit() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.ACE, Suit.DIAMONDS);
        assertFalse(card.equals(card2));
    }

    /**
     * Test equality of two cards with different ranks and suits.
     */
    public void testEqualsDifferentRankAndSuit() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.DIAMONDS);
        assertFalse(card.equals(card2));
    }

    /**
     * Test equality of two cards with null.
     */
    public void testEqualsNull() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        assertFalse(card.equals(null));
    }

    /**
     * Test equality of two cards with a different object.
     */
    public void testEqualsDifferentObject() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        assertFalse(card.equals(new Object()));
    }

    /**
     * Test the hash code of a card.
     */
    public void testHashCode() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.ACE, Suit.CLUBS);
        assertEquals(card.hashCode(), card2.hashCode());
    }

    /**
     * Test the hash code of a card with different ranks.
     */
    public void testHashCodeDifferentRank() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        assertFalse(card.hashCode() == card2.hashCode());
    }

    /**
     * Test the hash code of a card with different suits.
     */
    public void testHashCodeDifferentSuit() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.ACE, Suit.DIAMONDS);
        assertFalse(card.hashCode() == card2.hashCode());
    }

    /**
     * Test the hash code of a card with different ranks and suits.
     */
    public void testHashCodeDifferentRankAndSuit() {
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.DIAMONDS);
        assertFalse(card.hashCode() == card2.hashCode());
    }

}

