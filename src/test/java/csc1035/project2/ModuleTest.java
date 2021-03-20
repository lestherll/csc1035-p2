package csc1035.project2;

import csc1035.project2.model.Module;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModuleTest {

    private Module module = new Module("CSC1035", "Programming Portfolio 5", 30, 10);

    @Test
    void getModuleId() {
        assertEquals("CSC1035", module.getModuleId());
    }

    @Test
    void getName() {
        assertEquals("Programming Portfolio 5", module.getName());
    }

    @Test
    void getCredits() {
        assertEquals(30, module.getCredits());
    }

    @Test
    void getWeeks() {
        assertEquals(10, module.getWeeks());
    }

}