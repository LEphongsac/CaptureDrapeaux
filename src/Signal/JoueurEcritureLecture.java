package Signal;

import java.util.List;

public class JoueurEcritureLecture {
        private int nbJoueurs = 0;
        private boolean joueursEcrire = false;
        public List<List<Integer>> plateau;

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
