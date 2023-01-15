package model.cards;

import junit.framework.*;
import model.card.Card;
import model.card.Rank;
import model.card.Suit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ListOfCardsTest extends TestCase {


    /**
     * Test constructor under normal conditions.
     */
    public void testConstructor() {
        ListOfCardsMock cards = new ListOfCardsMock();
        assertEquals(0, cards.size());
    }

    /**
     * Test constructor with null cards.
     */
    public void testConstructorNullCards() {
        try {
            new ListOfCardsMock(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test addCard under normal conditions.
     */
    public void testAddCard() {
        ListOfCardsMock cards = new ListOfCardsMock();
        cards.addCard(new Card(Rank.ACE, Suit.CLUBS));
        assertEquals(1, cards.size());
        assertEquals(Rank.ACE, cards.getCard(0).getRank());
        assertEquals(Suit.CLUBS, cards.getCard(0).getSuit());
    }

    /**
     * Test addCard with null card.
     */
    public void testAddCardNullCard() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.addCard(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test addCards under normal conditions.
     */
    public void testAddCards() {
        ListOfCardsMock cards = new ListOfCardsMock();
        List<Card> cardsToAdd = new ArrayList<>();
        cardsToAdd.add(new Card(Rank.ACE, Suit.CLUBS));
        cardsToAdd.add(new Card(Rank.TWO, Suit.CLUBS));
        cardsToAdd.add(new Card(Rank.THREE, Suit.CLUBS));

        cardsToAdd.forEach(cards::addCard);

        assertEquals(3, cards.size());
        for (int i = 0; i < cardsToAdd.size(); i++) {
            assertEquals(cardsToAdd.get(i).getRank(), cards.getCard(i).getRank());
            assertEquals(cardsToAdd.get(i).getSuit(), cards.getCard(i).getSuit());
        }
    }

    /**
     * Test indexOf under normal conditions.
     */
    public void testIndexOf() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        cards.addCard(card);
        assertEquals(0, cards.indexOf(card));
    }

    /**
     * Test indexOf with null card.
     */
    public void testIndexOfNullCard() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.indexOf(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test indexOf with card not in list.
     */
    public void testIndexOfCardNotInList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        assertEquals(-1, cards.indexOf(new Card(Rank.ACE, Suit.CLUBS)));
    }

    /**
     * Test removeCard under normal conditions.
     */
    public void testRemoveCard() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        cards.addCard(card);
        cards.removeCard(card);
        assertEquals(0, cards.size());
    }

    /**
     * Test removeCard with null card.
     */
    public void testRemoveCardNullCard() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.removeCard(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test removeCard with card not in list.
     */
    public void testRemoveCardCardNotInList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        cards.removeCard(new Card(Rank.ACE, Suit.CLUBS));
        assertEquals(0, cards.size());
    }

    /**
     * Test clear under normal conditions.
     */
    public void testClear() {
        ListOfCardsMock cards = new ListOfCardsMock();
        cards.addCard(new Card(Rank.ACE, Suit.CLUBS));
        cards.addCard(new Card(Rank.TWO, Suit.CLUBS));
        cards.addCard(new Card(Rank.THREE, Suit.CLUBS));
        cards.clear();
        assertEquals(0, cards.size());
    }

    /**
     * Test getCard under normal conditions.
     */
    public void testGetCard() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        cards.addCard(card);
        assertEquals(card, cards.getCard(0));
    }

    /**
     * Test getCard with index out of bounds.
     */
    public void testGetCardIndexOutOfBounds() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.getCard(0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    /**
     * Test size under normal conditions.
     */
    public void testSize() {
        ListOfCardsMock cards = new ListOfCardsMock();
        assertEquals(0, cards.size());
        cards.addCard(new Card(Rank.ACE, Suit.CLUBS));
        assertEquals(1, cards.size());
        cards.addCard(new Card(Rank.TWO, Suit.CLUBS));
        assertEquals(2, cards.size());
        cards.addCard(new Card(Rank.THREE, Suit.CLUBS));
        assertEquals(3, cards.size());
    }

    /**
     * Test isEmpty under normal conditions.
     */
    public void testIsEmpty() {
        ListOfCardsMock cards = new ListOfCardsMock();
        assertTrue(cards.isEmpty());
        cards.addCard(new Card(Rank.ACE, Suit.CLUBS));
        assertFalse(cards.isEmpty());
    }

    /**
     * Test iterator under normal conditions.
     */
    public void testIterator() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        Card card3 = new Card(Rank.THREE, Suit.CLUBS);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);

        Iterator<Card> iterator = cards.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(card1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(card2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(card3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    /**
     * Test iterator with empty list.
     */
    public void testIteratorEmptyList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Iterator<Card> iterator = cards.iterator();
        assertFalse(iterator.hasNext());
    }

    /**
     * Test contains under normal conditions.
     */
    public void testContains() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        cards.addCard(card);
        assertTrue(cards.contains(card));
    }

    /**
     * Test contains with null card.
     */
    public void testContainsNullCard() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.contains(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test contains with card not in list.
     */
    public void testContainsCardNotInList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        assertFalse(cards.contains(new Card(Rank.ACE, Suit.CLUBS)));
    }

    /**
     * Test containsAll under normal conditions.
     */
    public void testContainsAll() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        Card card3 = new Card(Rank.THREE, Suit.CLUBS);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);

        ListOfCardsMock otherCards = new ListOfCardsMock();
        otherCards.addCard(card1);
        otherCards.addCard(card2);
        otherCards.addCard(card3);
        assertTrue(cards.containsAll(otherCards));
    }

    /**
     * Test containsAll with null list.
     */
    public void testContainsAllNullList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.containsAll((List<Card>) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test containsAll with null cards.
     */
    public void testContainsAllNullCards() {
        ListOfCardsMock cards = new ListOfCardsMock();
        ListOfCardsMock otherCards = null;
        try {
            cards.containsAll(otherCards);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test containsAll with cards not in list.
     */
    public void testContainsAllCardsNotInList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        Card card3 = new Card(Rank.THREE, Suit.CLUBS);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);

        ListOfCardsMock otherCards = new ListOfCardsMock();
        otherCards.addCard(card1);
        otherCards.addCard(card2);
        otherCards.addCard(card3);
        otherCards.addCard(new Card(Rank.FOUR, Suit.CLUBS));
        assertFalse(cards.containsAll(otherCards));
    }

    /**
     * Test add under normal conditions.
     */
    public void testAdd() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card = new Card(Rank.ACE, Suit.CLUBS);
        assertTrue(cards.addCard(card));
        assertEquals(1, cards.size());
        assertEquals(card, cards.getCard(0));
    }

    /**
     * Test add with null card.
     */
    public void testAddNullCard() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.addCard(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test getNotIn under normal conditions.
     */
    public void testGetNotIn() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        Card card3 = new Card(Rank.THREE, Suit.CLUBS);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);

        ListOfCardsMock otherCards = new ListOfCardsMock();
        otherCards.addCard(card1);
        otherCards.addCard(card2);
        List<Card> notIn = cards.getNotIn(otherCards);
        assertEquals(1, notIn.size());
        assertEquals(card3, notIn.get(0));
    }

    /**
     * Test getNotIn with null list.
     */
    public void testGetNotInNullList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.getNotIn((List<Card>) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test getNotIn with null cards.
     */
    public void testGetNotInNullCards() {
        ListOfCardsMock cards = new ListOfCardsMock();
        ListOfCardsMock otherCards = null;
        try {
            cards.getNotIn(otherCards);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test getNotIn with cards not in list.
     */
    public void testGetNotInCardsNotInList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        Card card3 = new Card(Rank.THREE, Suit.CLUBS);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);

        ListOfCardsMock otherCards = new ListOfCardsMock();
        otherCards.addCard(card1);
        otherCards.addCard(card2);
        otherCards.addCard(card3);
        otherCards.addCard(new Card(Rank.FOUR, Suit.CLUBS));
        List<Card> notIn = cards.getNotIn(otherCards);
        assertEquals(0, notIn.size());
    }

    /**
     * Test getNotIn with cards not in list.
     */
    public void testGetNotInCardsNotInList2() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        Card card3 = new Card(Rank.THREE, Suit.CLUBS);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);

        ListOfCardsMock otherCards = new ListOfCardsMock();
        otherCards.addCard(card1);
        otherCards.addCard(card2);
        otherCards.addCard(card3);
        otherCards.addCard(new Card(Rank.FOUR, Suit.CLUBS));
        List<Card> notIn = otherCards.getNotIn(cards);
        assertEquals(1, notIn.size());
        assertEquals(new Card(Rank.FOUR, Suit.CLUBS), notIn.get(0));
    }

    /**
     * Test getBetween two indices under normal conditions.
     */
    public void testGetBetween() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        Card card3 = new Card(Rank.THREE, Suit.CLUBS);
        Card card4 = new Card(Rank.FOUR, Suit.CLUBS);
        Card card5 = new Card(Rank.FIVE, Suit.CLUBS);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);
        cards.addCard(card4);
        cards.addCard(card5);

        List<Card> between = cards.getBetween(1, 4);
        assertEquals(3, between.size());
        assertEquals(card2, between.get(0));
        assertEquals(card3, between.get(1));
        assertEquals(card4, between.get(2));
    }

    /**
     * Test getBetween two indices with null list.
     */
    public void testGetBetweenNullList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.getBetween(0, 0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    /**
     * Test getBetween two indices with null list.
     */
    public void testGetBetweenNullList2() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.getBetween(1, 0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    /**
     * Test getBetween two indices with null list.
     */
    public void testGetBetweenNullList3() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.getBetween(0, 1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    /**
     * Test getBySuit under normal conditions.
     */
    public void testGetBySuit() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        Card card3 = new Card(Rank.THREE, Suit.CLUBS);
        Card card4 = new Card(Rank.FOUR, Suit.CLUBS);
        Card card5 = new Card(Rank.FIVE, Suit.CLUBS);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);
        cards.addCard(card4);
        cards.addCard(card5);

        List<Card> bySuit = cards.getBySuit(Suit.CLUBS);
        assertEquals(5, bySuit.size());
        assertEquals(card1, bySuit.get(0));
        assertEquals(card2, bySuit.get(1));
        assertEquals(card3, bySuit.get(2));
        assertEquals(card4, bySuit.get(3));
        assertEquals(card5, bySuit.get(4));
    }

    /**
     * Test getBySuit with null list.
     */
    public void testGetBySuitNullList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        List<Card> bySuit = cards.getBySuit(Suit.CLUBS);
        assertEquals(0, bySuit.size());
    }

    /**
     * Test getBySuit with null list.
     */
    public void testGetBySuitNullList2() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.getBySuit(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * Test getByRank under normal conditions.
     */
    public void testGetByRank() {
        ListOfCardsMock cards = new ListOfCardsMock();
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        Card card3 = new Card(Rank.THREE, Suit.CLUBS);
        Card card4 = new Card(Rank.FOUR, Suit.CLUBS);
        Card card5 = new Card(Rank.FIVE, Suit.CLUBS);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);
        cards.addCard(card4);
        cards.addCard(card5);

        List<Card> byRank = cards.getByRank(Rank.THREE);
        assertEquals(1, byRank.size());
        assertEquals(card3, byRank.get(0));
    }

    /**
     * Test getByRank with null list.
     */
    public void testGetByRankNullList() {
        ListOfCardsMock cards = new ListOfCardsMock();
        List<Card> byRank = cards.getByRank(Rank.THREE);
        assertEquals(0, byRank.size());
    }

    /**
     * Test getByRank with null list.
     */
    public void testGetByRankNullList2() {
        ListOfCardsMock cards = new ListOfCardsMock();
        try {
            cards.getByRank(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }
}
