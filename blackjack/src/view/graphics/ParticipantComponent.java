package view.graphics;

import model.participants.Participant;
import observer.Listenable;
import observer.Listener;
import vue.graphic.components.HandComponent;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 * ParticipantComponent is a component that represents a participant in a gui.
 * </b>
 *
 * <p>
 * A participant component is a JPanel. In this component, we can display the name of the participant,
 * the score of the participant, the hand of the participant
 * </p>
 *
 * @version 1.0
 */
public class ParticipantComponent extends JPanel implements Listener {

    /**
     * The participant to display.
     */
    private final Participant participant;


    /**
     * The label that displays the value of the hand.
     */
    private final JLabel handValueLabel;

    /**
     * HandComponent that displays the hand.
     */
    private final HandComponent handComponent;


    /**
     * Creates a new player component.
     *
     * @param participant The participant to display.
     */
    public ParticipantComponent(Participant participant) {
        this.participant = participant;
        this.handValueLabel = new JLabel("Value: " + this.participant.getHandValue());
        this.handComponent = new HandComponent(this.participant.getHand());
        this.initComponents();
    }


    /**
     * Initializes the components of the player component.
     */
    private void initComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Divide the panel in 2 columns
        //First column : name and score
        //Second column : hand
        // Second column will occupy 3/4 of the panel
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel(this.participant.getName()), c);
        c.gridy = 1;
        this.add(this.handValueLabel, c);
        c.weightx = 3;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;
        this.add(this.handComponent, c);

        this.updateBorder();

        this.participant.addListener("handValue", this);
        this.participant.addListener("canPlay", this);
        this.participant.addListener("isHisTurn", this);
    }

    @Override
    public void notify(Listenable listenable, String operation, Object... args) {
        if (operation.equals("handValue")) {
            this.handValueLabel.setText("Value: " + this.participant.getHandValue());
        } else if (operation.equals("isHisTurn") || operation.equals("canPlay")) {
            this.updateBorder();
        }
    }

    /**
     * Update the border of the component.
     */
    public void updateBorder() {
        if (this.participant.isHisTurn()) {
            this.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5, true));
        } else if (this.participant.canPlay()) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
        } else {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
        }
    }
}
