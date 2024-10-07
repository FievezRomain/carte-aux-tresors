package com.carte.model;

/**
 * Représente un aventurier sur la carte, avec sa position, son orientation
 * et sa séquence de mouvements.
 */
public class Aventurier {
    private String nom;
    private int x;
    private int y;
    private char orientation;
    private final String mouvements;
    private int indexMouvement;
    private int nbTresorsRamasses;

    /**
     * Initialise un aventurier avec un nom, une position, une orientation
     * et une séquence de mouvements.
     *
     * @param nom          Le nom de l'aventurier.
     * @param x            La position x (colonne) de l'aventurier.
     * @param y            La position y (ligne) de l'aventurier.
     * @param orientation  L'orientation initiale de l'aventurier ('N', 'S', 'E', 'O').
     * @param mouvements   La séquence de mouvements de l'aventurier.
     */
    public Aventurier(String nom, int x, int y, char orientation, String mouvements) {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.mouvements = mouvements;
        this.indexMouvement = 0;
        this.nbTresorsRamasses = 0;
    }

    /**
     * Exécute le prochain mouvement de la séquence.
     *
     * @param carte La carte sur laquelle l'aventurier se déplace.
     */
    public void executerProchainMouvement(Carte carte) {
        if (indexMouvement < mouvements.length()) {
            char mouvement = mouvements.charAt(indexMouvement);
            if (mouvement == 'A') {
                avancer();
            } else if (mouvement == 'D') {
                tournerDroite();
            } else if (mouvement == 'G') {
                tournerGauche();
            }
            indexMouvement++;
        }
    }

    /**
     * Fait avancer l'aventurier d'une case dans la direction de son orientation actuelle.
     */
    private void avancer() {
        switch (orientation) {
            case 'N':
                y--;
                break;
            case 'S':
                y++;
                break;
            case 'E':
                x++;
                break;
            case 'O':
                x--;
                break;
            default:
                break;
        }
    }

    /**
     * Fait tourner l'aventurier à droite.
     */
    private void tournerDroite() {
        switch (orientation) {
            case 'N':
                orientation = 'E';
                break;
            case 'S':
                orientation = 'O';
                break;
            case 'E':
                orientation = 'S';
                break;
            case 'O':
                orientation = 'N';
                break;
            default:
                break;
        }
    }

    /**
     * Fait tourner l'aventurier à gauche .
     */
    private void tournerGauche() {
        switch (orientation) {
            case 'N':
                orientation = 'O';
                break;
            case 'S':
                orientation = 'E';
                break;
            case 'E':
                orientation = 'N';
                break;
            case 'O':
                orientation = 'S';
                break;
            default:
                break;
        }
    }

    /**
     * Incrémente le nombre de trésors ramassés par l'aventurier.
     */
    public void incrementerTresorsRamasses() {
        nbTresorsRamasses++;
    }

    /**
     * Retourne la position X de l'aventurier.
     *
     * @return La position X.
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la position Y de l'aventurier.
     *
     * @return La position Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Retourne le nombre de trésors ramassés par l'aventurier.
     *
     * @return Le nombre de trésors ramassés.
     */
    public int getNbTresorsRamasses() {
        return nbTresorsRamasses;
    }

    /**
     * Met à jour la position de l'aventurier.
     *
     * @param x Nouvelle position X (colonne).
     * @param y Nouvelle position Y (ligne).
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne le nom de l'aventurier.
     *
     * @return Le nom de l'aventurier.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne l'orientation de l'aventurier ('N', 'S', 'E', 'O').
     *
     * @return L'orientation de l'aventurier.
     */
    public char getOrientation() {
        return orientation;
    }

    /**
     * Indique si l'aventurier a encore des mouvements à exécuter.
     *
     * @return True s'il reste des mouvements à exécuter, False sinon.
     */
    public boolean aEncoreDesMouvements() {
        return indexMouvement < mouvements.length();
    }
}
