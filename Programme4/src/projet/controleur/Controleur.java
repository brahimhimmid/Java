package projet.controleur;

import projet.controleur.partieDeJeu.IJeu;
import projet.modele.IA;
import projet.modele.Joueur;
import projet.modele.CoupInvalideException;
import projet.vue.Ihm;


public class Controleur {
    private final Ihm ihm;

    public Controleur(Ihm ihm) {
        this.ihm = ihm;
    }

    /*méthode pour jouer*/
    public void jouer(IJeu cJeu, boolean solo) {
        cJeu.initialiserJeu();
        Joueur[] joueurs = initialiserJoueurs(solo);
        partie(cJeu, joueurs[0], joueurs[1], joueurs[2], joueurs[3]);
        finJeu(joueurs[0], joueurs[1]);
    }


    /**
     * Permet de créer les deux joueurs et de garder en mémoire le joueur courant
     * @return un array contenant les données des joueurs
     */
    public static Joueur[] initialiserJoueurs(boolean solo) {
        Joueur[] res = new Joueur[4];

        // Les joueurs saisissent leur nom
        Ihm.afficher("Veuillez saisir le nom du joueur 1 :");
        Joueur j1 = new Joueur(Ihm.saisie());
        res[0] = j1;

        Joueur j2;
        if (solo){
            j2 = new IA("IA");
        }
        else {
            Ihm.afficher("Veuillez saisir le nom du joueur 2 :");
            j2 = new Joueur(Ihm.saisie());
        }
        res[1] = j2;

        // Un joueur est joueur courant lorsque c'est son tour de jouer un coup
        Joueur courant = j1;
        res[2] = courant;

        // Un joueur est n'est pas joueur courant lorsque ce n'est pas son tour de jouer un coup
        Joueur ancienCourant = j2;
        res[3] = ancienCourant;

        return res;
    }

    /**
     * Cette méthode effectue le déroulement de la partie du jeu sélectionné.
     * Pour les parties de code unique à chaque jeu on fait appel au méthodes de l'interface.
     * @param jeu l'interface jeu qui connait le jeu sélectionné
     * @param j1 le joueur 1
     * @param j2 le joueur 2
     * @param courant le joueur courant
     * @param ancienCourant le joueur ancien courant
     */
    public static void partie(IJeu jeu, Joueur j1, Joueur j2, Joueur courant, Joueur ancienCourant) {
        boolean jouerAuJeu = true;
        // TANT QUE les joueurs ne veulent jouer au jeu
        while (jouerAuJeu) {
            // Intialise la partie
            jeu.debutPartie();
            while (!(jeu.partieTerminee())) {
                // Affiche l'état de la partie
                jeu.etat();
                // Demande au joueur de saisir son coup
                jeu.debutTour(courant.getNom());

                // Le système exécute le coup saisie
                try {
                    // Le joueur saisit son coup
                    jeu.jouerCoup(courant instanceof IA);
                    // Le joueur courant a fini son tour, on change le joueur courant
                    Joueur tmp = courant;
                    courant = ancienCourant;
                    ancienCourant = tmp;
                } catch (CoupInvalideException e) {
                    // Si le coup est invalide on affiche le message d'erreur, puis la partie recommence
                    Ihm.afficher(e.getMessage());
                }

            }


            // Le système affiche le vainqueur puis lui ajoute une victoire à son attribut nbPartiesGagnees
            jeu.etat();
            if ((jeu.determinerGagnant())) {
                Ihm.afficher("Partie terminée. Victoire pour " + ancienCourant.getNom() + ".");
                if (ancienCourant.equals(j1)) {
                    j1.gagnePartie();
                } else {
                    j2.gagnePartie();
                }
            } else {
                Ihm.afficher("Partie terminée. Partie nulle, aucun vainqueur.");
            }

            // Le systeme demande on joueurs si ils souhaitent rejouer
            Ihm.afficher("Souhaitez vous refaire une partie ? \n" +
                    "Saisissez 'y' pour oui ou 'n' pour non.");
            boolean test = true;
            // TANT QUE la réponse donnée n'est pas oui('y') ou non('n')
            while (test) {
                // String reponse est la réponse saisie par le joueur
                String reponse = Ihm.saisie();

                // SI les joueurs ne souhaitent pas rejouer
                if (reponse.equals("n")) {
                    // Arrete le jeu
                    jouerAuJeu = false;
                    test = false;
                }
                // SI les joueurs souhaitent rejouer
                else if (reponse.equals("y")) {
                    // Le joueur commence la prochaine partie
                    courant = j1;
                    ancienCourant = j2;
                    test = false;
                }
                // SI la saisie n'est pas correcte
                else {
                    Ihm.afficher("Veuillez saisir 'y' pour oui ou 'n' pour non.");
                }
            }

        }

    }


    /**
     * méthode permettant d'afficher le vainqueur
     * le vainqueur est le joueur avec le plus de parties gagnées
     * la méthode affiche "ex aqueo" si les 2 joueurs sont à égalité
     * @param j1 le joueur 1
     * @param j2 le joueur 2
     */
    public static void finJeu(Joueur j1, Joueur j2) {
        // Le système affiche les scores et le gagnant du jeu ou ex aqueo si il y a égalité
        Ihm.afficher(j1.getNom() + " " + j1.getNbPartiesGagnees() + "-" + j2.getNbPartiesGagnees() + " " + j2.getNom());
        if (j1.getNbPartiesGagnees() > j2.getNbPartiesGagnees()) {
            Ihm.afficher("Vainqueur : " + j1.getNom());
        } else if (j1.getNbPartiesGagnees() < j2.getNbPartiesGagnees()) {
            Ihm.afficher("Vainqueur : " + j2.getNom());
        } else {
            Ihm.afficher("ex aequo");
        }
    }


}
