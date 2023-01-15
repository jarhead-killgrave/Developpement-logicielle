package vue.actions;

import java.awt.event.*;
import java.io.File;

import javax.swing.JFileChooser;

import vue.Taquin;

public class OuvrirUnfichier implements KeyListener, ActionListener{


    private final JFileChooser fc = new JFileChooser();
    private String filtre;
    private Taquin taquin;
    public OuvrirUnfichier(String filtre, Taquin taquin) {
        this.filtre = filtre;
        this.taquin = taquin;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        int res = fc.showOpenDialog(fc.getParent());

        if (res == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            this.taquin.setCheminImage(file.getAbsolutePath());
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
