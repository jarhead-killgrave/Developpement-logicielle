package controleur;

import lombok.Getter;
import lombok.Setter;
import modele.*;

public abstract class ControleurAbstraitTaquin {

    /** Le modele dont le controleur est en charge */
    protected TaquinModel modeleTaquin;

    /** Le numero contenu dans la case */
    protected int num = -1;

    /** Le nombre de permutation a faire */
    protected int nbrPermutations = -1;

    /** Le score de la partie */
    @Getter
    protected int score;

    protected boolean termine;

    protected ControleurAbstraitTaquin(TaquinModel modeleTaquin) {
        this.modeleTaquin = modeleTaquin;
        this.score = 0;
        this.termine = false;
    }

    public void setNumero(int num) {
        this.num = num;
        control();
    }

    public void setNumero(Case currentCase) {
        setNumero(currentCase.getValeur());
    }

    public void setNbrPermutations(int nbrPermutations) {
        this.nbrPermutations = nbrPermutations;
        control();
    }

    public abstract void control();

    public boolean isTermine(){
        return termine;
    }
    
    public TaquinModel getModel() {
        return modeleTaquin;
    }

}
