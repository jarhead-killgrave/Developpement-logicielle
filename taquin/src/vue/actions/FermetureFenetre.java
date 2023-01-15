package vue.actions;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.*;

public class FermetureFenetre implements ActionListener{

    private JFrame composant;

    public FermetureFenetre(JFrame composant) {
        this.composant = composant;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        composant.dispose();
        
    }
    
}
