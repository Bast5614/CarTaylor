package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.PartType;

import java.lang.reflect.Constructor;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartTypeImpl implements PartType {

    private String name;
    private Class<? extends PartImpl> classRef;
    private Category category;

    public PartTypeImpl(String name, Class<? extends PartImpl> classRef, Category category) {
        this.name = name;
        this.classRef = classRef;
        this.category = category;
    }


    @Override
    public String getName() {
        return name;
    }

    public Class<? extends PartImpl> getClassRef() {
        return classRef;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    public PartImpl newInstance() {
        Constructor<? extends PartImpl> constructor;
        try {
            constructor = classRef.getConstructor(PartType.class);
            return constructor.newInstance(this);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "constructor call failed", e);
            System.exit(-1);
        }
        return null;
    }
}

