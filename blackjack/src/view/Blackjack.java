package view;

import controller.Controller;
import model.Constants;
import model.participants.Player;

public interface Blackjack {


    /**
     * Gets the controller of the game.
     *
     * @return the controller of the game.
     */
    Controller getController();

    /**
     * Sets the controller.
     *
     * @param controller the controller.
     */
    void setController(Controller controller);

    /**
     * Initialize the view.
     */
    void initialize();

    /**
     * Ask the number of players.
     *
     * @return The number of players.
     */
    default int askNumberOfPlayers() {
        String title = "Number of players";
        String message = "Please enter the number of players( " + Constants.MINIMUM_NUMBER_OF_PLAYERS + " - " + Constants.MAXIMUM_NUMBER_OF_PLAYERS + " )";
        try {
            return Integer.parseInt(ask(title, message));
        } catch (NumberFormatException e) {
            displayMessage("Warning", "The number of players must be an integer");
            return askNumberOfPlayers();
        }
    }

    /**
     * Ask the number of bots.
     *
     * @return The number of bots.
     */
    default int askNumberOfBots() {
        String title = "Number of bots";
        String message = "Enter the number of bots ( 0 - " + (Constants.MAXIMUM_NUMBER_OF_PLAYERS - this.getController().getPlayers().size()) + " )";
        try {
            return Integer.parseInt(ask(title, message));
        } catch (NumberFormatException e) {
            displayMessage("Warning", "The number of bots must be an integer");
            return askNumberOfBots();
        }
    }


    /**
     * Ask a player to enter his bet.
     *
     * @param player The player.
     * @return The bet.
     */
    default int askBet(Player player) {
        String title = "Bet";
        String message = player.getName() + ", enter your bet(" + Constants.MINIMUM_BET + " - " + (player.getMoney() < Constants.MAXIMUM_BET ? player.getMoney() : Constants.MAXIMUM_BET) + ")";
        try {
            return Integer.parseInt(ask(title, message));
        } catch (NumberFormatException e) {
            displayMessage("Warning", "The bet must be an integer.");
            return askBet(player);
        }
    }


    /**
     * Ask the player to play again.
     *
     * @param player The player.
     * @return True if the player wants to play again.
     */
    default boolean askPlayAgain(Player player) {
        String title = "Play again";
        String message = player.getName() + ", do you want to play again?(y/n)";
        String answer = ask(title, message);

        // We check if the answer is valid.
        if (answer.matches("[yYnN]")) {
            return answer.matches("[yY]");
        } else {
            return askPlayAgain(player);
        }
    }

    /**
     * Show that we have no players.
     */
    default void showNoPlayers() {
        String title = "Informations for the players";
        String message = "There is no players.";
        displayMessage(title, message);
    }

    /**
     * Show the round
     */
    void showRound();

    /**
     * Display the result of the round.
     */
    default void showResult() {
        String title = "End round";
        String message = "The round is over.";
        displayMessage(title, message);

        title = "Result of the round";
        message = "The dealer has " + getController().getDealer().getHandValue() + " points.\n\n";
        for (Player player : getController().getStillPlaying()) {
            message += "Player " + player.getName() + " has " + player.getHandValue() + " points.";
            int result = getController().checkWin(player);
            if (result == 1) {
                message += "Player " + player.getName() + " wins.";
            } else if (result == 0) {
                message += "Player " + player.getName() + " loses.";
            } else {
                message += "Player " + player.getName() + " draws.";
            }
            message += "\n\n";
        }
        displayMessage(title, message);
    }

    /**
     * Display a message that the bet of the player will bet "bet".
     *
     * @param player The player.
     * @param bet    The bet.
     */
    default void displayBet(Player player, int bet) {
        String title = "Information for " + player.getName();
        String message = "You bet " + bet + " euros.";
        displayMessage(title, message);
    }

