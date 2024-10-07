package com.carte.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AventurierTest {

    private Aventurier aventurier;

    @Test
    public void testAvancer() {
        aventurier = new Aventurier("Indiana", 1, 1, 'S', "AAD");
        aventurier.executerProchainMouvement(null);  // Avancer
        assertEquals(2, aventurier.getY());
        aventurier.executerProchainMouvement(null);  // Avancer
        assertEquals(3, aventurier.getY());
    }

    @Test
    public void testTournerDroite() {
        aventurier = new Aventurier("Indiana", 1, 1, 'S', "AGA");
        aventurier.executerProchainMouvement(null);  // Avancer
        aventurier.executerProchainMouvement(null);  // Tourner sur la droite
        aventurier.executerProchainMouvement(null);  // Avancer
        assertEquals(2, aventurier.getX());
        assertEquals(2, aventurier.getY());
    }
}
