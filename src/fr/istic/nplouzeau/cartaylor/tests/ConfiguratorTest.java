package fr.istic.nplouzeau.cartaylor.tests;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.Configurator;
import fr.istic.nplouzeau.cartaylor.engine.CategoryImpl;
import fr.istic.nplouzeau.cartaylor.engine.ConfiguratorImpl;
import fr.istic.nplouzeau.cartaylor.engine.PartTypeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test de configurator
 */
public class ConfiguratorTest {

    private Configurator configurator;

    @BeforeEach
    @DisplayName("Test de Configurator")
    void setUp() {
       configurator = new ConfiguratorImpl();
    }

    @Test
    void testGetCategories() {
        Set<Category> categories = configurator.getCategories();
        assertEquals(categories.size(), 4);
    }
}
