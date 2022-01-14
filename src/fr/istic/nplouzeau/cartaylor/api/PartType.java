package fr.istic.nplouzeau.cartaylor.api;

/**
 * Un élément de la voiture
 */
public interface PartType {

    /**
     * Permet d'obtenir le nom de l'élément de la voiture
     * @return le nom de l'élément
     */
    String getName();

    /**
     * Permet d'obtenir la catégorie de l'élément selectionné
     * @return la catégorie de l'élément
     */
    Category getCategory();
}
