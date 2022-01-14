package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.CompatibilityManager;
import fr.istic.nplouzeau.cartaylor.api.PartType;

import java.util.*;

/**
 * Implementation de compatibilityManager
 */
public class CompatibilityManagerImpl implements CompatibilityManager {

    private Map<PartType, Set<PartType>> incompatibilities;
    private Map<PartType, Set<PartType>> requirements;

    /**
     * Constructor
     */
    public CompatibilityManagerImpl() {
        incompatibilities = new HashMap<>();
        requirements = new HashMap<>();
    }

    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        return incompatibilities.getOrDefault(reference, new HashSet<>());
    }

    @Override
    public Set<PartType> getRequirements(PartType reference) {
        return requirements.getOrDefault(reference, new HashSet<>());
    }

    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) {
        if (incompatibilities.get(reference) != null) {
            for (PartType partType : target) {
                incompatibilities.get(reference).add(partType);
            }
        } else {
            incompatibilities.put(reference, target);
        }
    }

    @Override
    public void removeIncompatibility(PartType reference, PartType target) {
        if (incompatibilities.get(reference) != null) {
            incompatibilities.remove(reference, target);
        }
    }

    @Override
    public void addRequirements(PartType reference, Set<PartType> target) {
        if (requirements.get(reference) != null) {
            for (PartType partType : target) {
                requirements.get(reference).add(partType);
            }
        } else {
            requirements.put(reference, target);
        }
    }

    @Override
    public void removeRequirement(PartType reference, PartType target) {
        if (requirements.get(reference) != null) {
            requirements.remove(reference, target);
        }
    }
}
