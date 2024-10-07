package com.carte.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente la carte du jeu avec les aventuriers, montagnes et trésors.
 */
public class Carte {
    private final int largeur;
    private final int hauteur;
    private final List<Aventurier> aventuriers;
    private final List<Montagne> montagnes;
    private final List<Tresor> tresors;

    /**
     * Initialise une carte avec une largeur et une hauteur.
     *
     * @param largeur La largeur de la carte.
     * @param hauteur La hauteur de la carte.
     */
    public Carte(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.aventuriers = new ArrayList<>();
        this.montagnes = new ArrayList<>();
        this.tresors = new ArrayList<>();
    }

    /**
     * Ajoute un aventurier sur la carte.
     *
     * @param aventurier L'aventurier à ajouter.
     */
    public void ajouterAventurier(Aventurier aventurier) {
        this.aventuriers.add(aventurier);
    }

    /**
     * Ajoute une montagne sur la carte.
     *
     * @param montagne La montagne à ajouter.
     */
    public void ajouterMontagne(Montagne montagne) {
        this.montagnes.add(montagne);
    }

    /**
     * Ajoute un trésor sur la carte.
     *
     * @param tresor Le trésor à ajouter.
     */
    public void ajouterTresor(Tresor tresor) {
        this.tresors.add(tresor);
    }

    /**
     * Retourne la liste des aventuriers sur la carte.
     *
     * @return La liste des aventuriers.
     */
    public List<Aventurier> getAventuriers() {
        return aventuriers;
    }

    /**
     * Retourne la liste des montagnes sur la carte.
     *
     * @return La liste des montagnes.
     */
    public List<Montagne> getMontagnes() {
        return montagnes;
    }

    /**
     * Retourne la liste des trésors sur la carte.
     *
     * @return La liste des trésors.
     */
    public List<Tresor> getTresors() {
        return tresors;
    }

    /**
     * Retourne la largeur de la carte.
     *
     * @return La largeur de la carte.
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Retourne la hauteur de la carte.
     *
     * @return La hauteur de la carte.
     */
    public int getHauteur() {
        return hauteur;
    }
}
