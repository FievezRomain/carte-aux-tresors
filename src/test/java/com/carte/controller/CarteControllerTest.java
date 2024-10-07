package com.carte.controller;

import com.carte.model.Aventurier;
import com.carte.model.Carte;
import com.carte.model.Montagne;
import com.carte.model.Tresor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarteControllerTest {

    private Carte carte;
    private CarteController controller;

    @Before
    public void setUp() {
        carte = new Carte(3, 4);
        controller = new CarteController(carte);

        carte.ajouterMontagne(new Montagne(1, 2));
        carte.ajouterTresor(new Tresor(0, 3, 3));
    }

    @Test
    public void testDeplacementAventurierMontagne() {
        Aventurier aventurier = new Aventurier("Lara", 1, 1, 'S', "A");
        carte.ajouterAventurier(aventurier);

        // Appel à la méthode publique qui gère les déplacements et la collecte des trésors
        controller.deplacerAventuriers();

        // L'aventurier devrait avoir avancé vers le sud, mais être bloqué par la montagne
        assertEquals(1, aventurier.getX());
        assertEquals(1, aventurier.getY());  // Bloqué par la montagne
    }

    @Test
    public void testDeplacementAventurier() {
        Aventurier aventurier = new Aventurier("Lara", 1, 1, 'S', "GADAA");
        carte.ajouterAventurier(aventurier);

        // Appel à la méthode publique qui gère les déplacements et la collecte des trésors
        controller.deplacerAventuriers();

        // L'aventurier devrait avoir avancé vers le sud, mais être bloqué par la montagne
        assertEquals(2, aventurier.getX());
        assertEquals(3, aventurier.getY());  // Bloqué par la montagne
    }

    @Test
    public void testDeplacementConflitMultipleAventuriers() {
        Aventurier aventurier = new Aventurier("Lara", 1, 1, 'S', "GADAA");
        Aventurier aventurier2 = new Aventurier("Jones", 2, 1, 'S', "AA");
        carte.ajouterAventurier(aventurier);
        carte.ajouterAventurier(aventurier2);

        // Appel à la méthode publique qui gère les déplacements et la collecte des trésors
        controller.deplacerAventuriers();

        // L'aventurier devrait avoir avancé vers le sud, mais être bloqué par la montagne
        assertEquals(2, aventurier2.getX());
        assertEquals(3, aventurier2.getY());
        assertEquals(2, aventurier.getX());
        assertEquals(2, aventurier.getY());  // Bloqué par l'aventurier2
    }

    @Test
    public void testRamassageTresorArrivantSurCase() {
        Aventurier aventurier = new Aventurier("Lara", 1, 1, 'S', "DAGAA");
        carte.ajouterAventurier(aventurier);

        // Appel à la méthode publique qui gère les déplacements et la collecte des trésors
        controller.deplacerAventuriers();

        Tresor tresor = carte.getTresors().get(0);
        assertEquals(2, tresor.getNbTresors());  // Le trésor devrait avoir été ramassé
        assertEquals(1, aventurier.getNbTresorsRamasses());  // L'aventurier doit avoir ramassé 1 trésor
    }

    @Test
    public void testPasDeRamassageTresorSansBouger() {
        Aventurier aventurier = new Aventurier("Lara", 1, 1, 'S', "DAGAADG");
        carte.ajouterAventurier(aventurier);

        // Appel à la méthode publique qui gère les déplacements et la collecte des trésors
        controller.deplacerAventuriers();

        Tresor tresor = carte.getTresors().get(0);
        assertEquals(2, tresor.getNbTresors());  // Le trésor devrait avoir été ramassé
        assertEquals(1, aventurier.getNbTresorsRamasses());  // L'aventurier doit avoir ramassé 1 trésor
    }
}
