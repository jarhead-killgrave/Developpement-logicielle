package model.participants.bot;

import model.cards.Deck;

/**
 * <b>
 * Strategy that use a probability to decide if the bot will hit or not.
 * </b>
 *
 * <p>
 * The blackjack is a probabilistic game. So we can use a probability to decide if the bot will hit or not.
 * According to the value of the bot's hand, the probability will be different.
 * We split the probability that bot stand in 8 parts according to the value of the bot's hand:
 * </p>
 *
 * @version 1.0
 */

public class BasicProbalityStrategy implements BotStrategy {

    /**
     * The probability that the bot will stand.
     */
    private final double probability;

    /**
     * Creates a new basic probability strategy.
     *
     * @param probability The probability that the bot will stand.
     */
    public BasicProbalityStrategy(double probability) {
        this.probability = probability;
    }

    @Override
    public boolean execute(Bot bot, Deck deck) {
        boolean canPlayAgain = true;
        if (bot.getHandValue() >= 18) {
            canPlayAgain = bot.stand();
        } else {
            if (bot.getHandValue() <= 11) {
                canPlayAgain = bot.hit(deck);
            } else {
                if (Math.random() < this.probability) {
                    canPlayAgain = bot.stand();
                } else {
                    canPlayAgain = bot.hit(deck);
                }
            }
        }
        return canPlayAgain;
    }
}


