package fr.istic.nplouzeau.cartaylor.tests;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.Part;
import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.engine.CategoryImpl;
import fr.istic.nplouzeau.cartaylor.engine.PartImpl;
import fr.istic.nplouzeau.cartaylor.engine.PartTypeImpl;
import fr.istic.nplouzeau.cartaylor.engine.parts.engines.EG100;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PartTest {
    Category engine;
    PartType eg100;
    Part EG100;

    @BeforeEach
    void setUp() {
        engine = new CategoryImpl("engine");
        eg100 = new PartTypeImpl("EG100", EG100.class, engine);
        EG100 = new EG100(eg100);
    }

    @Test
    void testGetName() {
        String name = "engine";
        Category category = new CategoryImpl(name);
        assertEquals(name, category.getName());
    }

    @Test
    void testGetPropertiesNamesNotNull(){
        assertNotNull(EG100.getPropertyNames());
    }
    @Test
    void testGetEmptyProperties(){
        assertFalse(EG100.getProperty("Error").isPresent());
    }
    @Test
    void testGetNullProperties(){
        assertThrows(NullPointerException.class, () -> EG100.getProperty(null));
    }


}
