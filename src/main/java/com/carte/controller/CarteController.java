package com.carte.controller;

import com.carte.model.Aventurier;
import com.carte.model.Carte;
import com.carte.model.Montagne;
import com.carte.model.Tresor;
import org.apache.log4j.Logger;

/**
 * Contrôleur qui gère les déplacements des aventuriers sur la carte
 * et la collecte des trésors.
 */
public class CarteController {
    private static final Logger logger = Logger.getLogger(CarteController.class);
    private final Carte carte;

    /**
     * Initialise le contrôleur avec la carte du jeu.
     *
     * @param carte La carte sur laquelle les aventuriers se déplacent.
     */
    public CarteController(Carte carte) {
        this.carte = carte;
    }

    /**
     * Fait déplacer tous les aventuriers de la carte selon leurs séquences de mouvements.
     */
    public void deplacerAventuriers() {
        boolean mouvementsRestants = true;

        // Continue jusqu'à ce qu'il n'y ait plus aucun mouvement à faire pour tous les aventuriers
        while (mouvementsRestants) {
            mouvementsRestants = false; // On suppose au départ qu'il n'y a plus de mouvement à faire

            for (Aventurier aventurier : carte.getAventuriers()) {
                int ancienneX = aventurier.getX();
                int ancienneY = aventurier.getY();

                // Si l'aventurier a encore des mouvements à faire
                if (aventurier.aEncoreDesMouvements()) {
                    // Exécuter le prochain mouvement
                    aventurier.executerProchainMouvement(carte);
                    logger.info(String.format("Aventurier %s s'est déplacé de (%s, %s) à (%s, %s) avec orientation %s",
                            aventurier.getNom(), ancienneX, ancienneY, aventurier.getX(), aventurier.getY(), aventurier.getOrientation()));

                    // Vérifier les collisions avec les montagnes
                    if (gererCollisionMontagne(aventurier, ancienneX, ancienneY)) {
                        continue;
                    }

                    // Vérifier si un autre aventurier est déjà sur cette position
                    boolean positionOccupee = carte.getAventuriers().stream()
                            .anyMatch(a -> !a.equals(aventurier) && a.getX() == aventurier.getX() && a.getY() == aventurier.getY());

                    // Si la position est occupée, on annule le mouvement
                    if (positionOccupee) {
                        logger.warn(String.format("Mouvement impossible pour aventurier %s : la case (%s, %s) est déjà occupée par un autre aventurier",
                                aventurier.getNom(), aventurier.getX(), aventurier.getY()));
                        aventurier.setPosition(ancienneX, ancienneY);
                    } else {
                        // Si l'aventurier s'est déplacé
                        if( ancienneX != aventurier.getX() || ancienneY != aventurier.getY() ) {
                            // Collecte des trésors si la case n'est pas occupée
                            gererRamassageTresor(aventurier);
                        }
                    }

                    // Il reste encore des mouvements à faire, donc on continue la boucle
                    mouvementsRestants = true;
                }
            }
        }
    }

    /**
     * Gère les collisions des aventuriers avec les montagnes.
     *
     * @param aventurier L'aventurier qui se déplace.
     * @param ancienneX  La position X avant le mouvement.
     * @param ancienneY  La position Y avant le mouvement.
     * @return True si une collision a eu lieu, False sinon.
     */
    private boolean gererCollisionMontagne(Aventurier aventurier, int ancienneX, int ancienneY) {
        for (Montagne montagne : carte.getMontagnes()) {
            if (aventurier.getX() == montagne.getX() && aventurier.getY() == montagne.getY()) {
                aventurier.setPosition(ancienneX, ancienneY); // Retour à la position initiale
                logger.warn(String.format("Mouvement impossible pour aventurier %s : une montagne bloque en (%s, %s)",
                        aventurier.getNom(), montagne.getX(), montagne.getY()));
                return true; // Collision détectée
            }
        }
        return false; // Pas de collision
    }

    /**
     * Gère la collecte des trésors par les aventuriers.
     *
     * @param aventurier L'aventurier qui ramasse les trésors.
     */
    private void gererRamassageTresor(Aventurier aventurier) {
        for (Tresor tresor : carte.getTresors()) {
            if (aventurier.getX() == tresor.getX() && aventurier.getY() == tresor.getY() && tresor.getNbTresors() > 0) {
                tresor.prendreTresor();
                aventurier.incrementerTresorsRamasses();
                logger.info(String.format("Aventurier %s a ramassé un trésor en (%s, %s). Il reste %s trésors sur la case.",
                        aventurier.getNom(), tresor.getX(), tresor.getY(), tresor.getNbTresors()));
            }
        }
    }
}
