package projet.controleur.coupsP4;

import projet.modele.CoupInvalideException;
import projet.modele.modeleP4.Grille;

public interface ICoupP4 {
    /**
     * Permet d'effectuer la stratégie appropriée pour le coup du joueur
     * @param g la grille du jeu de Puissance 4
     * @param jeton Le jeton du joueur courant
     * @return le nombre de rotations que le joueur courant peut encore effectuer
     * @throws CoupInvalideException si le coup joué est invalide
     */
    int strategieCoup(Grille g, String jeton) throws CoupInvalideException;
}
