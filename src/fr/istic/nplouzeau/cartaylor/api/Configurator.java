package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

/**
 * outil pour créer et gérer la configuration d'une voiture
 */
public interface Configurator {

    /**
     * récupère les différentes catégories disponibles
     *
     * @return les différentes catégories disponibles
     */
    Set<Category> getCategories();

    /**
     * récupère les différentes parties possibles pour une catégorie donnée
     *
     * @param category la catégorie où l'on souhaite voir les parties
     * @throws NullPointerException si param null
     *
     * @return les différentes parties d'une catégorie donnée
     */
    Set<PartType> getVariants(Category category);

    /**
     * récupère la configuration actuelle
     *
     * @return la configuration actuelle
     */
    Configuration getConfiguration();

    /**
     * récupère le compatibilitychecker
     *
     * @return le compatibilitychecker
     */
    CompatibilityChecker getCompatibilityChecker();

}
