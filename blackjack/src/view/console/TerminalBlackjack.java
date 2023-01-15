package view.console;

import controller.Controller;
import view.Blackjack;

import java.util.Scanner;

/**
 * <b>
 * A blackjack is a console view of a blackjack game.
 * </b>
 */
public class TerminalBlackjack implements Blackjack {


    /**
     * The controller of the game.
     */
    private Controller controller;

    /**
     * Creates a new Blackjack
     *
     * @param controller The controller of the blackjack.
     */
    public TerminalBlackjack(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    /**
     * Sets the controller.
     *
     * @param controller the controller.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() {

    }


    /**
     * Show the round
     */
    @Override
    public void showRound() {
        // boolean that indicates if it is the dealer's turn.
        if (this.getController().getCurrentPlayer() != null) {
            System.out.println("It's " + this.getController().getCurrentPlayer().getName() + "'s turn.");
            this.askPlayerToPlay();
            this.showRound();
        }

    }

    /**
     * Clear the screen.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Ask the player to play.
     */
    private void askPlayerToPlay() {
        this.update();
        System.out.println("Player " + this.getController().getCurrentPlayer().getName() + " turn.");
        // Ask the possible events.
        System.out.println("What do you want to do ?");

        System.out.println("0 : Hit");
        System.out.println("1 : Stand");
        System.out.println("2 : Double");

        // Ask the player to choose an event.
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice > 2) {
            // Check if the input is an integer.
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
        }

        switch (choice) {
            case 0:
                this.getController().hit();
                break;

            case 1:
                this.getController().stand();
                break;

            case 2:
                this.getController().doubleDown();
                break;
        }

    }


    /**
     * Updates the blackjack.
     */
    private void update() {
        // Delete the terminal.
        System.out.print("\033[H\033[2J");

        // Display the players the hand, the bet and the money
        this.getController().getStillPlaying().forEach(player -> {
            System.out.println(player.getName() + " : \n" + player.getHand().toString() + "\nBet : " + player.getBet() + "\nMoney : " + player.getMoney() + "\nhand value : " + player.getHandValue());
        });

        // Display the dealer hand, the bet and the money
        System.out.println("Dealer : \n" + this.getController().getDealer().getHand().toString() + "\n");

    }

    /**
     * Ask to continue the game.
     */
    private void askToContinue() {
        // Press enter to continue.
        System.out.println("Press enter to continue.");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }


    @Override
    public void processGame() {


        this.showRound();
        // Ask to continue.
        this.askToContinue();


    }


    @Override
    public void displayMessage(String title, String message) {
        System.out.println("--- " + title + " ---");
        System.out.println(message);
        System.out.println("--- --- ---");
        this.askToContinue();
    }

    @Override
    public String ask(String title, String message) {
        System.out.println("--- " + title + " ---");
        System.out.println(message);
        System.out.println("--- --- ---");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
