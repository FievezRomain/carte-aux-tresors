package com.carte.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CarteTest {

    @Test
    public void testAjouterAventurier() {
        Carte carte = new Carte(3, 4);
        Aventurier aventurier = new Aventurier("Indiana", 1, 1, 'S', "AADADA");
        carte.ajouterAventurier(aventurier);

        assertEquals(1, carte.getAventuriers().size());
        assertEquals(aventurier, carte.getAventuriers().get(0));
    }

    @Test
    public void testDeplacementAventurier() {
        Carte carte = new Carte(3, 4);
        Aventurier aventurier = new Aventurier("Indiana", 1, 1, 'S', "A");
        carte.ajouterAventurier(aventurier);

        aventurier.executerProchainMouvement(carte);

        // Vérifier que l'aventurier a avancé d'une case vers le sud
        assertEquals(1, aventurier.getX());
        assertEquals(2, aventurier.getY());
    }

}
