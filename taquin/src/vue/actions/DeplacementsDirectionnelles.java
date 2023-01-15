package vue.actions;

import java.awt.event.*;

import controleur.ControleurAbstraitTaquin;

public class DeplacementsDirectionnelles implements KeyListener{

    private ControleurAbstraitTaquin controleur;

    public DeplacementsDirectionnelles(ControleurAbstraitTaquin controleur) {
        this.controleur = controleur;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int positionVide = controleur.getModel().getPositionVide();
        int position = positionVide;

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                position -= 1;
                break;

            case KeyEvent.VK_RIGHT:
                position += 1;
                break;

            case KeyEvent.VK_UP:
                position -= controleur.getModel().getLargeur();
                break;

            case KeyEvent.VK_DOWN:
                position += controleur.getModel().getLargeur();
                break;

            default:
                break;
        }
        
        position = controleur.getModel().indexesCasesAutour(positionVide).contains(position) ? position : positionVide;
        
        controleur.setNumero(controleur.getModel().getCases().get(position));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
