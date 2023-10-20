package projet.main;

import projet.controleur.Controleur;
import projet.controleur.partieDeJeu.PartieJeuNim;
import projet.controleur.partieDeJeu.PartiePuissance4;
import projet.controleur.partieDeJeu.IJeu;
import projet.vue.Ihm;

public class Main {

    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        Controleur controleurJeu = new Controleur(ihm);

        IJeu jeu = null;
        while (jeu == null) {
            Ihm.afficher("Sélectionnez le jeu auquel vous souhaitez jouer :" +
                    "\n(1) Jeu de Nim" +
                    "\n(2) Jeu de Puissance 4");
            int choix = Ihm.saisieNb();

            switch (choix) {
                case 1:
                    jeu = new PartieJeuNim();
                    break;
                case 2:
                    jeu = new PartiePuissance4();
                    break;
                default:
                    Ihm.afficher("Veuillez saisir un nombre indiqué.");
            }
        }
        boolean solo=false;
        int mode=0;
        while (mode != 1 && mode != 2) {
            Ihm.afficher("Souhaitez vous jouer à un joueur ( joueur vs IA ) ou à deux joueurs ( joueur vs joueur ) ?" +
                    "\n(1) joueur vs IA " +
                    "\n(2) joueur vs joueur ");
            mode = Ihm.saisieNb();

            switch (mode) {
                case 1:
                    solo = true;
                    break;
                case 2:
                    solo = false;
                    break;
                default:
                    Ihm.afficher("Veuillez saisir un nombre indiqué.");
            }
        }

        controleurJeu.jouer(jeu,solo);
    }
}
