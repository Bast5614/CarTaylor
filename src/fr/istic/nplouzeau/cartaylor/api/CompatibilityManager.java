package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

/**
 * Permet d'ajouter et de retirer les compatibilités et incompatibilités d'une reference
 */
public interface CompatibilityManager extends CompatibilityChecker {

    /**
     * Ajoute toutes les incompatibilités d'une reference à une liste cible
     * @param reference l'élément de référence
     * @param target la liste des éléments cible
     * @throws NullPointerException si param null
     */
    void addIncompatibilities(PartType reference, Set<PartType> target);

    /**
     * Supprime toutes les incompatibilités d'une reference à une liste cible
     * @param reference l'élément de référence
     * @param target la liste des éléments cible
     * @throws NullPointerException si param null
     */
    void removeIncompatibility(PartType reference, PartType target);

    /**
     * Ajoute toutes les dépendances d'une reference à une liste cible
     * @param reference l'élément de référence
     * @param target la liste des éléments cible
     * precondition : ne doivent pas être nulle
     */
    void addRequirements(PartType reference, Set<PartType> target);

    /**
     * Supprime toutes les dépendances d'une reference à une liste cible
     * @param reference l'élément de référence
     * @param target la liste des éléments cible
     * @throws NullPointerException si param null
     */
    void removeRequirement(PartType reference, PartType target);

}
