package projet.modele;

public class Joueur {
    private String nom;
    private int nbPartiesGagnees;


    /**
     * @param nom du joueur à créer
     */
    public Joueur(String nom) {
        this.nom = nom;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the nbPartiesGagnees
     */
    public int getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }

    /**
     * incrémente le nombre de parties gagnées par le joueur
     */
    public void gagnePartie() {
        nbPartiesGagnees++;
    }


}
