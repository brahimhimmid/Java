package projet.controleur.coupsNim;

import projet.modele.CoupInvalideException;
import projet.modele.modeleNim.CoupNim;
import projet.modele.modeleNim.Tas;

import java.util.ArrayList;
import java.util.Random;

public class StrategieRandom implements ICoupIAJeuNim {


    /**
     * Permet à l'IA de jouer son coup
     * @param lesTas l'état de la partie
     * @param nbMax la contrainte imposée en début de partie sur le nombre d'alumette qu'on peut enlever
     * @throws CoupInvalideException est demandé par la méthode gererCoup() mais l'IA ne se trompera jamais
     */
    @Override
    public void jouerIA(Tas lesTas, int nbMax) throws CoupInvalideException {
        /*Afin de respecter le principe ouvert-fermé et de ne pas modifier la classe Tas du modele
         nous avons decider de ne pas implementer de getter dans Tas */

        Random rand = new Random();
        // Liste dans laquelle les tas où l'IA peut jouer sont repertoriés
        ArrayList<Integer> TasNonVides = new ArrayList<>();

        boolean parcours = true;

        // Numéro du tas parcouru
        int num = 1;

        // TANT QUE le programme n'a pas parcouru tous les tas
        while (parcours){
            try {
                // SI il reste des allumettes dans le tas parcouru
                if (lesTas.nbAllumettes(num) > 0) {
                    // Ajoute ce tas à la liste des Tas non vides
                    TasNonVides.add(num);
                }
                num++;
            } catch (ArrayIndexOutOfBoundsException e1){
                // Lorsque tous les tas ont été parcourus, on arr$ete le parcours
                parcours=false;
            }
        }

        // Choisi un tas au hasard
        int numDuTas = rand.nextInt(TasNonVides.toArray().length);
        int m = TasNonVides.get(numDuTas);


        int n;
        // SI il y a une restriction sur les coups ET la restriction est inférieure au nombre d'allumettes dans le tas
        if (lesTas.nbAllumettes(m) > nbMax && nbMax > 0) {
            // Choisir un nombre inférieur à la resriction
            n = rand.nextInt(nbMax);
        } else {
            // SINON choisir un nombre inférieur au nombre d'allumettes dans le tas
            n = rand.nextInt(lesTas.nbAllumettes(m));
        }
        n++;

        // L'IA joue le coup
        CoupNim coup = new CoupNim(m, n);
        lesTas.gererCoup(coup);


    }
}
