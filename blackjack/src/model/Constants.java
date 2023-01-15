package model;

import model.evaluation.AceValue;

/**
 * <b>
 * Constants contains all the constants model of the game.
 * </b>
 *
 * @version 1.0
 */
public final class Constants {

    /**
     * The busted value.
     */
    public static final int BLACKJACK_VALUE = 21;

    /**
     * The ACE value.
     */
    public static final AceValue ACE_VALUE = AceValue.ELEVEN;

    /**
     * The dealer limit
     */
    public static final int DEALER_LIMIT = 17;

    /**
     * The number of decks.
     */
    public static final int NUMBER_OF_DECKS = 6;

    /**
     * The maximum number of players.
     */
    public static final int MAXIMUM_NUMBER_OF_PLAYERS = 5;

    /**
     * The minimum number of players.
     */
    public static final int MINIMUM_NUMBER_OF_PLAYERS = 1;


    /**
     * The minimum bet.
     */
    public static final int MINIMUM_BET = 2;

    /**
     * The maximum bet.
     */
    public static final int MAXIMUM_BET = 100;

    /**
     * The number of cards to deal to a player at the beginning of the game.
     */
    public static final int INITIAL_CARDS_PER_PLAYER = 2;

    /**
     * The number of cards to deal to the dealer at the beginning of the game.
     */
    public static final int INITIAL_CARDS_FOR_DEALER = 1;

    /**
     * The ratio of the bet when the player wins.
     */
    public static final double WIN_RATIO = 1.0;

    /**
     * The ratio of the bet when the player wins with a blackjack.
     */
    public static final double BLACKJACK_RATIO = 1.5;

    /**
     * The maximun money a player can have.
     */
    public static final int MAXIMUM_MONEY = 10000;
}
