package prca_cc2_jan2022;

import java.util.List;

public class Joueur extends Thread {
    private JoueurEcritureLecture EJ;
    int tempsJoueur;
    int identifierJoueur;
    int nbToursR;
    boolean finJeu;
    List<List<Integer>> plateau;
    Signal signal = new Signal();
    public Joueur(JoueurEcritureLecture base, int identifierJoueur, int tpsJoueur, int nbToursR, List<List<Integer>> plateau, boolean finJeu) {
        this.EJ = base;
        this.tempsJoueur = tpsJoueur;
        this.plateau=plateau;
        this.identifierJoueur=identifierJoueur;
        this.nbToursR=nbToursR;
        this.finJeu=finJeu;
    }
    public void Stop() {
        this.finJeu = true;
    }
    public void run() {
        for(int i=0;i<20;i++) {
            double x = Math.random() * (plateau.size() - 1);
            double y = Math.random() * (plateau.size() - 1);
            EJ.entreJoueur();
            try {
                sleep(this.tempsJoueur);
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
            //Si l'espace est !=0 et != lui => prends le drapeau
            if (EJ.plateau.get((int) x).get((int) y) > 0 && EJ.plateau.get((int) x).get((int) y) != identifierJoueur) {
                EJ.sortJoueur();
                EJ.entreEcrire();
                if (EJ.plateau.get((int) x).get((int) y) > 0 && EJ.plateau.get((int) x).get((int) y) != identifierJoueur) {
                    EJ.plateau.get((int) x).remove((int) y);
                    EJ.plateau.get((int) x).add((int) y, identifierJoueur - (identifierJoueur * 2));
                }
                EJ.sortEcrire();
            }
            EJ.sortJoueur();
        }
    }
}

