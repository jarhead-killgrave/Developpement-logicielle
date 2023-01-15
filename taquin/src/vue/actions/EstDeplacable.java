// package vue.actions;

// import java.awt.*;
// import java.awt.event.*;
// import java.util.Arrays;

// import javax.swing.*;
// import javax.swing.border.BevelBorder;
// import javax.swing.border.Border;

// import controleur.ControleurAbstraitTaquin;
// import modele.TaquinModel;
// import vue.composants.PlateauJouable;

// public class EstDeplacable implements MouseListener {


//     private PlateauJouable plateau;

//     public EstDeplacable(PlateauJouable plateau) {
//         this.plateau = plateau;
//     }
//     @Override
//     public void mouseClicked(MouseEvent e) {
//         // TODO Auto-generated method stub
        
//     }

//     @Override
//     public void mouseEntered(MouseEvent e) {
//         Component label = this.plateau.getComponentAt(e.getX(), e.getY());
//         int index = Arrays.asList(this.plateau.getComponents()).indexOf(label);
//         TaquinModel model = plateau.getControleur().getModel();
//         if (model.indexesCasesAutour(model.getPositionVide()).contains(index)) {
//             System.out.println(plateau.getAssociationObjetEtImage().get(label));

//         }      
//     }

//     @Override
//     public void mouseExited(MouseEvent e) {
//         // TODO Auto-generated method stub
//         JLabel label = (JLabel) this.plateau.getComponentAt(e.getX(), e.getY());
//         Border bordure = BorderFactory.createRaisedBevelBorder();
//         label.setBorder(bordure);
        
//     }

//     @Override
//     public void mousePressed(MouseEvent e) {
        

//     }

//     @Override
//     public void mouseReleased(MouseEvent e) {
            
//     }
    
// }
