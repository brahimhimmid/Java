package projet.controleur.coupsNim;

import projet.modele.CoupInvalideException;
import projet.modele.modeleNim.Tas;

public interface ICoupIAJeuNim {
    /**
     * Permet à l'IA de jouer son coup
     * @param lesTas l'état de la partie
     * @param nbMax la contrainte imposée en début de partie sur le nombre d'alumette qu'on peut enlever
     * @throws CoupInvalideException est demandé par la méthode gererCoup() mais l'IA ne se trompera jamais
     */
    void jouerIA(Tas lesTas, int nbMax) throws CoupInvalideException;
}
