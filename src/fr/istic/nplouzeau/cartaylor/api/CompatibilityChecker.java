package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

/**
 * Cette interface permet de vérifier la compatibilité ou l'incompatibilité des parties selectionnées
 */
public interface CompatibilityChecker {

    /**
     * Permet d'obtenir la liste des élements incompatibles avec l'élément sélectionné
     * @param reference l'élément selectionné
     * @throws NullPointerException Ne doit pas être nulle
     * @return la liste des éléments incompatible
     */
    Set<PartType> getIncompatibilities(PartType reference);

    /**
     * Permet d'obtenir les élements nécessaires pour l'élément sélectionné
     * @param reference l'élément selectionné
     * @throws NullPointerException Ne doit pas être nulle
     * @return la liste des éléments nécessaires
     */
    Set<PartType> getRequirements(PartType reference);

}
