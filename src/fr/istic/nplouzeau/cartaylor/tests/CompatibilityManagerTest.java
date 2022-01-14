package fr.istic.nplouzeau.cartaylor.tests;

import fr.istic.nplouzeau.cartaylor.api.*;
import fr.istic.nplouzeau.cartaylor.engine.CategoryImpl;
import fr.istic.nplouzeau.cartaylor.engine.CompatibilityManagerImpl;
import fr.istic.nplouzeau.cartaylor.engine.ConfiguratorImpl;
import fr.istic.nplouzeau.cartaylor.engine.PartTypeImpl;
import fr.istic.nplouzeau.cartaylor.engine.parts.engines.EG100;
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
                    if (part.getName().equals("XM")) {
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
    public void TestIncompatibility() {
        CompatibilityManager cc = new CompatibilityManagerImpl();
        //part2 est incompatible avec part1 et part3
        Category cat1 = new CategoryImpl("Cat1");
        PartType part1 = new PartTypeImpl("part1", EG100.class, cat1);
        PartType part2 = new PartTypeImpl("part2",EG100.class, cat1);
        PartType part3 = new PartTypeImpl("part3", EG100.class,cat1);
        Set<PartType> set1 = new HashSet<>();
        set1.add(part1);
        set1.add(part3);
        cc.addIncompatibilities(part2,set1);

        //part1 est incompatible avec par4 d'une autre catégorie
        Category cat2 = new CategoryImpl("Cat2");
        PartType part4 = new PartTypeImpl("part4", EG100.class,cat2);
        Set<PartType> set2 = new HashSet<>();
        set2.add(part4);
        cc.addIncompatibilities(part1,set2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(cc.getIncompatibilities(part2), set1),
                () -> Assertions.assertEquals(cc.getIncompatibilities(part1), set2));
    }

    @Test
    public void TestRequirements() {
        CompatibilityManager cc = new CompatibilityManagerImpl();
        //part2 est incompatible avec part1 et part3
        Category cat1 = new CategoryImpl("Cat1");
        PartType part1 = new PartTypeImpl("part1", EG100.class,cat1);
        PartType part2 = new PartTypeImpl("part2", EG100.class,cat1);
        PartType part3 = new PartTypeImpl("part3", EG100.class,cat1);
        Set<PartType> set1 = new HashSet<>();
        set1.add(part1);
        set1.add(part3);
        cc.addRequirements(part2,set1);

        //part1 est incompatible avec par4 d'une autre catégorie
        Category cat2 = new CategoryImpl("Cat2");
        PartType part4 = new PartTypeImpl("part4", EG100.class,cat2);
        Set<PartType> set2 = new HashSet<>();
        set2.add(part4);
        cc.addRequirements(part1,set2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(cc.getRequirements(part2), set1),
                () -> Assertions.assertEquals(cc.getRequirements(part1), set2));
    }
}
