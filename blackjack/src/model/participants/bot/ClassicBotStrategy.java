package model.participants.bot;

import model.cards.Deck;
import model.participants.Dealer;

public class ClassicBotStrategy implements BotStrategy {

    /**
     * The dealer of the game.
     */
    private final Dealer dealer;


    /**
     * The bot that will use the strategy.
     *
     * @param dealer The dealer of the game.
     */
    public ClassicBotStrategy(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public boolean execute(Bot bot, Deck deck) {

        boolean canPlayAgain = true;
        // the bot will stand if he has 18 or more
        if (bot.getHandValue() >= 18) {
            System.out.println("Bot " + bot.getName() + " stands");
            canPlayAgain = bot.stand();
        } else {
            // The bot double if he has 10 or 11 and the dealer has 9 or less
            if (bot.getHandValue() == 10 || bot.getHandValue() == 11) {

                if (this.dealer.getHandValue() <= 9) {
                    System.out.println("Bot " + bot.getName() + " doubles");
                    canPlayAgain = bot.doubleHit(deck);
                }

            }
            // The bot hits if he has 17 or less
            if (bot.getHandValue() <= 17) {
                System.out.println("Bot " + bot.getName() + " hits");
                canPlayAgain = bot.hit(deck);
            }
        }
        return canPlayAgain;
    }
}

