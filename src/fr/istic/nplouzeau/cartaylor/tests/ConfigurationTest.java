package fr.istic.nplouzeau.cartaylor.tests;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.Configuration;
import fr.istic.nplouzeau.cartaylor.api.Configurator;
import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.engine.CategoryImpl;
import fr.istic.nplouzeau.cartaylor.engine.ConfigurationImpl;
import fr.istic.nplouzeau.cartaylor.engine.ConfiguratorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * Classe de test de Configuration
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
        /* check configuration complete et non complete */
        Assertions.assertAll(
                () -> Assertions.assertTrue(configurator.getConfiguration().isComplete()),
                () -> Assertions.assertFalse(configurator2.getConfiguration().isComplete()));
    }

    @Test
    void testCompleteNotValidIncomp() {
        /* check configuration complete et non valide */
        for (Category category : configurator.getCategories()) {
            if (category.getName().equals("Exterior")) {
                Set<PartType> parts = configurator.getVariants(category);
                for (PartType part : parts) {
                    if (part.getName().equals("XM")) {
                        configurator.getConfiguration().selectPart(part);
                    }
                }
            }
        }

        Assertions.assertAll(
                () -> Assertions.assertTrue(configurator.getConfiguration().isComplete()),
                () -> Assertions.assertFalse(configurator.getConfiguration().isValid()));
    }

    @Test
    void testCompleteNotValidReq() {
        /* check configuration complete et non valide */
        for (Category category : configurator.getCategories()) {
            if (category.getName().equals("Transmission")) {
                Set<PartType> parts = configurator.getVariants(category);
                for (PartType part : parts) {
                    if (part.getName().equals("TC120")) {
                        configurator.getConfiguration().selectPart(part);
                    }
                }
            }
        }

        Assertions.assertAll(
                () -> Assertions.assertTrue(configurator.getConfiguration().isComplete()),
                () -> Assertions.assertFalse(configurator.getConfiguration().isValid()));
    }
    
    @Test
    public void TestClear() {
        configurator.getConfiguration().clear();
        /* aucune part ne doit être sélectionnée après un clear */
        for (Category c: configurator.getCategories())
            Assertions.assertNull(configurator.getConfiguration().getSelectionForCategory(c));
        Assertions.assertFalse(configurator.getConfiguration().isComplete());
        Assertions.assertFalse(configurator.getConfiguration().isValid());
    }
}
