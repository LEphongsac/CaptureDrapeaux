package prca_cc2_jan2022;

import java.util.List;

public class JoueurEcritureLecture {
        private int nbJoueurs = 0;
        private boolean joueursEcrire = false;
        List<List<Integer>> plateau;

        public JoueurEcritureLecture(List<List<Integer>> plateau){
        this.plateau=plateau;
    }

        public synchronized void entreJoueur() {
            while (this.joueursEcrire){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            nbJoueurs++;
        }


        public synchronized void sortJoueur() {
            nbJoueurs--;
            notify();
        }


        public synchronized void entreEcrire() {
            while ((this.joueursEcrire)|| (this.nbJoueurs > 0)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.joueursEcrire = true;
        }


        public synchronized void sortEcrire() {
            this.joueursEcrire = false;
            notify();
        }

}
