package projet.controleur.partieDeJeu;


import projet.controleur.coupsP4.CoupColonne;
import projet.controleur.coupsP4.CoupIA;
import projet.controleur.coupsP4.CoupRotation;
import projet.controleur.coupsP4.ICoupP4;
import projet.modele.CoupInvalideException;
import projet.modele.modeleP4.Grille;
import projet.vue.Ihm;

public class PartiePuissance4 implements IJeu {

    private static Grille g;
    private static String jeton2;
    private static String jetonCourant;
    private boolean avecRotation;
    private static int rotationsRestantes2;
    private static int rotationsRestantesCourantes;

    public PartiePuissance4() {

    }


    /**
     * Permet d'initialiser le jeu. Crée les objets du modèle spécifique au jeu.
     */
    @Override
    public void initialiserJeu() {
        PartiePuissance4.g = new Grille();

    }

    /**
     * Permet d'initialiser la partie et permet au joueur de saisir si il souhaite jouer avec les contraintes. Réenitialise les objets du modèle.
     */
    @Override
    public void debutPartie() {
        // Intialisation des jetons
        jetonCourant = "R";
        jeton2 = "J";
        g.initialiser();

        // Le systeme demande aux joueurs si ils souhaitent jouer avec rotation
        Ihm.afficher("Souhaitez vous jouer avec les rotations de grille ? Vous avez droit à 4 rotations chacun.\n" +
                "Saisissez 'y' pour oui ou 'n' pour non.");
        boolean test = true;
        while (test) {
            // String reponse est la réponse saisie par le joueur
            String reponse = Ihm.saisie();

            // SI les joueurs ne souhaitent pas jouer avec rotations
            if (reponse.equals("n")) {
                // Le jeu se déroulera sans rotations
                avecRotation = false;
                test = false;
            }
            // SI les joueurs souhaitent jouer avec rotations
            else if (reponse.equals("y")) {
                // Le jeu se déroulera avec rotations
                avecRotation = true;
                rotationsRestantesCourantes = 4;
                rotationsRestantes2 = 4;
                test = false;
            }
            // SI la saisie n'est pas correcte
            else {
                Ihm.afficher("Veuillez saisir 'y' pour oui ou 'n' pour non.");
            }
        }
    }

    /**
     * Permet de tester si la partie est terminée.
     *
     * @return true si la partie est terminee
     */
    @Override
    public boolean partieTerminee() {
        // La partie est terminée si un joueur gagne ou si la grille est pas pleine
        return g.partieTerminee(jeton2) || g.grillePleine();
    }

    /**
     * Permet d'afficher l'état de la partie.
     */
    @Override
    public void etat() {
        Ihm.afficher(g.toString());
    }

    /**
     * Permet d'afficher le message qui demande au joueur de jouer un coup sous une forme spécifique au jeu.
     *
     * @param j nom du joueur
     */
    public void debutTour(String j) {
        if (avecRotation) {
            Ihm.afficher(j + " : à vous de jouer un coup. Saisissez le numéro de la colonne dans laquelle vous souhaitez placer votre jeton.\n" +
                    "Ou saisissez 'g' pour effectuer une rotation à gauche, ou saisissez 'd' pour effectuer un rotation à droite. \n" +
                    "Vous avez encore droit à " + rotationsRestantesCourantes + " rotation(s).");
        } else {
            Ihm.afficher(j + " : à vous de jouer un coup. Saisissez le numéro de la colonne dans laquelle vous souhaitez placer votre jeton.");
        }
    }

    /**
     * Permet au joueur de jouer son coup. Il peut effectuer plusieurs stratégies
     *
     * @throws CoupInvalideException si le coup est invalide
     */
    @Override
    public void jouerCoup(boolean isAI) throws CoupInvalideException {

        ICoupP4 test;

        // Si le joueur courant est l'IA
        if (isAI) {

            test = new CoupIA(rotationsRestantesCourantes,avecRotation);

        } else {
            // SI une partie avec rotation est en cours le joueurs peut effectuer une rotation ou saisir une colonne
            if (avecRotation) {

                test = new CoupRotation(rotationsRestantesCourantes);
            }
            // SINON la partie en cours est jouée sans rotation, le joueur n'a acces qu'à une seule stratégie
            else {
                test = new CoupColonne();
            }
        }
        int tmp = rotationsRestantesCourantes;
        rotationsRestantesCourantes = rotationsRestantes2;
        rotationsRestantes2 = tmp;


        // Nombre de rotation restantes du joueur qui vient de jouer
        rotationsRestantes2 = test.strategieCoup(g, jetonCourant);

        String tm = jetonCourant;
        jetonCourant = jeton2;
        jeton2 = tm;
    }

    /**
     * Permet de déterminer si la partie s'est terminée car il y a un gagnant ou si c'est à cause d'un match nul
     *
     * @return true si il y a un gagnant dans la partie
     */
    @Override
    public boolean determinerGagnant() {
        //SI suite à une rotation, les 2 joueurs sont gagnants en même temps
        if (g.partieTerminee(jeton2) && g.partieTerminee(jetonCourant)) {
            // Alors il n'y a pas de gagnant
            return false;
        }
        //SI la grille est pleine et qu'aucun des joueurs est gagnant
        if (g.grillePleine() && !(g.partieTerminee(jeton2) || g.partieTerminee(jetonCourant))) {
            // Alors il n'y a pas de gagnant
            return false;
        }
        return true;
    }


}

