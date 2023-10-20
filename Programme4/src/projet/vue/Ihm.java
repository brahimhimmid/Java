package projet.vue;

import projet.modele.modeleNim.CoupNim;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe permettant de gérer les interactions avec l'utilisateur
 */
public class Ihm {


    /**
     * Gère la saisie d'un String s par l'utilisateur
     *
     * @return s, ce qu'a saisie de l'utilisateur
     */
    public static String saisie() {
        Scanner sc = new Scanner(System.in);
        // s prend la valeur de la saisie de l'utilisateur
        String s = sc.next();
        return s;
    }


    /**
     * Gère la saisie d'un int x par l'utilisateur
     *
     * @return x, ce qu'a saisie de l'utilisateur
     */

    public static int saisieNb() {
        //Initilisation du int
        int x = 0;
        try {
            // On utilise la méthode saisie() puis on convertit ce qui a été saisie en int
            x = Integer.parseInt(saisie());
        } catch (NumberFormatException e) {
            // Si on ne peut pas faire la conversion, on recommence la saisie
            System.out.println("Veuillez saisir un nombre entier.");
            x = saisieNb();
        }

        return x;
    }

    public static CoupNim saisieCoup() {
        Scanner sc = new Scanner(System.in);
        CoupNim c = new CoupNim(0, 0);
        try {
            int m = sc.nextInt();
            int n = sc.nextInt();
            c = new CoupNim(m, n);
        } catch (InputMismatchException e) {
            System.out.println("n et m doivent tous les deux être des nombres entiers. \n" +
                    "Veuillez saisir un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.");
            saisieCoup();
        }


        return c;
    }

    /**
     * Affiche sur l'interface le message saisit en paramètre
     *
     * @param s le message à afficher
     */
    public static void afficher(String s) {
        System.out.println(s);
    }


}


