package fr.istic.nplouzeau.cartaylor.api;


import java.util.Set;

/**
 * Une configuration de voiture
 */
public interface Configuration {

    /**
     * vérifie qu'il n'y a aucune incompatiblité entre les pièces choisies
     *
     * @return true si la configuration est valide, false sinon
     */
    boolean isValid();

    /**
     * vérifie si l'utilisateur a fini de compléter sa configuration
     *
     * @return true si la configuration est complète, false sinon
     */
    boolean isComplete();

    /**
     * retourne toutes les parties sélectionnées par l'utilisateur dans sa 
     * configuration
     *
     * @return Les différentes parties sélectionnées
     */
    Set<PartType> getSelectedParts();

    /**
     * ajoute une partie à la configuration
     *
     * @param chosenPart la partie à ajouter
     * @throws NullPointerException si param null
     */
    void selectPart(PartType chosenPart);

    /**
     * récupère la partie choisie dans une catégorie donnée
     *
     * @param category la catégorie voulue
     * @throws NullPointerException si param null
     *
     * @return la partie sélectionnée pour la catégorie
     */
    PartType getSelectionForCategory(Category category);

    /**
     * enlève une partie de la voiture selon la catégorie
     *
     * @param categoryToClear la catégorie de l'élèment à enlever
     * @throws NullPointerException si param null
     */
    void unselectPartType(Category categoryToClear);

    /**
     * vide entièrement la configuration
     */
    void clear();
}
