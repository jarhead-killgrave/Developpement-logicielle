package view.graphics;

import controller.Controller;
import model.participants.Player;
import view.Blackjack;
import vue.graphic.components.HiddenDeckComponent;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 * BlackjackGUI is the first screen of the game.
 * </b>
 *
 * @version 1.0
 */
public class BlackjackGUI extends JFrame implements Blackjack {

    /**
     * The panel that contains the players.
     */
    private final JPanel playersPanel;
    /**
     * The controller of the game.
     */
    private Controller controller;

    /**
     * Map that associates a player to his component.
     */


    /**
     * Creates a new start screen.
     *
     * @param controller The controller of the start screen.
     */
    public BlackjackGUI(Controller controller) {
        this.controller = controller;
        this.playersPanel = new JPanel();

        this.initComponents();
    }

    /**
     * Initializes the components of the start screen.
     */
    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Blackjack");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        // Minimun size of the window.
        // The window will take the half of the screen in width and the total height.
        this.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height));


        this.setLocationRelativeTo(this.getParent());
        this.pack();

    }

    @Override
    public void processGame() {

        this.showRound();

    }

    @Override
    public void displayMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String ask(String title, String message) {
        String answer = JOptionPane.showInputDialog(this, message, title, JOptionPane.QUESTION_MESSAGE);
        if(answer == null){
            this.getController().endGame();
        }
        return answer;
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Initialize the view.
     */
    @Override
    public void initialize() {

        // Set the layout of the frame.

        // Panel that contains the dealer components and the deck.
        JPanel dealerPanel = new JPanel();
        dealerPanel.setLayout(new GridLayout(1, 2));
        dealerPanel.add(new ParticipantComponent(this.getController().getDealer()));
        dealerPanel.add(new HiddenDeckComponent(this.getController().getDeck(), false, 5, 5));
        this.add(dealerPanel);

        // Add the Actions component.
        this.add(new ActionsComponent(this.controller));


        // Add the players panel.
        this.add(this.playersPanel);

        // Pack the frame.
        this.pack();

        // Set the location of the frame.
        this.setLocationRelativeTo(null);

        // Build information players frame.
        JFrame informationPlayersFrame = new InformationsPlayers(this.getController().getPlayers());

        // Show the information players frame.
        informationPlayersFrame.setVisible(true);

        this.initPlayersPanel();
        this.setVisible(true);


    }

    public void initPlayersPanel() {

        this.playersPanel.removeAll();
        this.playersPanel.setLayout(new GridLayout(1, this.getController().getPlayers().size()));
        for (Player player : this.getController().getStillPlaying()) {
            this.playersPanel.add(new ParticipantComponent(player));
        }
        this.pack();


    }


    /**
     * Show the round
     */
    @Override
    public void showRound() {

        // If the grid layout is not set, we set it.
        if (!(this.playersPanel.getLayout() instanceof GridLayout) || ((GridLayout) this.playersPanel.getLayout()).getColumns() != this.getController().getStillPlaying().size()) {
            this.initPlayersPanel();
        }
    }

}
