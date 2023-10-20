package projet.controleur.coupsP4;

import projet.modele.CoupInvalideException;
import projet.modele.modeleP4.Grille;
import projet.vue.Ihm;

public class CoupRotation implements ICoupP4 {

    // Le nombre de rotations que le joueur courant peut encore effectuer
    private int rotationsRestantesCourantes;

    public CoupRotation(int rotationsRestantesCourantes) {
        this.rotationsRestantesCourantes = rotationsRestantesCourantes;
    }

    /**
     * Permet d'effectuer la stratégie appropriée pour le coup du joueur
     * @param g la grille du jeu de Puissance 4
     * @param jetonCourant Le jeton du joueur courant
     * @return le nombre de rotations que le joueur courant peut encore effectuer
     * @throws CoupInvalideException si le coup joué est invalide
     */
    @Override
    public int strategieCoup(Grille g, String jetonCourant) throws CoupInvalideException {
        // Le joueur saisie son coup
        String rep = Ihm.saisie();
        // SI il a saisie d ou g
        if (rep.equals("d") || (rep.equals("g"))) {
            // SI il peut encore effectuer des rotations
            if (rotationsRestantesCourantes != 0) {
                // Le programme effectue une rotation dans le sens qu'à indiquer le joueur
                g.rotation(rep);
                // Le joueur n'a droit qu'à 4 rotations
                rotationsRestantesCourantes--;
            } else {
                // SINON si le joueur ne peut plus effectuer de rotation : message d'erreur
                throw new CoupInvalideException("Vous ne pouvez plus effectuer de rotations.");
            }
        }
        // SINON si le joueur a saisie une colonne
        else {
            try {
                // Le programmme essaye d'effectuer le coup
                g.gererCoup(Integer.parseInt(rep), jetonCourant);
            } catch (NumberFormatException e) {
                // Renvoie message d'erreur si le coup est invalide
                throw new CoupInvalideException("Veuillez saisir un nombre entier supérieur ou égal à 1 ou 'g' ou 'd'.");
            }
        }
        // Retourne le nombre de rotations que le joueur courant peut encore effectuer
        return rotationsRestantesCourantes;
    }
}
