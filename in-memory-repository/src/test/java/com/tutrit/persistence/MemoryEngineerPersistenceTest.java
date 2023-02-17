package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Engineer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryEngineerPersistenceTest {
    MemoryEngineerPersistence engineerPersistence;

    @BeforeEach
    void beforeEach() {
        engineerPersistence = new MemoryEngineerPersistence(new UUIDWrapperMock());
        engineerPersistence.deleteAll();
    }


    @Test
    void save() {
        List<Engineer> engineersStart = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(0, engineersStart.size());
        assertEquals(getEngineerExpected0(), engineerPersistence.save(getEngineer0()));
        assertEquals(getEngineerExpected1(), engineerPersistence.save(getEngineer1()));
        List<Engineer> engineersEnd = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(2, engineersEnd.size());
    }

    @Test
    void findById() {
        List<Engineer> engineersStart = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(0, engineersStart.size());
        engineerPersistence.save(getEngineer0());
        engineerPersistence.save(getEngineer1());
        assertEquals(getEngineerExpected0(), engineerPersistence.findById("0"));
        assertEquals(getEngineerExpected1(), engineerPersistence.findById("1"));
    }

    @Test
    void findAll() {
        List<Engineer> engineersStart = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(0, engineersStart.size());
        engineerPersistence.save(getEngineer0());
        List<Engineer> engineersMedium = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(1, engineersMedium.size());
        engineerPersistence.save(getEngineer1());
        List<Engineer> engineersEnd = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(2, engineersEnd.size());
    }

    @Test
    void update() {
        var engineer4 = new Engineer("", "FirstName4", "LastName4", "Admin4", "cat4", "edu4", 4, 4);
        var engineerExpected4 =  new Engineer("1", "FirstName4", "LastName4", "Admin4", "cat4", "edu4", 4, 4);
        engineerPersistence.save(getEngineer0());
        engineerPersistence.save(getEngineer1());
        engineerPersistence.save(getEngineer2());
        assertEquals(getEngineerExpected1(), engineerPersistence.findById("1"));
        engineerPersistence.update("1", engineer4);
        assertEquals(engineerExpected4, engineerPersistence.findById("1"));
    }

    @Test
    void delete() {
        List<Engineer> engineersStart = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(0, engineersStart.size());
        engineerPersistence.save(getEngineer0());
        engineerPersistence.save(getEngineer1());
        engineerPersistence.save(getEngineer2());
        List<Engineer> engineersMedium1 = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(3, engineersMedium1.size());
        assertFalse(engineerPersistence.delete("4"));
        List<Engineer> engineersMedium2 = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(3, engineersMedium2.size());
        assertNotNull(engineerPersistence.findById("1"));
        assertTrue(engineerPersistence.delete("1"));
        List<Engineer> engineersEnd = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(2, engineersEnd.size());
        assertNull(engineerPersistence.findById("1"));
    }

    @Test
    void deleteAll() {
        List<Engineer> engineersStart = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(0, engineersStart.size());
        engineerPersistence.save(getEngineer0());
        engineerPersistence.save(getEngineer1());
        engineerPersistence.save(getEngineer2());
        List<Engineer> engineersMedium = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(3, engineersMedium.size());
        engineerPersistence.deleteAll();
        List<Engineer> engineersEnd = (List<Engineer>) engineerPersistence.findAll();
        assertEquals(0, engineersEnd.size());
    }

    private Engineer getEngineer0() {
        return new Engineer("", "FirstName0", "LastName0", "Admin0", "cat0", "edu0", 0, 0);
    }

    private Engineer getEngineer1() {
        return new Engineer("", "FirstName1", "LastName1", "Admin1", "cat1", "edu1", 1, 1);
    }

    private Engineer getEngineer2() {
        return new Engineer("", "FirstName2", "LastName2", "Admin2", "cat2", "edu2", 2, 2);
    }

    private Engineer getEngineerExpected0() {
        return new Engineer("0", "FirstName0", "LastName0", "Admin0", "cat0", "edu0", 0, 0);
    }

    private Engineer getEngineerExpected1() {
        return new Engineer("1", "FirstName1", "LastName1", "Admin1", "cat1", "edu1", 1, 1);
    }
}
