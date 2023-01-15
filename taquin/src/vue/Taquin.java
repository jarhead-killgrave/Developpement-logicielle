package vue;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.imageio.ImageIO;
import javax.swing.*;

import observeur.EcouteurTaquin;
import vue.actions.CliqueSurImage;
import vue.actions.DeplacementsDirectionnelles;
import vue.actions.OuvrirUnfichier;
import controleur.*;
import modele.Case;
import vue.composants.*;
import vue.composants.Menu;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import lombok.Getter;
public class Taquin extends JFrame implements EcouteurTaquin{

    /**Le controleur sur lequel on a la vue*/
    @Getter
    TaquinControleur control;
    @Getter
    private PlateauJouable plateau;
    /**Le chemin vers l'image */
    private String cheminImage;

    private ZoneInformation zoneInformation;
    private Menu menu;



    public Taquin(TaquinControleur control, String cheminImage){
        super("Taquin puzzle");

        this.control = control;
        this.cheminImage = cheminImage;
        this.plateau = new PlateauJouable(control, this.cheminImage);
        this.zoneInformation = new ZoneInformation(control);
        zoneInformation.build();
        plateau.build();
        add(plateau);
        add(zoneInformation, BorderLayout.NORTH);
        this.menu = new Menu(this);
        initialize();
        
    }

    private void initialize() {
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        menu.creerMenuBar(this);
        Border bordure = BorderFactory.createLoweredSoftBevelBorder();

        this.getRootPane().setBorder(bordure);

    }
    
    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
        this.plateau.setCheminImage(cheminImage);

    }

    private void initialiseEvenement() {
        
    }
        


    @Override
    public void miseAjour(Object source) {
        // TODO Auto-generated method stub
        // afficheTaquin();
        System.out.println(control.getModel());
        repaint();

        if (control.isTermine()) {
            JOptionPane.showMessageDialog(this, "La partie est terminé. Veuillez melanger le plateau");
        }
        
    }



    
}
