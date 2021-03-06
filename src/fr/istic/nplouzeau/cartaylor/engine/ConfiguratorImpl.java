package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;

import java.util.*;

/**
 * Classe d'implémentation de Configurator
 */
public class ConfiguratorImpl implements Configurator {

    private Map<Category, Set<PartType>> catalog;
    private Configuration configuration;
    private CompatibilityManager compatibilityManager;

    /**
     * Constructeur
     */
    public ConfiguratorImpl() {
        catalog = new HashMap<>();
        configuration = new ConfigurationImpl(this);
        compatibilityManager = new CompatibilityManagerImpl();

        //Implémentation des catégories
        Category catEngine =  new CategoryImpl("Engine");
        Set<PartType> engineParts = new HashSet<>();
        PartType EG100 = new PartTypeImpl("EG100", catEngine);
        PartType EG133 = new PartTypeImpl("EG133", catEngine);
        PartType EG210 = new PartTypeImpl("EG210", catEngine);
        PartType ED110 = new PartTypeImpl("ED110", catEngine);
        PartType ED180 = new PartTypeImpl("ED180", catEngine);
        PartType EH120 = new PartTypeImpl("EH120", catEngine);
        engineParts.add(EG100);
        engineParts.add(EG133);
        engineParts.add(EG210);
        engineParts.add(ED110);
        engineParts.add(ED180);
        engineParts.add(EH120);
        catalog.put(catEngine,engineParts);

        Category catTransmission = new CategoryImpl("Transmission");
        Set<PartType> transmissionParts = new HashSet<>();
        PartType TM5 = new PartTypeImpl("TM5", catTransmission);
        PartType TM6 = new PartTypeImpl("TM6", catTransmission);
        PartType TA5 = new PartTypeImpl("TA5", catTransmission);
        PartType TS6 = new PartTypeImpl("TS6", catTransmission);
        PartType TSF7 = new PartTypeImpl("TSF7", catTransmission);
        PartType TC120 = new PartTypeImpl("TC120", catTransmission);
        transmissionParts.add(TM5);
        transmissionParts.add(TM6);
        transmissionParts.add(TA5);
        transmissionParts.add(TS6);
        transmissionParts.add(TSF7);
        transmissionParts.add(TC120);
        catalog.put(catTransmission,transmissionParts);

        Category catExterior = new CategoryImpl("Exterior");
        Set<PartType> exteriorPart = new HashSet<>();
        PartType XC = new PartTypeImpl("XC", catExterior);
        PartType XM = new PartTypeImpl("XM", catExterior);
        PartType XS = new PartTypeImpl("XS", catExterior);
        exteriorPart.add(XC);
        exteriorPart.add(XM);
        exteriorPart.add(XS);
        catalog.put(catExterior,exteriorPart);

        Category carInterior = new CategoryImpl("Interior");
        Set<PartType> interiorPart = new HashSet<>();
        PartType IN = new PartTypeImpl("IN", carInterior);
        PartType IH = new PartTypeImpl("IH", carInterior);
        PartType IS = new PartTypeImpl("IS", carInterior);
        interiorPart.add(IN);
        interiorPart.add(IH);
        interiorPart.add(IS);
        catalog.put(carInterior,interiorPart);

        //Ajout incompatibilities et requirements
        Set<PartType> requirementEH120 = new HashSet<>();
        requirementEH120.add(TC120);
        compatibilityManager.addRequirements(EH120, requirementEH120);

        Set<PartType> incompatibilitiesTA5 = new HashSet<>();
        incompatibilitiesTA5.add(EG100);
        compatibilityManager.addIncompatibilities(TA5, incompatibilitiesTA5);

        Set<PartType> incompatibilitiesTSF7 = new HashSet<>();
        incompatibilitiesTSF7.add(EG100);
        incompatibilitiesTSF7.add(EG133);
        incompatibilitiesTSF7.add(ED110);
        compatibilityManager.addIncompatibilities(TSF7, incompatibilitiesTSF7);

        Set<PartType> requirementTC120 = new HashSet<>();
        requirementTC120.add(EH120);
        compatibilityManager.addRequirements(TC120, requirementTC120);

        Set<PartType> incompatibilitiesXC = new HashSet<>();
        incompatibilitiesXC.add(EG210);
        compatibilityManager.addIncompatibilities(XC, incompatibilitiesXC);

        Set<PartType> incompatibilitiesXM = new HashSet<>();
        incompatibilitiesXM.add(EG100);
        compatibilityManager.addIncompatibilities(XM, incompatibilitiesXM);

        Set<PartType> incompatibilitiesXS = new HashSet<>();
        incompatibilitiesXS.add(EG100);
        compatibilityManager.addIncompatibilities(XS, incompatibilitiesXS);

        Set<PartType> requirementXS = new HashSet<>();
        requirementXS.add(IS);
        compatibilityManager.addRequirements(XS, requirementXS);

        Set<PartType> incompatibilitiesIS = new HashSet<>();
        incompatibilitiesIS.add(EG100);
        incompatibilitiesIS.add(TM5);
        compatibilityManager.addIncompatibilities(IS, incompatibilitiesIS);

        Set<PartType> requirementIS = new HashSet<>();
        requirementIS.add(XS);
        compatibilityManager.addRequirements(IS, requirementIS);
    }
    @Override
    public Set<Category> getCategories() {
        Set<Category> categoriesSet = new HashSet<>();
        for (Map.Entry<Category,Set<PartType>> entry : catalog.entrySet()) {
            categoriesSet.add(entry.getKey());
        }
        return categoriesSet;
    }

    @Override
    public Set<PartType> getVariants(Category category) {
        return catalog.get(category);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return compatibilityManager;
    }
}
