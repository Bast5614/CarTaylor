package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.Configuration;
import fr.istic.nplouzeau.cartaylor.api.Configurator;
import fr.istic.nplouzeau.cartaylor.api.PartType;

import java.util.*;

/**
 * Implementation de la classe de Configuration
 */
public class ConfigurationImpl implements Configuration {

    private Map<Category, PartType> selectedParts;
    private ConfiguratorImpl configurator;

    /**
     * Constructeur
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
            for (Map.Entry<Category,PartType> entry : selectedParts.entrySet()) {
                Set<PartType> incompatibilities = configurator.getCompatibilityChecker().getIncompatibilities(entry.getValue());
                Set<PartType> requirements = configurator.getCompatibilityChecker().getRequirements(entry.getValue());
                for (PartType incompatibility : incompatibilities) {
                    if (selectedParts.containsValue(incompatibility)) {
                        return false;
                    }
                }
                for (PartType requirement : requirements) {
                    if (!selectedParts.containsValue(requirement)) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean isComplete() {
        if (selectedParts.size() == configurator.getCategories().size()) {
            for (Map.Entry<Category,PartType> entry : selectedParts.entrySet()) {
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
    public Set<PartType> getSelectedParts() {
        HashSet<PartType> ret = new HashSet<>();
        for (Map.Entry<Category,PartType> entry : selectedParts.entrySet()) {
            ret.add(entry.getValue());
        }
        return ret;
    }

    @Override
    public void selectPart(PartType chosenPart) {
        selectedParts.put(chosenPart.getCategory(), chosenPart);
    }

    @Override
    public PartType getSelectionForCategory(Category category) {
        return selectedParts.get(category);
    }

    @Override
    public void unselectPartType(Category categoryToClear) {
        selectedParts.remove(categoryToClear);
    }

    @Override
    public void clear() {
        for (Map.Entry<Category,PartType> entry : selectedParts.entrySet()) {
            entry.setValue(null);
        }
    }
}
