package fr.istic.nplouzeau.cartaylor.tests;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.CompatibilityChecker;
import fr.istic.nplouzeau.cartaylor.api.Configurator;
import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.engine.CategoryImpl;
import fr.istic.nplouzeau.cartaylor.engine.CompatibilityManagerImpl;
import fr.istic.nplouzeau.cartaylor.engine.ConfiguratorImpl;
import fr.istic.nplouzeau.cartaylor.engine.PartTypeImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class de test de Category
 */
public class CategoryTest {

    private Category engine;

    @BeforeEach
    @DisplayName("Test de Category")
    void setUp() {
        engine = new CategoryImpl("Engine");
    }

    @Test
    @DisplayName("Test de getName")
    public void testGetName() {
        assertNotNull(engine.getName());
        assertEquals(engine.getName(), "Engine");
    }

}
