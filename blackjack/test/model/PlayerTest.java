package model;

import junit.framework.*;
import model.cards.FactoryDeck;
import model.cards.Hand;
import model.evaluation.BlackjackCardEvaluator;
import model.participants.Player;

public class PlayerTest extends TestCase{

    /**
     * Test of Constructor with the given name and money.
     */
    public void testConstructorGivenNameAndMoney() {
        Player player = new Player("Player", new BlackjackCardEvaluator(), 100);
        assertEquals("Player", player.getName());
        assertEquals(Double.compare(100, player.getMoney()), 0);
    }

    /**
     * Test of Constructor with the given name, money, bet, hand, canPlay and isHisTurn.
     */
    public void testConstructorGivenNameMoneyBetHandCanPlayAndIsHisTurn() {
        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        assertEquals("Player", player.getName());
        assertEquals(Double.compare(100, player.getMoney()), 0);
        assertEquals(Double.compare(10, player.getBet()), 0);
        assertTrue(player.canPlay());
        assertTrue(player.isHisTurn());
    }

    /**
     * Test method getMoney()
     */
    public void testGetMoney() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100);
        assertEquals(Double.compare(100, player.getMoney()), 0);
    }

    /**
     * Test method setMoney()
     */
    public void testSetMoney() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100);
        player.setMoney(200);
        assertEquals(Double.compare(200, player.getMoney()), 0);
    }


    /**
     * Test method increaseMoney()
     */
    public void testIncreaseMoney() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100);
        player.increaseMoney(100);
        assertEquals(Double.compare(200, player.getMoney()), 0);
    }

    /**
     * Test method decreaseMoney()
     */
    public void testDecreaseMoney() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100);
        player.decreaseMoney(50);
        assertEquals(Double.compare(50, player.getMoney()), 0);
    }

    /**
     * Test method getBet()
     */
    public void testGetBet() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        assertEquals(Double.compare(10, player.getBet()), 0);
    }

    /**
     * Test method setBet()
     */
    public void testSetBet() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        player.setBet(20);
        assertEquals(Double.compare(20, player.getBet()), 0);
    }

    /**
     * Test method increaseBet()
     */
    public void testIncreaseBet() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        player.increaseBet(10);
        assertEquals(Double.compare(20, player.getBet()), 0);
    }

    /**
     * Test method decreaseBet()
     */
    public void testDecreaseBet() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        player.decreaseBet(5);
        assertEquals(Double.compare(5, player.getBet()), 0);
    }

    /**
     * Test method doubleBet()
     */
    public void testDoubleBet() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        player.doubleBet();
        assertEquals(Double.compare(20, player.getBet()), 0);

    }

    /**
     * Test method isPlayer()
     */
    public void testIsPlayer() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        assertTrue(player.isPlayer());
    }

    // /**
    //  * Test method hit()
    //  */
    public void testHit() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        player.hit(FactoryDeck.createDeck32());
        assertEquals(1, player.getHand().getCards().size());
    }
    
    /**
     * Test method doubleHit()
     */
    public void testDoubleHit() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        player.doubleHit(FactoryDeck.createDeck32());
        assertEquals(1, player.getHand().getCards().size());
    }

    /**
     * Test method stand()
     */
    public void testStand() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        player.stand();
        assertEquals(false, player.canPlay());
    }

    /**
     * Test method placeBet()
     */
    public void testPlaceBet() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100);
        player.placeBet(10);
        assertEquals(Double.compare(10, player.getBet()), 0);
    }

    /**
     * Test method reset()
     */
    public void testReset() {

        Player player = new Player("Player", new BlackjackCardEvaluator(), 100, 10, new Hand(), true, true);
        player.reset();

        assertEquals(0, player.getHand().getCards().size());
        assertTrue(player.canPlay());
        assertFalse(player.isHisTurn());
    }

}
