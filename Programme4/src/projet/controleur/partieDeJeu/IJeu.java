package projet.controleur.partieDeJeu;

import projet.modele.CoupInvalideException;

public interface IJeu {

    /**
     * Permet d'initialiser le jeu. Crée les objets du modèle spécifique au jeu.
     */
    void initialiserJeu();

    /**
     * Permet d'initialiser la partie et permet au joueur de saisir si il souhaite jouer avec les contraintes. Réenitialise les objets du modèle.
     */
    void debutPartie();

    /**
     * Permet de tester si la partie est terminée.
     * @return true si la partie est terminee
     */
    boolean partieTerminee();

    /**
     * Permet d'afficher le message qui demande au joueur de jouer un coup sous une forme spécifique au jeu.
     * @param j nom du joueur
     */
    void debutTour(String j);

    /**
     * Permet d'afficher l'état de la partie.
     */
    void etat();

    /**
     * Permet au joueur de jouer son coup.
     * @throws CoupInvalideException
     */
    void jouerCoup(boolean isAI) throws CoupInvalideException;

    /**
     * Permet de déterminer si la partie s'est terminée car il y a un gagnant ou si c'est à cause d'un match nul
     * @return true si il y a un gagnant dans la partie
     */
    boolean determinerGagnant();

}
