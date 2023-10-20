package projet.modele.modeleP4;


import projet.modele.CoupInvalideException;

public class Grille {
    /**
     * représente l'état courant de la partie
     */
    private String[][] laGrille;

    /**
     * crée une grille de String vide
     */
    public Grille() {
        laGrille = new String[7][7];
    }

    /**
     * remplit la grille avec des String " ". Permet d'initialiser une grille vide
     */
    public void initialiser() {
        for (int i = 0; i < laGrille.length; i++) {
            for (int j = 0; j < laGrille[0].length; j++) {
                laGrille[i][j] = " ";
            }
        }
    }


    public String[][] getLaGrille() {
        return laGrille;
    }

    public void setLaGrille(String[][] laGrille) {
        Grille grille2 = new Grille();
        grille2.initialiser();
        for (int col = 0 ; col<=laGrille.length-1; col++){
            for (int li = laGrille[0].length-1 ; li>=0; li--){
                if (!laGrille[li][col].equals(" ")){
                    try {
                        grille2.gererCoup(col+1,laGrille[li][col]);
                    } catch ( CoupInvalideException e){
                        // n'arrive jamais
                    }

                }
            }
        }
        this.laGrille=grille2.laGrille;
    }

    /**
     * Permet de vérifier si la grille est pleine
     *
     * @return true si la grille est pleine
     */
    public boolean grillePleine() {
        for (int j = 0; j < laGrille[0].length; j++) {
            if (laGrille[0][j].equals(" ")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne vrai si la partie est terminée et faux sinon. La partie est terminée si 4 jetons sont alignés horizontalement, verticalement, diagonalement,
     * ou si la grille est pleine.
     *
     * @param jeton la couleur du jeton du joueur qui vient de jouer
     * @return true si la partie est terminée
     */
    public boolean partieTerminee(String jeton) {

        // vérifie si 4 jetons sont alignés horizontalement
        for (String[] strings : laGrille) {
            for (int j = 0; j < laGrille[0].length - 3; j++) {
                if (strings[j].equals(jeton) &&
                        strings[j + 1].equals(jeton) &&
                        strings[j + 2].equals(jeton) &&
                        strings[j + 3].equals(jeton)) {
                    return true;
                }
            }
        }
        // vérifie si 4 jetons sont alignés verticalement
        for (int i = 0; i < laGrille.length - 3; i++) {
            for (int j = 0; j < laGrille[0].length; j++) {
                if (laGrille[i][j].equals(jeton) &&
                        laGrille[i + 1][j].equals(jeton) &&
                        laGrille[i + 2][j].equals(jeton) &&
                        laGrille[i + 3][j].equals(jeton)) {
                    return true;
                }
            }
        }
        // vérifie si 4 jetons sont alignés diagonalement /
        for (int i = 3; i < laGrille.length; i++) {
            for (int j = 0; j < laGrille[0].length - 3; j++) {
                if (laGrille[i][j].equals(jeton) &&
                        laGrille[i - 1][j + 1].equals(jeton) &&
                        laGrille[i - 2][j + 2].equals(jeton) &&
                        laGrille[i - 3][j + 3].equals(jeton)) {
                    return true;
                }
            }
        }
        // vérifie si 4 jetons sont alignés diagonalement \
        for (int i = 0; i < laGrille.length - 3; i++) {
            for (int j = 0; j < laGrille[0].length - 3; j++) {
                if (laGrille[i][j].equals(jeton) &&
                        laGrille[i + 1][j + 1].equals(jeton) &&
                        laGrille[i + 2][j + 2].equals(jeton) &&
                        laGrille[i + 3][j + 3].equals(jeton)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * retourne l'état de la partie sous forme d'une grille de String constituées des jetons.
     * "J" représente un jeton jaune. "R" représente un jeton rouge. " " représente une case vide.
     *
     * @return l'état de la partie
     */
    @Override
    public String toString() {
        String s = " 1 2 3 4 5 6 7\n";
        for (String[] strings : laGrille) {
            s += "|";
            for (int j = 0; j < laGrille[0].length; j++) {
                s += "" + strings[j];
                s += "|";
            }
            s += "\n";
        }
        return s;
    }


    /**
     * modifie l'état de la partie en fonction du coup passé en paramètre
     *
     * @param coup la colonne choisie
     * @throws CoupInvalideException si le coup est invalide
     */
    public void gererCoup(int coup, String jeton) throws CoupInvalideException {
        /* SI la colonne existe et n'est pas pleine */
        if (coup > 0 && coup <= laGrille.length && laGrille[0][coup - 1].equals(" ")) {
            // Ajoute le jeton le plus bas possible dans la colonne
            for (int i = laGrille.length - 1; i >= 0; i--) {
                if (laGrille[i][coup - 1].equals(" ")) {
                    laGrille[i][coup - 1] = jeton;
                    break;
                }
            }
        } else {
            throw new CoupInvalideException("Le coup est invalide, rejouez");
        }
    }

    /**
     * effectue une rotation à gauche ou à droite de la grille
     * @param direction la direction de la rotation
     * @throws CoupInvalideException si le joueur ne peut pas faire de rotation
     */
    public void rotation(String direction) throws CoupInvalideException {
        // La nouvelle grille qui sera renvoyé est créée et initialisée
        Grille grille2 = new Grille();
        grille2.initialiser();

        // Effectue une rotation vers la gauche
        if (direction.equals("g")) {
            for (int i = 0; i < laGrille.length; i++) {
                for (int j = 0; j < laGrille[0].length; j++) {
                    if (!laGrille[i][j].equals(" ")) {
                        grille2.gererCoup(i + 1, laGrille[i][j]);
                    }
                }
            }
        }
        // Effectue une rotation vers la droite
        else if (direction.equals("d")) {
            for (int i = 0; i < laGrille.length; i++) {
                for (int j = laGrille[0].length-1; j >= 0 ; j--) {
                    if (!laGrille[i][j].equals(" ")) {
                        grille2.gererCoup(laGrille.length - i, laGrille[i][j]);
                    }
                }
            }
        } else {
            throw new CoupInvalideException("Veuillez saisir 'g' ou 'd'.");
        }
        laGrille=grille2.laGrille;
    }



}
