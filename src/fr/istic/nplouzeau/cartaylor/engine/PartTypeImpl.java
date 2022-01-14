package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.PartType;

import java.util.Objects;

/**
 * Classe d'implémentation de PartType
 */
public class PartTypeImpl implements PartType {

    private String name;
    private Category category;

    /**
     * Constructeur
     * @param name Nom du partType
     * @param category catégorie associé
     */
    public PartTypeImpl(String name, Category category) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(category);
        this.name = name;
        this.category = category;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }
}