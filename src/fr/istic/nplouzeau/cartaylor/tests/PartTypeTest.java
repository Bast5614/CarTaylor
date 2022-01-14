package fr.istic.nplouzeau.cartaylor.tests;
import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.engine.CategoryImpl;
import fr.istic.nplouzeau.cartaylor.engine.PartTypeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test de PartType
 */
public class PartTypeTest {

    private Category engine;
    private PartType part1;

    @BeforeEach
    @DisplayName("Test de PartType")
    void setUp() {
        engine = new CategoryImpl("Engine");
        part1 = new PartTypeImpl("EG100", engine);
    }

    @Test
    @DisplayName("Test de getName")
    void testGetName() {
        assertNotNull(part1.getName());
        assertEquals(part1.getName(), "EG100");
    }

    @Test
    @DisplayName("Test de getCategory")
    void testGetCategory() {
        assertNotNull(part1.getCategory());
        assertEquals(part1.getCategory(), engine);
    }

    @Test
    @DisplayName("Test de getCategory")
    void itestGetCategory() {
        assertNotNull(part1.getCategory());
        assertEquals(part1.getCategory(), engine);
    }
}
