package controller;

import model.Constants;
import model.Shoe;
import model.cards.Deck;
import model.evaluation.BlackjackCardEvaluator;
import model.participants.Dealer;
import model.participants.Player;
import model.participants.Players;
import model.participants.bot.BasicProbalityStrategy;
import model.participants.bot.Bot;
import model.participants.bot.BotStrategy;
import model.participants.bot.ClassicBotStrategy;
import view.Blackjack;

import java.util.Iterator;

/**
 * <b>
 * A blackjack controller is the controller of a blackjack game.
 * </b>
 *
 * @version 1.0
 */
public class Controller {

    /**
     * The listenablePlayers of the game.
     */
    private final Players listenablePlayers;
    /**
     * The dealer of the game.
     */
    private final Dealer dealer;
    /**
     * The deck of the game.
     */
    private final Deck deck;
    /**
     * The evaluator of the cards.
     */
    private final BlackjackCardEvaluator evaluator;
    /**
     * The still playing listenablePlayers.
     */
    private Players stillPlaying;
    /**
     * Iterator under the still playing listenablePlayers.
     */
    private Iterator<Player> iterator;
    /**
     * The current player.
     */
    private Player currentPlayer;
    /**
     * Boolean that indicates if the game is over.
     */
    private boolean gameOver;
    /**
     * The blackjack view.
     */
    private Blackjack view;


    /**
     * Build a blackjack controller.
     *
     * @param listenablePlayers The listenablePlayers of the game.
     * @param evaluator         The evaluator of the game.
     * @param view              The view of the game.
     */
    public Controller(Players listenablePlayers, BlackjackCardEvaluator evaluator, Blackjack view) {
        this.listenablePlayers = listenablePlayers;
        this.currentPlayer = null;
        this.dealer = new Dealer("Dealer", evaluator);
        this.deck = new Shoe(Constants.NUMBER_OF_DECKS);
        this.gameOver = false;
        this.stillPlaying = new Players();
        this.iterator = this.stillPlaying.iterator();
        this.evaluator = evaluator;
        this.view = view;
    }

    /**
     * Build a blackjack controller.
     *
     * @param evaluator The evaluator of the game.
     * @param view      The view of the game.
     */
    public Controller(BlackjackCardEvaluator evaluator, Blackjack view) {
        this(new Players(), evaluator, view);
    }

    /**
     * Returns the blackjack view.
     *
     * @return The blackjack view.
     */
    public Blackjack getView() {
        return this.view;
    }

    /**
     * Sets the blackjack view.
     *
     * @param view The blackjack view.
     */
    public void setView(Blackjack view) {
        this.view = view;
    }

    /**
     * Returns the listenablePlayers of the game.
     *
     * @return The listenablePlayers of the game.
     */
    public Players getPlayers() {
        return this.listenablePlayers;
    }

    /**
     * Add a player to the game.
     *
     * @param player The player to add.
     * @return True if the player has been added, false otherwise.
     */
    public boolean addPlayer(Player player) {
        return this.listenablePlayers.add(player);
    }

    /**
     * Returns the dealer of the game.
     *
     * @return The dealer of the game.
     */
    public Dealer getDealer() {
        return this.dealer;
    }

    /**
     * Returns the deck of the game.
     *
     * @return The deck of the game.
     */
    public Deck getDeck() {
        return this.deck;
    }


    /**
     * Returns the still playing listenablePlayers.
     *
     * @return The still playing listenablePlayers.
     */
    public Players getStillPlaying() {
        return this.stillPlaying;
    }

    /**
     * Returns the iterator under the still playing listenablePlayers.
     *
     * @return The iterator under the still playing listenablePlayers.
     */
    public Iterator<Player> getIterator() {
        return this.iterator;
    }

    /**
     * Returns the boolean that indicates if the game is over.
     *
     * @return The boolean that indicates if the game is over.
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * Sets the boolean that indicates if the game is over.
     *
     * @param gameOver The boolean that indicates if the game is over.
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Returns the current player.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Sets the current player.
     *
     * @param currentPlayer The current player.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Returns the next player.
     *
     * @return The next player.
     */
    public Player nextPlayer() {
        if (this.iterator.hasNext()) {
            this.setCurrentPlayer(this.iterator.next());
            if (this.currentPlayer.canPlay()) {
                this.getCurrentPlayer().setHisTurn(true);

                return this.getCurrentPlayer();
            } else {
                return this.nextPlayer();
            }
        } else {
            return null;
        }
    }

