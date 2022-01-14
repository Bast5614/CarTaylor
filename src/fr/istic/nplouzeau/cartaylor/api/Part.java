package fr.istic.nplouzeau.cartaylor.api;

public interface Part extends PropertyManager {

    /**
     * Getter du nom de la part
     * @return string
     */
    default String getName() {
        return this.getClass().getTypeName();
    };

    /**
     * Getter de la category de la part
     * @return Category
     */
    Category getCategory();

    /**
     * Gette du PartType associé à la part
     * @return PartType
     */
    PartType getType();
}