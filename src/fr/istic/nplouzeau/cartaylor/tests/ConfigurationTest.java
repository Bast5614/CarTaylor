package fr.istic.nplouzeau.cartaylor.tests;

import fr.istic.nplouzeau.cartaylor.api.*;
import fr.istic.nplouzeau.cartaylor.engine.CategoryImpl;
import fr.istic.nplouzeau.cartaylor.engine.ConfigurationImpl;
import fr.istic.nplouzeau.cartaylor.engine.ConfiguratorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * Classe de test de configurator
 */
public class ConfigurationTest {

    //Configuration valide et complet
    private Configurator configurator;
    //Configuration non valide et non complet
    private Configurator configurator2;
    @BeforeEach
    @DisplayName("Test de Category")
    void setUp() {
        configurator = new ConfiguratorImpl();//créer une configuration valide
        Set<Category> categories = configurator.getCategories();
        //On parcourt les différentes catégories
        for (Category category : categories) {
            //On sélectionne celle qui nous intéresse
            if (category.getName().equals("Engine")) {
                Set<PartType> parts = configurator.getVariants(category);
                //On parcourt les partType de la catégorie
                for (PartType part : parts) {
                    //On choisit celle qui nous intéresse
                    if (part.getName().equals("EG100")) {
                        configurator.getConfiguration().selectPart(part);
                    }
                }
            }

            //On parcourt les différentes catégories
            if (category.getName().equals("Transmission")) {
                Set<PartType> parts = configurator.getVariants(category);
                //On parcourt les partType de la catégorie
                for (PartType part : parts) {
                    //On choisit celle qui nous intéresse
                    if (part.getName().equals("TM5")) {
                        configurator.getConfiguration().selectPart(part);
                    }
                }
            }

            //On parcourt les différentes catégories
            if (category.getName().equals("Exterior")) {
                Set<PartType> parts = configurator.getVariants(category);
                //On parcourt les partType de la catégorie
                for (PartType part : parts) {
                    //On choisit celle qui nous intéresse
                    if (part.getName().equals("XC")) {
                        configurator.getConfiguration().selectPart(part);
                    }
                }
            }

            //On parcourt les différentes catégories
            if (category.getName().equals("Interior")) {
                Set<PartType> parts = configurator.getVariants(category);
                //On parcourt les partType de la catégorie
                for (PartType part : parts) {
                    //On choisit celle qui nous intéresse
                    if (part.getName().equals("IN")) {
                        configurator.getConfiguration().selectPart(part);
                    }
                }
            }
        }

        configurator2 = new ConfiguratorImpl();
        Set<Category> categories2 = configurator2.getCategories();
        for (Category category : categories2) {
            //On sélectionne celle qui nous intéresse
            if (category.getName().equals("Engine")) {
                Set<PartType> parts = configurator2.getVariants(category);
                //On parcourt les partType de la catégorie
                for (PartType part : parts) {
                    //On choisit celle qui nous intéresse
                    if (part.getName().equals("EG100")) {
                        configurator2.getConfiguration().selectPart(part);
                    }
                }
            }
        }
    }

    @Test
    void testValid() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(configurator.getConfiguration().isValid()),
                () -> Assertions.assertFalse(configurator2.getConfiguration().isValid()));
    }

    @Test
    void testComplete() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(configurator.getConfiguration().isComplete()),
                () -> Assertions.assertFalse(configurator2.getConfiguration().isComplete()));
    }
}
