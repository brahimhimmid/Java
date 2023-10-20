package projet.controleur.coupsP4.strategieP4IA;

import projet.modele.CoupInvalideException;
import projet.modele.modeleP4.Grille;

public class CoupRotationIA implements IStrategieIAP4 {
    // permet de savoir si la rotation qui fait gagner est une rotation a droite ou a gauche
    private final boolean rotationGagnante;



    public CoupRotationIA(boolean rotationGagnante) {
        this.rotationGagnante = rotationGagnante;
    }

    /**
     * Permet à l'IA d'effectuer sa stratégie
     * @param g la grille de la partie de puissance 4 en cours
     * @param rotationsRestantes le nombre de rotations que l'IA peut encore effectuer
     * @return le nombre de rotations que l'IA peut encore effectuer
     */
    @Override
    public int gererCoupIA(Grille g, int rotationsRestantes) {
        try {
            // SI la rotation gagnante est une rotation a droite
            if (rotationGagnante) {
                // faire rotation vers la droite
                g.rotation("d");
            } else {
                // SINON rotation vers la gauche
                g.rotation("g");
            }
        } catch (CoupInvalideException e){
            //n'arrive jamais
        }
        return rotationsRestantes-1;
    }
}
