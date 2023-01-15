package model.participants.bot;

import model.cards.Deck;
import model.participants.Dealer;

import java.util.Random;

/**
 * <b>
 *     Strategy
 * </b>
 */
public class RandomBotStrategy implements BotStrategy{

    /**
     * The random
     */
    private Random random;

    /**
     * Build a new instance of this Strategy
     *
     * @param random the random generator
     * @throws NullPointerException if the random generator is null
     */
    public RandomBotStrategy(Random random){
        if(random == null){
            throw new NullPointerException("The random generator cannot be null");
        }
        this.random = random;
    }

    @Override
    public boolean execute(Bot bot, Deck deck) {
        boolean canPlay = false;

        // The probability to hit, double or draw are the same
        int choice = random.nextInt(3);

        switch (choice){

            case 0:
                canPlay = bot.hit(deck);
                break;

            case 1:
                if (bot.getMoney() >= bot.getBet()){
                    canPlay = bot.doubleHit(deck);
                }else{
                    // We don't have enough money so we just hit
                    canPlay = bot.hit(deck);
                }
                break;

            case 2:
                canPlay = bot.stand();
                break;
        }

        return  canPlay;

    }
}
