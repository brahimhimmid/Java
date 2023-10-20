package projet.controleur.partieDeJeu;

import projet.controleur.coupsNim.ICoupIAJeuNim;
import projet.controleur.coupsNim.StrategieRandom;
import projet.modele.CoupInvalideException;
import projet.modele.modeleNim.Tas;
import projet.modele.modeleNim.CoupNim;
import projet.vue.Ihm;

public class PartieJeuNim implements IJeu {
    private static Tas lesTas;
    private int nbMax;

    public PartieJeuNim() {
    }


    /**
     * Permet d'initialiser le jeu. Crée les objets du modèle spécifique au jeu.
     */
    @Override
    public void initialiserJeu() {
        int nbTas = 0;
        while (nbTas < 1) {
            Ihm.afficher("Veuillez saisir le nombre de tas avec lesquels jouer.\n " +
                    "Il doit être supérieur ou égal à 1.");

            // L'utilisateur saisit le nombre de tas utiliser dans la partie
            nbTas = Ihm.saisieNb();
        }
        // Un objet tas est créer en utilisant la saisie de l'utilisateur en paramètre
        PartieJeuNim.lesTas = new Tas(nbTas);

    }

    /**
     * Permet d'initialiser la partie et permet au joueur de saisir si il souhaite jouer avec les contraintes. Réenitialise les objets du modèle.
     */
    @Override
    public void debutPartie() {
        lesTas.initialiser();

        Ihm.afficher("Veuillez saisir le nombre maximum d'allumettes que l'on peut retirer à chaque coup.\n" +
                "Saisissez 0 si vous souhaitez jouer sans nombre maximum.");
        nbMax = Ihm.saisieNb();
    }

    /**
     * Permet de tester si la partie est terminée.
     *
     * @return true si la partie est terminee
     */
    @Override
    public boolean partieTerminee() {
        return lesTas.partieTerminee();
    }

    /**
     * Permet d'afficher l'état de la partie.
     */
    public void etat() {
        Ihm.afficher(lesTas.toString());
    }

    /**
     * Permet d'afficher le message qui demande au joueur de jouer un coup sous une forme spécifique au jeu.
     *
     * @param j nom du joueur
     */
    public void debutTour(String j) {

        Ihm.afficher(j + " : à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.");
        // SI les joueurs ont décidés de jouer avec une contrainte, on la précise à l'affichage
        if (nbMax != 0) {
            Ihm.afficher("Vous ne pouvez pas retirer plus de " + nbMax + " allumettes.");
        }
    }

    /**
     * Permet au joueur de jouer son coup.
     *
     * @throws CoupInvalideException si le coup est invalide
     */
    @Override
    public void jouerCoup(boolean isAI) throws CoupInvalideException {


        // Si le joueur courant est l'IA
        if (isAI) {
            // L'IA effectue sa strategie
            ICoupIAJeuNim strat = new StrategieRandom();
            strat.jouerIA(lesTas, nbMax);

            
        } else {
            CoupNim coup = Ihm.saisieCoup();
            // SI le coup ne respecte pas la contrainte, le joueur doit resaisir son coup
            if (coup.getNbAllumettes() > nbMax && nbMax != 0) {
                throw new CoupInvalideException("Vous ne pouvez pas retirer plus de " + nbMax + " allumettes.");
            } else {
                lesTas.gererCoup(coup);
            }
        }
    }

    /**
     * Permet de déterminer si la partie s'est terminée car il y a un gagnant ou si c'est à cause d'un match nul
     * Il ne peut pas y avoir de match nul au jeu de Nim, donc la méthode renvoie toujours vrai
     *
     * @return true si il y a un gagnant dans la partie
     */
    @Override
    public boolean determinerGagnant() {
        return true;
    }


}