    /**
     * The current player hits.
     */
    public void hit() {
        if (this.getCurrentPlayer() != null) {
            try {
                // If we can not play after the hit we go to the next player(player or dealer).
                if (!this.getCurrentPlayer().hit(this.getDeck())) {
                    this.setCurrentPlayer(this.nextPlayer());
                    this.checkAfterPlayer();
                }
            } catch (IndexOutOfBoundsException e) {
                this.getView().displayEmptyDeck();
            }
        }

    }

    /**
     * The current player stands.
     */
    public void stand() {

        // If it is not the turn of the dealer
        if (this.getCurrentPlayer() != null) {
            this.setCurrentPlayer(this.nextPlayer());
            this.checkAfterPlayer();
        }
    }

    /**
     * The current player doubles.
     */
    public void doubleDown() {

        // If it is not the turn of the dealer
        if (this.currentPlayer != null) {

            try {
                if (this.getCurrentPlayer().getMoney() < this.getCurrentPlayer().getBet()) {
                    this.getView().displayNotEnoughMoney();
                } else {

                    this.currentPlayer.doubleHit(this.deck);

                    // We change the player to next
                    this.setCurrentPlayer(this.nextPlayer());

                    // If the player is not null
                    this.checkAfterPlayer();
                }

            } catch (IndexOutOfBoundsException e) {
                this.view.displayEmptyDeck();
            }


        }

    }

    /**
     * The dealer plays.
     */
    public void dealerPlay() {
        this.getDealer().takeCardsUntil(this.getDeck(), Constants.DEALER_LIMIT);
        // If the dealer is busted
        this.setGameOver(true);
        this.getDealer().setHisTurn(false);
        this.endRound();

    }

    /**
     * Initializes the players.
     */
    private void initPlayers() {

        int nbPlayers = this.getView().askNumberOfPlayers();

        if (nbPlayers < Constants.MINIMUM_NUMBER_OF_PLAYERS || nbPlayers > Constants.MAXIMUM_NUMBER_OF_PLAYERS) {
            this.getView().displayWrongNumberOfPlayers();
            this.initPlayers();
        } else {
            for (int i = 0; i < nbPlayers; i++) {
                String name = this.getView().askPlayerName(i);
                int money = this.getView().askPlayerMoney(i);
                if (money < Constants.MINIMUM_BET || money > Constants.MAXIMUM_MONEY) {
                    this.getView().displayWrongMoney();
                    money = this.getView().askPlayerMoney(i);
                }
                this.addPlayer(new Player(name, this.evaluator, money));
            }
        }

    }

    /**
     * Initializes the bots
     */
    private void initBots() {
        int nbBots = this.getView().askNumberOfBots();

        if (nbBots > Constants.MAXIMUM_NUMBER_OF_PLAYERS - this.getPlayers().size() || nbBots < 0) {
            this.getView().displayWrongNumberOfBots();
            this.initBots();
        } else {
            for (int i = 0; i < nbBots; i++) {
                BotStrategy strategy = null;
                if (Math.random() < 0.5) {
                    strategy = new ClassicBotStrategy(this.getDealer());
                } else {
                    strategy = new BasicProbalityStrategy(0.3f);
                }
                this.addPlayer(new Bot("Bot" + i, this.evaluator, 1000, strategy));
            }
        }


    }


    /**
     * Asks each player to bet
     */
    private void askBets() {

        this.getStillPlaying().removeIf(player -> {
            if (player instanceof Bot) {
                // A random bet between minBet and min(maxBet, player.getMoney())
                double bet = (Math.random() * (Constants.MAXIMUM_BET - Constants.MINIMUM_BET + 1) + Constants.MINIMUM_BET);
                if (bet > player.getMoney()) {
                    bet = player.getMoney();
                }
                player.placeBet(bet);
            } else {
                int nbTentatives = 0;
                int bet = this.getView().askBet(player);
                while ((bet < Constants.MINIMUM_BET || bet > Constants.MAXIMUM_BET || (bet > player.getMoney())) && nbTentatives < 3) {
                    this.getView().displayInvalidBet(player, 3 - nbTentatives);
                    bet = this.getView().askBet(player);
                    nbTentatives++;
                }
                if (nbTentatives == 3) {
                    this.getView().displayPlayerLeft(player);
                    return true;
                }
                player.placeBet(bet);
            }
            return false;
        });
    }

