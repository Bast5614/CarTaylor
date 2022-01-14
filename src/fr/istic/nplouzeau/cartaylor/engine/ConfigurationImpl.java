package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;

import java.util.*;

/**
 * Classe d'implémentation de Configuration
 */
public class ConfigurationImpl implements Configuration {

    private Map<Category, Part> selectedParts;
    private ConfiguratorImpl configurator;

    /**
     * Constructeur de Configuration
     * @param configurator configurator associé
     */
    public ConfigurationImpl(Configurator configurator) {
        this.configurator = (ConfiguratorImpl) configurator;
        selectedParts = new HashMap<>();
        for (Category cat : configurator.getCategories()) {
            selectedParts.put(cat, null);
        }
    }
    @Override
    public boolean isValid() {
        if (isComplete()) {
            Set<PartType> partTypes = new HashSet<>();
            this.selectedParts.values().forEach(part -> partTypes.add(part.getType()));
            for (Part entry : this.selectedParts.values()) {
                Set<PartType> incompatibilities = configurator.getCompatibilityChecker().getIncompatibilities(entry.getType());
                Set<PartType> requirements = configurator.getCompatibilityChecker().getRequirements(entry.getType());

                for (PartType incompatibility : incompatibilities) {
                    if (partTypes.contains(incompatibility)) {
                        return false;
                    }
                }
                for (PartType requirement : requirements) {
                    if (!partTypes.contains(requirement)) {
                        return false;
                    }
                }
            }
        } else {
            //System.out.println("test");
            return false;
        }
        return true;
    }

    @Override
    public boolean isComplete() {
        if (selectedParts.size() == configurator.getCategories().size()) {
            for (Map.Entry<Category,Part> entry : selectedParts.entrySet()) {
                if (entry.getValue() == null) {
                    return false;
                }
            }
        } else {

            return false;
        }
        return true;
    }

    @Override
    public Set<Part> getSelectedParts() {
        HashSet<Part> ret = new HashSet<>();
        for (Map.Entry<Category,Part> entry : selectedParts.entrySet()) {
            ret.add(entry.getValue());
        }
        return ret;
    }

    @Override
    public void selectPart(PartType chosenPart) {
        selectedParts.put(chosenPart.getCategory(), chosenPart.newInstance());
    }

    @Override
    public Optional<Part> getSelectionForCategory(Category category) {
        if (!this.selectedParts.containsKey(category)){
            return Optional.empty();
        }
        return Optional.of(this.selectedParts.get(category));

    }

    @Override
    public void unselectPartType(Category categoryToClear) {
        selectedParts.remove(categoryToClear);
    }

    @Override
    public void clear() {
        for (Map.Entry<Category,Part> entry : selectedParts.entrySet()) {
            entry.setValue(null);
        }
    }
}
