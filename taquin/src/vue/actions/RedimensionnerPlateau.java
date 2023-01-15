package vue.actions;

import java.awt.event.*;

import vue.composants.PlateauJouable;

public class RedimensionnerPlateau implements ComponentListener{

    private PlateauJouable plateau;
    
    public RedimensionnerPlateau(PlateauJouable plateau) {
        this.plateau = plateau;
    }
    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void componentResized(ComponentEvent e) {
        plateau.initialiseAssociationObjetEtImage();
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
