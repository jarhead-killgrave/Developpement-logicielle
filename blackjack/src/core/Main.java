package core;

import controller.Controller;
import model.evaluation.BlackjackCardEvaluator;
import view.Blackjack;
import view.console.TerminalBlackjack;
import view.graphics.BlackjackGUI;

public class Main {

    /**
     * The main method.
     *
     * @param args decides if the game is played in the console or in the GUI.
     */
    public static void main(String[] args) {

        int decision = 1;
        if (args.length > 0) {
            try {
                decision = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument. Please enter 0 for console or 1 for GUI.");
                System.exit(0);
            }
        }

        Blackjack view;
        if (decision == 0) {
            view = new TerminalBlackjack(null);
        } else {
            view = new BlackjackGUI(null);
        }

        Controller controller = new Controller(new BlackjackCardEvaluator(), view);
        view.setController(controller);
        controller.launchGame();
    }

}

