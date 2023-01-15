package application;

import modele.*;
import vue.Taquin;
import java.util.*;
import controleur.*;

public class Main {

    public static void main(String[] args) {

        int longueur;

        int largeur;

        int melange;

        boolean solver;

        if (args.length < 2) {

            longueur = 3;

            largeur = 3;

        } else {

            longueur = Integer.parseInt(args[0]);

            largeur = Integer.parseInt(args[1]);

        }

        TaquinModel etat = new TaquinModel(longueur, largeur);

        int numcase;

        int taille = longueur * largeur;

        Case c = new Case();

        Scanner entree = new Scanner(System.in);

        System.out.println(etat);
        TaquinControleur control = new TaquinControleur(etat);
        
        Taquin taquin = new Taquin(control, "/home/jarhead/Documents/genie-logiciel-kitsoukou-doumbouya-orouguidou-rousseau/Images/index.jpg");
        etat.ajoutEcouteur(taquin);
        // while (!etat.estTermine()) {

        //     System.out.println("Veuillez entrer le numero de case à déplacer(-1 pour quitter ou recommencer) : ");

        //     try {

        //         if (entree.hasNextInt()) {

        //             numcase = entree.nextInt();

        //             if (numcase == -1) {

        //                 break;

        //             }

        //             if (numcase >= 0 && numcase < taille) {

        //                 // c.setValeur(numcase);

        //                 // etat.deplace(c);
        //                 control.setNumero(numcase);

        //                 System.out.println();

        //                 System.out.println(etat);

        //             }

        //         }

        //         else {

        //             entree.next();

        //             continue;

        //         }

        //     } catch (RuntimeException ex) {

        //         System.err.println("The number is not an integer ");

        //     }

        // }
        // System.out.println("Le score est de :" + control.getScore());

    }

}
