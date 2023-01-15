package vue.composants;

import java.awt.LayoutManager;
import java.awt.event.*;
import java.nio.Buffer;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import controleur.TaquinControleur;


public class ZoneInformation extends JPanel {
    
    private JLabel zoneNbCoups;
    private JLabel zoneCoups;
    private int nbCoups;
    private TaquinControleur controleur;

    public ZoneInformation(TaquinControleur controleur) {
        this.zoneNbCoups = new JLabel();
        this.zoneCoups = new JLabel();
        this.nbCoups = 0;
        this.controleur = controleur;
    }

    private void initialiserComposant() {
        this.zoneCoups.setHorizontalAlignment(SwingConstants.CENTER);
        this.zoneCoups.setText("Nombre de coup");
        this.zoneNbCoups.setHorizontalAlignment(SwingConstants.CENTER);
        Border bordure = BorderFactory.createLoweredSoftBevelBorder();
        this.zoneNbCoups.setBorder(bordure);
        this.zoneNbCoups.setText(""+nbCoups);
    }
    
    public void build() {
        initialiserComposant();
        Border bordure = BorderFactory.createRaisedBevelBorder();

        this.setBorder(bordure);
        this.setLayout(new GridLayout(2, 1));
        this.add(zoneCoups);
        this.add(zoneNbCoups);

    }
    

    public void paint(Graphics g) {
        super.paint(g);
        this.zoneNbCoups.setText("" + controleur.getScore());
    }
}
