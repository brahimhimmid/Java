package projet.controleur.coupsP4;

import projet.controleur.coupsP4.strategieP4IA.CoupColonneIA;
import projet.controleur.coupsP4.strategieP4IA.CoupRotationIA;
import projet.controleur.coupsP4.strategieP4IA.IStrategieIAP4;
import projet.modele.CoupInvalideException;
import projet.modele.modeleP4.Grille;

public class CoupIA implements ICoupP4 {
    // Le nombre de rotations restantes à l'IA après avoir jouer son coup
    private final int rotationsRestantes;
    // True si les rotations sont autorisés, False sinon
    private final boolean avecRotation;

    public CoupIA(int rotationsRestantes, boolean avecRotation) {
        this.rotationsRestantes = rotationsRestantes;
        this.avecRotation = avecRotation;
    }

    /**
     * Permet d'effectuer la stratégie appropriée pour le coup du joueur
     * @param g la grille du jeu de Puissance 4
     * @param jIA Le jeton du joueur courant
     * @return le nombre de rotations que le joueur courant peut encore effectuer
     * @throws CoupInvalideException si le coup joué est invalide mais l'IA ne saisit jamais de coup invalide
     */
    @Override
    public int strategieCoup(Grille g, String jIA) throws CoupInvalideException {



        // Initialisation de la strategie de l'IA
        IStrategieIAP4 strategieIA;

        // simule une rotation a droite et a gauche, si cette rotation fait gagner l'IA, L'IA l'execute
        Grille gSimD = new Grille();
        gSimD.setLaGrille(g.getLaGrille());
        gSimD.rotation("d");
        Grille gSimG = new Grille();
        gSimG.setLaGrille(g.getLaGrille());
        gSimG.rotation("g");

        boolean iaPeutGagner = gSimD.partieTerminee(jIA) || gSimG.partieTerminee(jIA);
        boolean advPeutPasGagner = !(gSimD.partieTerminee("R") || gSimG.partieTerminee("R"));

        // SI l'IA peut gagner en faisant une rotation ET que l'adversaire ne gagne pas en meme temps (match nul) ET que les rotations sont autorisées dans la partie
        if (iaPeutGagner && advPeutPasGagner && avecRotation) {
            // Strategie rotation
            strategieIA = new CoupRotationIA(gSimD.partieTerminee(jIA));

        } else {
            // Strategie colonne
            strategieIA = new CoupColonneIA(avecRotation);
        }

        // joue le coup approprié
        return strategieIA.gererCoupIA(g, rotationsRestantes);
    }
}