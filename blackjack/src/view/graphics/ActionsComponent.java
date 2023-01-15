package view.graphics;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 * ActionsComponent is a component that represents all the actions of a player.
 * </b>
 */
public class ActionsComponent extends JPanel {

    /**
     * The controller of the game.
     */
    private final Controller controller;

    /**
     * Creates a new actions component.
     *
     * @param controller The controller of the game.
     */
    public ActionsComponent(Controller controller) {
        this.controller = controller;
        this.initComponents();
    }

    /**
     * Initializes the components of the actions component.
     */
    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JButton hitButton = new JButton("Hit");
        hitButton.addActionListener(e -> this.controller.hit());

        this.add(hitButton);
        JButton standButton = new JButton("Stand");
        standButton.addActionListener(e -> this.controller.stand());
        this.add(standButton);
        JButton doubleButton = new JButton("Double");
        doubleButton.setMinimumSize(new Dimension(50, 50));
        doubleButton.addActionListener(e -> this.controller.doubleDown());
        this.add(doubleButton);
    }


}
