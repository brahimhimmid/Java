package projet.controleur.coupsP4.strategieP4IA;

import projet.modele.modeleP4.Grille;

public interface IStrategieIAP4 {
    /**
     * Permet à l'IA d'effectuer sa stratégie
     * @param g la grille de la partie de puissance 4 en cours
     * @param rotationsRestantes le nombre de rotations que l'IA peut encore effectuer
     * @return le nombre de rotations que l'IA peut encore effectuer
     */
    int gererCoupIA (Grille g, int rotationsRestantes);
}
