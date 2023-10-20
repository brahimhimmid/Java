package projet.controleur.coupsP4.strategieP4IA;

import projet.modele.CoupInvalideException;
import projet.modele.modeleP4.Grille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoupColonneIA implements IStrategieIAP4 {
    private final boolean avecRotation;

    public CoupColonneIA(boolean avecRotation) {
        this.avecRotation = avecRotation;
    }

    /**
     * Verifie si le joueur avec les jetons "jeton" peut aligner "x" jetons en placant son jeton a ligne "li" et la colonne "col"
     * dans la grille g
     *
     * @param jeton R ou J la couleur des jetons alignés que l'on teste
     * @param li    la ligne à laquelle sera placer le jeton
     * @param col   la colonne dans laquelle on veut placer le jeton
     * @param x     le nombre de jeton alignés qu'on veut tester
     * @param g     la grille dans laquelle on teste les alignements
     * @return true si x jetons peuvent etre aligner en jouant le coup
     */
    public boolean peutAlignerX(String jeton, int li, int col, int x, String[][] g) {
        // TODO Cette méthode peut sûrement être améliorer mais on a plus le temps
        boolean horizontalG = true;
        boolean horizontalD = true;
        boolean diagonalGb = true;
        boolean diagonalDb = true;
        boolean diagonalGh = true;
        boolean diagonalDh = true;

        boolean horizontal = true;
        boolean vertical = true;
        boolean diagonalNwSe = true;
        boolean diagonalSwNe = true;

        for (int i = 1; i < x; i++) {

            // Verifie si X-1 jetons sont placés à gauche, afin de savoir si on peut aligner X jetons horizontalement
            if (0 <= col - i && col - i <= 6) {
                horizontalG = horizontalG && g[li][col - i].equals(jeton);
            } else {
                horizontalG = false;
            }
            // Verifie si X-1 jetons sont placés à droite, afin de savoir si on peut aligner X jetons horizontalement
            if (0 <= col + i && col + i <= 6) {
                horizontalD = horizontalD && g[li][col + i].equals(jeton);
            } else {
                horizontalD = false;
            }

            // Verifie si X-1 jetons sont placés sous le coup à jouer, afin de savoir si on peut aligner X jetons verticalement
            if (0 <= li + i && li + i <= 6) {
                vertical = vertical && g[li + i][col].equals(jeton);
            } else {
                vertical = false;
            }

            // Verifie si X-1 jetons sont placés en bas à gauche du coup à jouer, afin de savoir si on peut aligner X jetons diagonalement /
            if (li + i <= 6 && 0 <= col - i) {
                diagonalGb = diagonalGb && g[li + i][col - i].equals(jeton);
            } else {
                diagonalGb = false;
            }
            // Verifie si X-1 jetons sont placés en bas à droite du coup à jouer, afin de savoir si on peut aligner X jetons diagonalement \
            if (li + i <= 6 && col + i <= 6) {
                diagonalDb = diagonalDb && g[li + i][col + i].equals(jeton);
            } else {
                diagonalDb = false;
            }
            // Verifie si X-1 jetons sont placés en haut à gauche du coup à jouer, afin de savoir si on peut aligner X jetons diagonalement \
            if (0 <= li - i && 0 <= col - i) {
                diagonalGh = diagonalGh && g[li - i][col - i].equals(jeton);
            } else {
                diagonalGh = false;
            }
            // Verifie si X-1 jetons sont placés en haut à droite du coup à jouer, afin de savoir si on peut aligner X jetons diagonalement /
            if (0 <= li - i && col + i <= 6) {
                diagonalDh = diagonalDh && g[li - i][col + i].equals(jeton);
            } else {
                diagonalDh = false;
            }


            //Verifie si X jetons peuvent être alignés horizontalement
            horizontal = horizontalG || horizontalD;
            // SI on ne sort pas de la grill
            if (0 <= col - 1 && col + 1 <= 6) {
                // SI on veut aligner 3 jetons
                if (x == 3) {
                    // Verifie le cas ou 1 jeton est placé à gauche ET un jeton est placé à droite
                    horizontal = horizontal || (g[li][col - 1].equals(jeton) && g[li][col + 1].equals(jeton));
                }
            }
            // gJdd est le cas ou 1 jeton est placé à gauche ET 2 jetons sont placés à droite
            boolean gJdd = false;
            // gJdd est le cas ou 2 jetons sont placés à gauche ET 1 jeton est placé à droite
            boolean ggJd = false;
            // SI on ne sort pas de la grille
            if (0 <= col - 1 && col + 2 <= 6) {
                gJdd = (g[li][col - 1].equals(jeton) && g[li][col + 2].equals(jeton) && g[li][col + 1].equals(jeton));
            }
            if (0 <= col - 2 && col + 1 <= 6) {
                ggJd = (g[li][col - 1].equals(jeton) && g[li][col - 2].equals(jeton) && g[li][col + 1].equals(jeton));
            }
            // SI on veut aligner 4 jetons
            if (x == 4) {
                horizontal = horizontal ||
                        gJdd ||
                        ggJd;
            }


            // Verifie si X jetons peuvent être aligner diagonalement \ (Nord-ouest vers Sud-est)
            diagonalNwSe = diagonalGh || diagonalDb;
            // SI on ne sort pas de la grille
            if ((0 <= li - 1 && li + 1 <= 6) && (0 <= col - 1 && col + 1 <= 6)) {
                // SI on veut aligner 3 jetons
                if (x == 3) {
                    diagonalNwSe = diagonalNwSe || (g[li - 1][col - 1].equals(jeton) && g[li + 1][col + 1].equals(jeton));
                }
            }

            // gJddDiagonaleNwSe est le cas ou 1 jeton est placé en haut à gauche ET 2 jetons sont placés en bas à droite du coup à jouer
            boolean gJddDiagonaleNwSe = false;
            // ggJdDiagonaleNwSe est le cas ou 2 jetons est placé en haut à gauche ET 1 jeton est placé en bas à droite du coup à jouer
            boolean ggJdDiagonaleNwSe = false;
            // SI on ne sort pas de la grille
            if ((0 <= li - 1 && li + 2 <= 6) && (0 <= col - 1 && col + 2 <= 6)) {
                gJddDiagonaleNwSe = (g[li - 1][col - 1].equals(jeton) && g[li + 2][col + 2].equals(jeton) && g[li + 1][col + 1].equals(jeton));
            }
            if ((0 <= li - 2 && li + 1 <= 6) && (0 <= col - 2 && col + 1 <= 6)) {
                ggJdDiagonaleNwSe = (g[li - 1][col - 1].equals(jeton) && g[li - 2][col - 2].equals(jeton) && g[li + 1][col + 1].equals(jeton));
            }
            // SI on veut aligner 4 jetons
            if (x == 4) {
                diagonalNwSe = diagonalNwSe ||
                        gJddDiagonaleNwSe ||
                        ggJdDiagonaleNwSe;
            }

            // Verifie si X jetons peuvent être aligner diagonalement / (Sud-ouest vers Nord-est)
            diagonalSwNe = diagonalGb || diagonalDh;
            // SI on ne sort pas de la grille
            if ((0 <= li - 1 && li + 1 <= 6) && (0 <= col - 1 && col + 1 <= 6)) {
                // SI on veut aligner 3 jetons
                if (x == 3) {
                    diagonalSwNe = diagonalSwNe || (g[li + 1][col - 1].equals(jeton) && g[li - 1][col + 1].equals(jeton));
                }
            }

            // gJddDiagonaleSwNe est le cas ou 1 jeton est placé en bas à gauche ET 2 jetons sont placés en haut à droite du coup à jouer
            boolean gJddDiagonaleSwNe = false;
            // ggJdDiagonaleSwNe est le cas ou 2 jetons est placé en bas à gauche ET 1 jeton est placé en haut à droite du coup à jouer
            boolean ggJdDiagonaleSwNe = false;
            // SI on ne sort pas de la grille
            if ((0 <= li - 2 && li + 1 <= 6) && (0 <= col - 1 && col + 2 <= 6)) {
                gJddDiagonaleSwNe = (g[li + 1][col - 1].equals(jeton) && g[li - 2][col + 2].equals(jeton) && g[li - 1][col + 1].equals(jeton));
            }
            if ((0 <= li - 1 && li + 2 <= 6) && (0 <= col - 2 && col + 1 <= 6)) {
                ggJdDiagonaleSwNe = (g[li + 1][col - 1].equals(jeton) && g[li + 2][col - 2].equals(jeton) && g[li - 1][col + 1].equals(jeton));
            }
            // SI on veut aligner 4 jetons
            if (x == 4) {
                diagonalSwNe = diagonalSwNe ||
                        gJddDiagonaleSwNe ||
                        ggJdDiagonaleSwNe;
            }


        }

        return horizontal || vertical || diagonalNwSe || diagonalSwNe;
    }

    /**
     *  Permet à l'IA d'effectuer sa stratégie
     * @param g la grille de la partie de puissance 4 en cours
     * @param rotationsRestantes le nombre de rotations que l'IA peut encore effectuer
     * @return le nombre de rotations que l'IA peut encore effectuer
     */
    @Override
    public int gererCoupIA(Grille g, int rotationsRestantes) {
        // <score, liste de coups>
        Map<Integer, ArrayList<Integer>> mapScores = new HashMap<>();
        for (int s = 1; s <= 7; s++) {
            mapScores.put(s, new ArrayList<>());
        }
        int score = 0;
        // POUR CHAQUE colonne
        for (int coup = 0; coup < 7; coup++) {
            // POUR CHAQUE coup possible
            for (int li = 6; li >= 0; li--) {
                // SI coup disponible trouvé
                if (g.getLaGrille()[li][coup].equals(" ")) {


                    score = 1;
                    int scr = score;
                    String j = "R";
                    // Attribuer un score au coup
                    for (int i = 2; i <= 4; i++) {
                        for (int a = 0; a < 2; a++) {
                            scr++;
                            // SI l'adversaire/l'IA peut aligner i jetons
                            if (peutAlignerX(j, li, coup, i, g.getLaGrille())) {
                                score = scr;
                            }
                            // "R" = jeton de l'adversaire, "J" = jeton de l'IA
                            if (j.equals("R")) j = "J";
                            else j = "R";
                        }
                    }
                    break;
                }
            }

            // Attribue le score au coup
            if (score > 0) {
                mapScores.get(score).add(coup);

            }

        }

        // Pour chaque coup classé par score, vérifier si il peut etre jouer, si oui le jouer
        Grille gSimD = new Grille();
        Grille gSimG = new Grille();
        Grille gSim = new Grille();
        boolean tourFini = false;
        ArrayList<Integer> compt = new ArrayList<>();

        // Pour chaque score
        for (int i = 7; i > 0; i--) {
            // Pour chaque coup ayant ce score
            for (int c = 0; c < mapScores.get(i).size(); c++) {

                // Simule les rotations de l'adversaire après le coup
                try {
                    gSim.setLaGrille(g.getLaGrille());
                    gSim.gererCoup(mapScores.get(i).get(c) + 1, "J");
                    gSimD.setLaGrille(gSim.getLaGrille());
                    gSimD.rotation("d");
                    gSimG.setLaGrille(gSim.getLaGrille());
                    gSimG.rotation("g");
                    compt.add(mapScores.get(i).get(c) + 1);
                } catch (CoupInvalideException e) {
                    // n'arrive jamais
                }

                // SI le joueur ne gagne pas en faisant une rotation après le coup de l'IA
                if (!(gSimD.partieTerminee("R") || gSimG.partieTerminee("R")) || !avecRotation) {
                    // L'IA joue le coup
                    try {
                        g.gererCoup(mapScores.get(i).get(c) + 1, "J");
                    } catch (CoupInvalideException e) {
                        //n'arrive jamais
                    }
                    // Arrete de naviguer la liste
                    tourFini = true;
                    break;
                }

            }
            // SI l'IA a jouer son coup, son tour est fini, elle arrête le parcours des coups possibles
            if (tourFini){
                break;
            }
            // SINON SI l'IA a parcouru tous les coups possibles et n'a pas trouver de coup pour empecher le joueur de gagner avec une rotation
            else if (i == 1) {
                try {
                    g.gererCoup(compt.get(0), "J");
                } catch (CoupInvalideException e) {
                    //n'arrive jamais
                }
            }
        }
        return rotationsRestantes;
    }
}
