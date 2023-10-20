package projet.controleur.coupsP4;

import projet.modele.CoupInvalideException;
import projet.modele.modeleP4.Grille;
import projet.vue.Ihm;

public class CoupColonne implements ICoupP4 {



    /**
     * Permet d'effectuer la stratégie appropriée pour le coup du joueur
     * @param g la grille du jeu de Puissance 4
     * @param jetonCourant Le jeton du joueur courant
     * @return le nombre de rotations que le joueur courant peut encore effectuer
     * @throws CoupInvalideException si le coup joué est invalide
     */
    @Override
    public int strategieCoup(Grille g, String jetonCourant) throws CoupInvalideException {
        // le joueur saisie la colonne
        int coup = Ihm.saisieNb();
        g.gererCoup(coup, jetonCourant);
        // Une partie sans rotations est en cours, le nombre de rotations restantes est toujours 0
        return 0;
    }
}
