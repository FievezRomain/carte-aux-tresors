package com.carte.app;

import com.carte.controller.CarteController;
import com.carte.model.Carte;
import com.carte.utils.FileParser;

import java.io.IOException;
import org.apache.log4j.Logger;

public class CarteAuxTresorsApp {
    // Initialiser un logger pour cette classe
    private static final Logger logger = Logger.getLogger(CarteAuxTresorsApp.class);

    public static void main(String[] args) {
        if (args.length < 2) {
            logger.error("Usage: java CarteAuxTresorsApp <fichier_entree> <fichier_sortie>");
            return;
        }

        String fichierEntree = args[0];
        String fichierSortie = args[1];

        try {
            // Lire le fichier d'entrée et initialiser la carte
            Carte carte = FileParser.lireFichier(fichierEntree);
            logger.info("Fichier d'entrée " + fichierEntree + " chargé avec succès.");

            // Créer le contrôleur et exécuter les déplacements
            CarteController controller = new CarteController(carte);
            controller.deplacerAventuriers();
            logger.info("Déplacements des aventuriers terminés.");

            // Écrire le fichier de sortie
            FileParser.ecrireFichierSortie(fichierSortie, carte);

            logger.info("Simulation terminée. Résultat écrit dans " + fichierSortie);
        } catch (IOException e) {
            logger.error("Erreur lors de la lecture ou de l'écriture du fichier : "+ e.getMessage());
        }
    }
}
