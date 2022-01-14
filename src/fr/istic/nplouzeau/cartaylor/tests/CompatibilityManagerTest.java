package fr.istic.nplouzeau.cartaylor.tests;

import fr.istic.nplouzeau.cartaylor.api.*;
import fr.istic.nplouzeau.cartaylor.engine.CategoryImpl;
import fr.istic.nplouzeau.cartaylor.engine.CompatibilityManagerImpl;
import fr.istic.nplouzeau.cartaylor.engine.ConfiguratorImpl;
import fr.istic.nplouzeau.cartaylor.engine.ConfigurationImpl;
import fr.istic.nplouzeau.cartaylor.engine.PartTypeImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de test de CompatibilityManager
 */
public class CompatibilityManagerTest {

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
    @DisplayName("Test Incompatibility")
    public void TestIncompatibility() {
        CompatibilityManager cc = new CompatibilityManagerImpl();
        //part2 est incompatible avec part1 et part3
        Category cat1 = new CategoryImpl("Cat1");
        PartType part1 = new PartTypeImpl("part1", cat1);
        PartType part2 = new PartTypeImpl("part2", cat1);
        PartType part3 = new PartTypeImpl("part3", cat1);
        Set<PartType> set1 = new HashSet<>();
        set1.add(part1);
        set1.add(part3);
        cc.addIncompatibilities(part2,set1);

        //part1 est incompatible avec par4 d'une autre catégorie
        Category cat2 = new CategoryImpl("Cat2");
        PartType part4 = new PartTypeImpl("part4", cat2);
        Set<PartType> set2 = new HashSet<>();
        set2.add(part4);
        cc.addIncompatibilities(part1,set2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(cc.getIncompatibilities(part2), set1),
                () -> Assertions.assertEquals(cc.getIncompatibilities(part1), set2));
    }

    @Test
    @DisplayName("Test de requirements")
    public void TestRequirements() {
        CompatibilityManager cc = new CompatibilityManagerImpl();
        //part2 est incompatible avec part1 et part3
        Category cat1 = new CategoryImpl("Cat1");
        PartType part1 = new PartTypeImpl("part1", cat1);
        PartType part2 = new PartTypeImpl("part2", cat1);
        PartType part3 = new PartTypeImpl("part3", cat1);
        Set<PartType> set1 = new HashSet<>();
        set1.add(part1);
        set1.add(part3);
        cc.addRequirements(part2,set1);

        //part1 est incompatible avec par4 d'une autre catégorie
        Category cat2 = new CategoryImpl("Cat2");
        PartType part4 = new PartTypeImpl("part4", cat2);
        Set<PartType> set2 = new HashSet<>();
        set2.add(part4);
        cc.addRequirements(part1,set2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(cc.getRequirements(part2), set1),
                () -> Assertions.assertEquals(cc.getRequirements(part1), set2));
    }

    @Test
    public void TestValideConfiguratior() {
        /* vérifie si un configurator avec une configuration valide est bon */
        Configuration validConf = this.configurator.getConfiguration();
        Assertions.assertAll(
                () -> Assertions.assertTrue(validConf.isComplete()),
                () -> Assertions.assertTrue(validConf.isValid()));
    }

    @Test
    public void TestValideConfiguration() {
        /* utiliser un configurator avec une mauvaise configuration
         * et lui ajouter des parties pour le rendre valide*/
        Configuration validConf = new ConfigurationImpl(configurator2);

        Set<Category> categories = configurator2.getCategories();
        for (Category category : categories) {
            if (category.getName().equals("Engine")) {
                Set<PartType> parts = configurator2.getVariants(category);
                for (PartType part : parts) {
                    if (part.getName().equals("EG100")) {
                        validConf.selectPart(part);
                    }
                }
            }

            if (category.getName().equals("Transmission")) {
                Set<PartType> parts = configurator2.getVariants(category);
                for (PartType part : parts) {
                    if (part.getName().equals("TM5")) {
                        validConf.selectPart(part);
                    }
                }
            }

            if (category.getName().equals("Exterior")) {
                Set<PartType> parts = configurator2.getVariants(category);
                for (PartType part : parts) {
                    if (part.getName().equals("XC")) {
                        validConf.selectPart(part);
                    }
                }
            }

            if (category.getName().equals("Interior")) {
                Set<PartType> parts = configurator2.getVariants(category);
                for (PartType part : parts) {
                    if (part.getName().equals("IN")) {
                        validConf.selectPart(part);
                    }
                }
            }
        }

        Assertions.assertAll(
                () -> Assertions.assertTrue(validConf.isComplete()),
                () -> Assertions.assertTrue(validConf.isValid()));
    }


    @Test
    public void TestNoComplete() {
        Configuration badConf = this.configurator2.getConfiguration();
        Assertions.assertAll(
                () -> Assertions.assertFalse(badConf.isComplete()),
                () -> Assertions.assertFalse(badConf.isValid()));
    }

}