    /**
     * Display that the player could not bet the amount and that it will be removed from the game.
     *
     * @param player     The player.
     * @param tentatives The number of tentatives
     */
    default void displayInvalidBet(Player player, int tentatives) {
        String title = "Information for " + player.getName();
        String message = "You still have " + tentatives + " tentatives to bet before being removed from the game.";
        displayMessage(title, message);
    }

    /**
     * Display that we have no more players and that the game will end.
     */
    default void displayNoPlayerLeft() {
        String message = "No more players left. The game will end.";
        displayMessage("Information", message);
    }

    /**
     * Show the rules of the game.
     */
    default void showRules() {
        String title = "Rules of the game";
        String rules = "The goal of the game is to get a hand value as close to 21 as possible without going over 21.\n" +
                "The value of a hand is the sum of the values of the individual cards.\n" +
                "The value of a card is the number on the card for cards 2 through 10.\n" +
                "Face cards (Jack, Queen, King) are worth 10.\n" +
                "The value of an Ace is " + Constants.ACE_VALUE + "\n" +
                "The dealer will hit until his hand has value " + Constants.DEALER_LIMIT + " or more.\n" +
                "If the dealer goes over 21, all players still in the game win.\n" +
                "If the dealer does not go over 21, the highest hand without going over 21 wins.\n" +
                "If the dealer and a player have the same value, it is a tie and no one wins.\n" +
                "If in the beginning of the game, a player has a blackjack, he wins a ratio of " + Constants.BLACKJACK_RATIO + " of his bet.\n";
        displayMessage(title, rules);
    }

    /**
     * Quit the game.
     */
    default void quitGame() {
        String title = "Quit";
        String message = "The game is over. Thank you for playing.";
        displayMessage(title, message);

    }

    default String askPlayerName(int i) {
        String title = "Ask player name";
        String message = "Enter the name of the player " + i + " :";
        return ask(title, message);
    }

    default int askPlayerMoney(int i) {
        String title = "Ask player money";
        String message = "Enter the money of the player " + i + "(must be an integer between " + Constants.MINIMUM_BET + " and " + 10000 + ") :";
        try {
            return Integer.parseInt(ask(title, message));
        } catch (NumberFormatException e) {
            return askPlayerMoney(i);
        }
    }

    void processGame();

    default void displayEmptyDeck() {
        String title = "Information";
        String message = "The deck is empty. The game will end.";
        displayMessage(title, message);
    }

    /**
     * Display a message
     *
     * @param title   The title of the message.
     * @param message The message.
     */
    void displayMessage(String title, String message);

    /**
     * Ask to do something.
     *
     * @param title   The title of the message.
     * @param message The message.
     * @return The answer.
     */
    String ask(String title, String message);

    default void displayWrongNumberOfPlayers() {
        String title = "Error";
        String message = "The number of players must be between " + Constants.MINIMUM_NUMBER_OF_PLAYERS + " and " + Constants.MAXIMUM_NUMBER_OF_PLAYERS + ".";
        displayMessage(title, message);
    }

    default void displayWrongMoney() {
        String title = "Error";
        String message = "The money must be an integer between " + Constants.MINIMUM_BET + " and " + 10000 + ".";
        displayMessage(title, message);
    }

    default void displayWrongNumberOfBots() {
        String title = "Error";
        String message = "The number of bots must be between " + 0 + " and " + (Constants.MAXIMUM_NUMBER_OF_PLAYERS - this.getController().getPlayers().size()) + ".";
        displayMessage(title, message);
    }

    default void displayPlayerLeft(Player player) {
        String title = "Information";
        String message = "Player " + player.getName() + " left the game.";
        displayMessage(title, message);
    }

    default void displayNoEnoughCards() {
        String title = "Error";
        String message = "There is not enough cards in the deck to play. The game will end.";
        displayMessage(title, message);
    }

    default void displayNotEnoughMoney() {
        String title = "Error";
        String message = "You don't have enough money to bet.";
        displayMessage(title, message);
    }
}
