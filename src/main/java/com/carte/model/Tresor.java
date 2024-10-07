package com.carte.model;

public class Tresor {
    private int x;
    private int y;
    private int nbTresors;

    public Tresor(int x, int y, int nbTresors) {
        this.x = x;
        this.y = y;
        this.nbTresors = nbTresors;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNbTresors() {
        return nbTresors;
    }

    public void prendreTresor() {
        if (nbTresors > 0) {
            nbTresors--;
        }
    }
}
