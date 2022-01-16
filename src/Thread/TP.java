package Thread;

import Signal.JoueurEcritureLecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TP extends Thread {
    public static void main(String[] args) throws InterruptedException {
        //utilisateur saisi taille de plateau par Scanner
        int taille = 7;
        //utilisateur saisi joueur par Scanner
        int joueurJ = 5;
        int toursR=0;
        int drapeaux=0,drapeauTotal = 0;

        Joueur[] joueurs = new Joueur[joueurJ];
        //utilisateur saisi facteur (if...) Par Scanner
        Facteur facteur = Facteur.UNDEFINED;
        switch(facteur){
            case UNDEFINED:
                int tempT= Facteur.UNDEFINED.getTemp(joueurJ);
                toursR=0;
                drapeaux=joueurJ;
                drapeauTotal=drapeaux*joueurJ;
                break;
            case SIMPLE:
                toursR=joueurJ*Facteur.SIMPLE.getTemp(joueurJ);
                drapeaux=toursR;
                drapeauTotal=drapeaux*joueurJ;
                break;
            case DOUBLE:
                toursR=joueurJ*Facteur.DOUBLE.getTemp(joueurJ);
                drapeaux=toursR;
                drapeauTotal=drapeaux*joueurJ;
                break;
            case TRIPLE:
                toursR=joueurJ*Facteur.TRIPLE.getTemp(joueurJ);
                drapeaux=toursR;
                drapeauTotal=drapeaux*joueurJ;
                break;
        }

        //Si Espace insuffisance pour les drapeau, Systeme arrete
        if(taille*taille<drapeauTotal){
            System.out.println("Insuffisance espace");
            System.exit(1);
        }

        //-----Plateau-----//
        List<List<Integer>> plateau = getPlateau(taille,drapeaux,drapeauTotal,joueurJ);
        System.out.println("\n");
        System.out.println("\t\t-------START-------");
        affichePlateau(plateau);
        System.out.println("\n");



        //--------------CREATION DU JOUEUR-----------------//
        JoueurEcritureLecture EJ = new JoueurEcritureLecture(plateau);
        boolean finJeu=false;
        int tempsJoueur=10;
        for (int i = 0; i < joueurJ; i++) {
            joueurs[i] = new Joueur(EJ, i + 1, tempsJoueur, toursR, EJ.plateau, finJeu);
        }

        for (int i = 0; i < joueurJ; i++) {
            joueurs[i].start();
        }
        Thread.sleep(1000);

        for (int i = 0; i < joueurJ; i++) {
            joueurs[i].Stop();
        }

        //Fin du jeu
        System.out.println("\n");
        System.out.println("\t\t-------FINISH--------");
        affichePlateau(plateau);

        //La complexite est grand car j'utilise trop de boucle, mais j'ai pas assez de temps de tester et reflechir a un atre.
        Map<Integer,Integer> pointCalcul = calculPoint(plateau,joueurJ);
        for(int i=1;i<joueurJ+1;i++) {
            System.out.println("Joueur: " +i+" point: "+ pointCalcul.get(i));
        }
    }


    public static List<List<Integer>> getPlateau(int taille,int drapeaux,int drapeauxTotal,int joueurJ){
        //---------------CREATION DU PLATEAU__________________//
        List<List<Integer>> plateau = new ArrayList<>(taille);
        for (int i = 0; i < taille; i++) {
            plateau.add(new ArrayList());
            for (int j = 0; j < taille; j++) {
                plateau.get(i).add(j, 0);
            }
        }

        //Met drapeaux aleatoirement sur plateau
        int drapeauxCount=drapeaux;
        for(int i=0;i<joueurJ;i++) {
            while (drapeauxCount > 0) {
                double x = Math.random() * (taille) ;
                double y = Math.random() * (taille) ;
                if(plateau.get((int) x).get((int)y)==0||plateau.get((int) x).get((int)y)<0) {
                    plateau.get((int) x).remove((int)y);
                    plateau.get((int) x).add((int) y, i + 1);
                    drapeauxCount--;
                }
            }
            drapeauxCount=drapeaux;
        }

        return plateau;
    }

    public static void affichePlateau(List<List<Integer>> plateau){
        for (List list : plateau) {
            System.out.print("\n\n");
            for (int i = 0; i < plateau.size(); i++) {
                System.out.print("\t" + list.get(i) + "\t");
            }
        }
    }

    public static Map<Integer,Integer> calculPoint(List<List<Integer>> plateau,int nbJoueur){
        Map<Integer,Integer> joueurPoint = new HashMap<>();
        int point=0;
        for(int k=1;k<nbJoueur+1;k++) {
            for (int i = 0; i < plateau.size(); i++) {
                for (int j = 0; j < plateau.size(); j++) {
                    if (plateau.get(i).get(j) < 0 &&( plateau.get(i).get(j) - (plateau.get(i).get(j) * 2))==k ) {
                        point++;
                    }
                }
            }
            joueurPoint.put(k,point);
            point=0;
        }
        return joueurPoint;
    }
}
