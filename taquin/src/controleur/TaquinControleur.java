package controleur;

import java.util.List;

import modele.*;

public class TaquinControleur extends ControleurAbstraitTaquin {

    public TaquinControleur(TaquinModel modeleTaquin) {
        super(modeleTaquin);
    }


    public int getModelPositionVide() {
        return modeleTaquin.getPositionVide();
    }

    public void setModelPositionVide(int positionVide) {
        modeleTaquin.setPositionVide(positionVide);
    }

    public int getModelLargeur() {
        return modeleTaquin.getLargeur();
    }

    public int getModelLongueur() {
        return modeleTaquin.getLongueur();
    }

    public int getModelTaille() {
        return modeleTaquin.getTaille();
    }

    public List<Case> getModelCases() {
        return modeleTaquin.getCases();
    }

    @Override
    public void control() {

        if (num != -1) {
            Case c = new Case(num);
            boolean ok = modeleTaquin.deplace(c);
            score = ok ? score + 1 : score;
            
        }
        if (nbrPermutations != -1) {
            modeleTaquin.melanger(nbrPermutations);
            score = 0;
        }
        num = -1;
        nbrPermutations = -1;
    }

}
