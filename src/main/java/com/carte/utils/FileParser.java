package com.carte.utils;

import com.carte.model.Aventurier;
import com.carte.model.Carte;
import com.carte.model.Montagne;
import com.carte.model.Tresor;

import java.io.*;

/**
 * Utilitaire pour lire le fichier d'entrée et configurer la carte avec les montagnes, trésors et aventuriers.
 */
public class FileParser {

    /**
     * Lit le fichier d'entrée pour configurer la carte.
     *
     * @param cheminFichier Le chemin vers le fichier d'entrée.
     * @return La carte configurée avec les aventuriers, montagnes et trésors.
     * @throws IOException En cas d'erreur de lecture du fichier.
     */
    public static Carte lireFichier(String cheminFichier) throws IOException {
        Carte carte = null;
        BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));

        String ligne;
        while ((ligne = reader.readLine()) != null) {
            if (ligne.startsWith("#")) {
                continue; // Ignorer les lignes de commentaire
            }
            String[] parts = ligne.split(" - ");
            switch (parts[0]) {
                case "C":
                    int largeur = Integer.parseInt(parts[1]);
                    int hauteur = Integer.parseInt(parts[2]);
                    carte = new Carte(largeur, hauteur);
                    break;
                case "M":
                    int xMontagne = Integer.parseInt(parts[1]);
                    int yMontagne = Integer.parseInt(parts[2]);
                    carte.ajouterMontagne(new Montagne(xMontagne, yMontagne));
                    break;
                case "T":
                    int xTresor = Integer.parseInt(parts[1]);
                    int yTresor = Integer.parseInt(parts[2]);
                    int nbTresors = Integer.parseInt(parts[3]);
                    carte.ajouterTresor(new Tresor(xTresor, yTresor, nbTresors));
                    break;
                case "A":
                    String nom = parts[1];
                    int xAventurier = Integer.parseInt(parts[2]);
                    int yAventurier = Integer.parseInt(parts[3]);
                    char orientation = parts[4].charAt(0);
                    String mouvements = parts[5];
                    carte.ajouterAventurier(new Aventurier(nom, xAventurier, yAventurier, orientation, mouvements));
                    break;
            }
        }
        reader.close();
        return carte;
    }

    /**
     * Écrit le fichier de sortie avec l'état final de la carte.
     *
     * @param cheminFichier Le chemin vers le fichier de sortie.
     * @param carte         La carte avec ses aventuriers, trésors et montagnes.
     * @throws IOException En cas d'erreur d'écriture du fichier.
     */
    public static void ecrireFichierSortie(String cheminFichier, Carte carte) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier));

        // Écrire les dimensions de la carte
        writer.write("C - " + carte.getLargeur() + " - " + carte.getHauteur());
        writer.newLine();

        // Écrire les montagnes
        for (Montagne montagne : carte.getMontagnes()) {
            writer.write("M - " + montagne.getX() + " - " + montagne.getY());
            writer.newLine();
        }

        // Écrire les trésors restants
        for (Tresor tresor : carte.getTresors()) {
            writer.write("T - " + tresor.getX() + " - " + tresor.getY() + " - " + tresor.getNbTresors());
            writer.newLine();
        }

        // Écrire les aventuriers et leur nombre de trésors ramassés
        for (Aventurier aventurier : carte.getAventuriers()) {
            writer.write("A - " + aventurier.getNom() + " - " + aventurier.getX() + " - " + aventurier.getY()
                    + " - " + aventurier.getOrientation() + " - " + aventurier.getNbTresorsRamasses());
            writer.newLine();
        }

        writer.close();
    }
}