    /**
     * Starts the game.
     */
    public void startGame() {

        this.initPlayers();

        this.initBots();

        // We can now fix the still playing Players
        this.stillPlaying = new Players(this.listenablePlayers);

        this.getView().initialize();

        // We shuffle the deck
        this.getDeck().shuffle();


    }

    /**
     * Starts the round.
     */
    public void startRound() {

        int nbPlayers = this.getStillPlaying().size();

        if ((nbPlayers * Constants.INITIAL_CARDS_PER_PLAYER + Constants.INITIAL_CARDS_FOR_DEALER) > this.getDeck().size()) {
            this.getView().displayNoEnoughCards();
            this.endGame();
        }

        this.getStillPlaying().reset();
        // We reset the dealer
        this.getDealer().reset();

        // We ask the bets
        this.askBets();

        // Add listener

        // The dealer take one card
        this.getDealer().takeCard(this.getDeck());

        // The listenablePlayers who still play take two cards
        for (Player player : this.getStillPlaying()) {
            player.takeCard(this.getDeck());
            player.takeCard(this.getDeck());
        }

        // We check each player
        this.checkPlayers();

        this.iterator = this.getStillPlaying().iterator();

        // We fix the current player
        this.currentPlayer = this.nextPlayer();

        this.checkAfterPlayer();

        this.getView().showRound();

    }

    public void endGame() {
        this.getView().quitGame();
        System.exit(0);
    }

    /**
     * Pre verification before we let the players play.
     */
    private void checkPlayers() {
        for (Player player : this.getStillPlaying()) {
            if (player.hasBlackjack()) {
                this.payBlackjack(player);
                player.setHisTurn(false);
                player.setCanPlay(false);
            } else if (player.isBusted()) {
                player.setHisTurn(false);
                player.setCanPlay(false);
            }
        }
    }

    /**
     * We check if the next player is a bot
     */
    private void checkBot() {
        if (this.getCurrentPlayer() instanceof Bot) {

            // We automatically play for the bot
            boolean canPlay = ((Bot)this.getCurrentPlayer()).play(this.getDeck());
            if (!canPlay) {
                this.setCurrentPlayer(this.nextPlayer());
            }
            this.checkAfterPlayer();
        }
    }

    /**
     * Checking after the current player has played.
     */
    private void checkAfterPlayer() {
        if (this.getCurrentPlayer() != null) {
            this.checkBot();
        } else {
            this.getDealer().setHisTurn(true);
            this.dealerPlay();
        }
    }


    /**
     * Ends the round.
     */
    public void endRound() {

        this.getStillPlaying().forEach(this::collectBet);

        this.getView().showResult();

        // We remove the listenablePlayers who have no more money
        this.getStillPlaying().removeIf(player -> player.getMoney() == 0 || player.getMoney() < Constants.MINIMUM_BET);

        // We ask each player(except the bots) if he wants to play again
        this.getStillPlaying().removeIf(player -> {
            if (player instanceof Bot) {
                return false;
            }
            return !this.getView().askPlayAgain(player);
        });

        // If we have at least one player and at least one player are not a bot, we start a new round
        if (this.getStillPlaying().size() > 0 && this.getStillPlaying().stream().anyMatch(player -> !(player instanceof Bot))) {
            this.startRound();
        } else {
            this.endGame();
        }
    }


    /**
     * Launches the game.
     */
    public void launchGame() {
        // We start the game
        this.startGame();

        // Show rules
        this.getView().showRules();

        // We start the round
        this.startRound();
    }


    /**
     * Checks if a player wins.
     *
     * @param player The player to check.
     * @return 1 if the player wins, 0 if it is a draw, -1 if the player loses.
     */
    public int checkWin(Player player) {
        if (player.isBusted()) {
            return -1;
        } else if (this.getDealer().isBusted()) {
            return 1;
        }
        return Integer.compare(player.getHandValue(), this.getDealer().getHandValue());
    }

    /**
     * Pay or collect the bet of a player according to the ratio
     *
     * @param player The player to pay or collect
     */
    public void payBlackjack(Player player) {
        player.increaseMoney((1 + Constants.BLACKJACK_RATIO) * player.getBet());
        player.setBet(0);
    }

    /**
     * Collect the bet of a player
     *
     * @param player The player to collect
     */
    public void collectBet(Player player) {
        int win = this.checkWin(player);
        if (win == 1) {
            player.increaseMoney((1 + Constants.WIN_RATIO) * player.getBet());
        } else if (win == 0) {
            player.increaseMoney(player.getBet());
        }
        player.setBet(0);
    }

}
