package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.PartType;

import java.util.*;

/**
 * Classe d'implémentation de la Category
 */
public class CategoryImpl implements Category {

    private String name;

    /**
     * Constructeur
     * @param name nom de la catégorie
     */
    public CategoryImpl(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }

}
