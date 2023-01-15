package model.participants.bot;

import model.cards.Deck;
import model.evaluation.BlackjackCardEvaluator;
import model.participants.Player;

/**
 * <b>
 * Bot is a participant of the game that can play automatically.
 * </b>
 *
 * @version 1.0
 */
public class Bot extends Player {

    /**
     * The strategy of the bot.
     */
    private final BotStrategy strategy;

    /**
     * Creates a new bot with the given name and money.
     *
     * @param name      The name of the bot.
     * @param evaluator The evaluator of the bot.
     * @param money     The money of the bot.
     * @param strategy
     */
    public Bot(String name, BlackjackCardEvaluator evaluator, int money, BotStrategy strategy) {
        super(name, evaluator, money);
        this.strategy = strategy;
    }

    /**
     * Returns the strategy of the bot.
     *
     * @return The strategy of the bot.
     */
    public BotStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * The bot plays automatically.
     *
     * @param deck The deck of the game.
     * @return boolean true if the bot can play again, false otherwise.
     */
    public boolean play(Deck deck) {
        return this.strategy.execute(this, deck);
    }


}