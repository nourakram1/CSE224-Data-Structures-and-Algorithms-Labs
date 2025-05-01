package table;

import factory.Factory;
import org.junit.jupiter.api.Test;
import util.ListUtils;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractTableTest<S, T extends Table<S>> {

    protected static final int INITIAL_CAPACITY = 21;
    private final Factory<S> factory;
    
    AbstractTableTest(final Factory<S> factory) {
        this.factory = factory;
    }
    
    protected T createTable() {
        return createTable(INITIAL_CAPACITY);
    }

    protected abstract T createTable(int capacity);

    @Test
    void testInsert() {
        T table = createTable();
        S stub = factory.createInstance();
        assertTrue(table.insert(stub), "Insert should return true.");
    }

    @Test
    void testDuplicateInsert() {
        T table = createTable();
        S stub = factory.createInstance();
        table.insert(stub);
        assertFalse(table.insert(stub), "Duplicate insert should return false.");
    }

    @Test
    void testRemove() {
        T table = createTable();
        S stub = factory.createInstance();
        table.insert(stub);
        assertTrue(table.remove(stub), "Remove should return true.");
    }

    @Test
    void testDuplicateRemove() {
        T table = createTable();
        S stub = factory.createInstance();
        table.insert(stub);
        table.remove(stub);
        assertFalse(table.remove(stub), "Duplicate remove should return false.");
    }

    @Test
    void testRemoveNonExistent() {
        T table = createTable();
        S stub = factory.createInstance();
        assertFalse(table.remove(stub), "Remove should return false.");
    }

    @Test
    void testContains() {
        T table = createTable();
        S stub = factory.createInstance();
        table.insert(stub);
        assertTrue(table.contains(stub), "Contains should return true.");
    }

    @Test
    void testContainsNonExistent() {
        T table = createTable();
        S stub = factory.createInstance();
        assertFalse(table.contains(stub), "Contains should return false.");
    }

    @Test
    void testInsertAllUnique() {
        T table = createTable();
        List<S> stubs = factory.createUniqueInstances();
        List<S> inserted = table.insertAll(stubs);
        assertTrue(ListUtils.haveSameElements(stubs, inserted),
                "Insert all unique elements should return the same elements.");
    }

    @Test
    void testInsertAllWithDuplicates() {
        T table = createTable();
        List<S> uniqueStubs = factory.createUniqueInstances();
        List<S> duplicateStubs = factory.createDuplicateInstances(uniqueStubs);
        List<S> inserted = table.insertAll(duplicateStubs);
        assertTrue(ListUtils.haveSameElements(inserted, uniqueStubs),
                "Insert all with duplicates elements should return only the unique elements.");
    }

    @Test
    void testInsertAllWithEmptyList() {
        T table = createTable();
        List<S> inserted = table.insertAll(Collections.emptyList());
        assertTrue(inserted.isEmpty(),
                "Insert all elements of an empty list should return an empty list.");
    }

    @Test
    void testRemoveAllUnique() {
        T table = createTable();
        List<S> stubs = factory.createUniqueInstances();
        table.insertAll(stubs);
        List<S> removed = table.removeAll(stubs);
        assertTrue(ListUtils.haveSameElements(stubs, removed),
                "Remove all unique elements should return the same elements.");
    }

    @Test
    void removeAllWithDuplicates() {
        T table = createTable();
        List<S> uniqueStubs = factory.createUniqueInstances();
        List<S> duplicateStubs = factory.createDuplicateInstances(uniqueStubs);
        table.insertAll(duplicateStubs);
        List<S> removed = table.removeAll(duplicateStubs);
        assertTrue(ListUtils.haveSameElements(removed, uniqueStubs),
                "Remove all with duplicates elements should return only the inserted elements.");
    }

    @Test
    void testRemoveAllWithEmptyList() {
        T table = createTable();
        List<S> removed = table.insertAll(Collections.emptyList());
        assertTrue(removed.isEmpty(),
                "Remove all elements of an empty list should return an empty list.");
    }

    @Test
    void testGetElements() {
        T table = createTable();
        List<S> uniqueStubs = factory.createUniqueInstances();
        table.insertAll(uniqueStubs);
        List<S> elements = table.getElements();
        assertTrue(ListUtils.haveSameElements(elements, uniqueStubs));
    }

    @Test
    void testGrow() {
        T table = createTable(2);
        List<S> uniqueStubs = factory.createUniqueInstances();
        assertEquals(uniqueStubs.size(), table.insertAll(uniqueStubs).size(),
                "Hashtable should grow to accommodate all insertions.");
    }
}