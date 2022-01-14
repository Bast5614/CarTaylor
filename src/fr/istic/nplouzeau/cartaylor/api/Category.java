package fr.istic.nplouzeau.cartaylor.api;

import fr.istic.nplouzeau.cartaylor.engine.PartTypeImpl;

import java.util.Set;

/**
 * @author plouzeau
 * <p>
 * A public type to organize part types in categories
 */
public interface Category {

    /**
     * Permet d'obtenir le nom de la categorie
     * @return String du nom
     */
    String getName();

}
