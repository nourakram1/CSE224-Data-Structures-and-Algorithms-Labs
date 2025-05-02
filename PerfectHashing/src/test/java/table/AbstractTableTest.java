package table;

import factory.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import util.ListUtils;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractTableTest<S, T extends Table<S>> {
    
    protected static final int REPEAT_COUNT = 50;
    
    private final Factory<S> factory;
    protected T table;
    protected static final int INITIAL_TABLE_CAPACITY = 21;

    AbstractTableTest(final Factory<S> factory) {
        this.factory = factory;
    }
    
    protected T createTable() {
        return createTable(INITIAL_TABLE_CAPACITY);
    }

    protected abstract T createTable(int capacity);

    @BeforeEach
    void setUp() {
        table = createTable();
    }

    @RepeatedTest(REPEAT_COUNT)
    void testInsert() {
        S stub = factory.createInstance();
        assertTrue(table.insert(stub), "Insert should return true.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testDuplicateInsert() {
        S stub = factory.createInstance();
        table.insert(stub);
        assertFalse(table.insert(stub), "Duplicate insert should return false.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testRemove() {
        S stub = factory.createInstance();
        table.insert(stub);
        assertTrue(table.remove(stub), "Remove should return true.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testDuplicateRemove() {
        S stub = factory.createInstance();
        table.insert(stub);
        table.remove(stub);
        assertFalse(table.remove(stub), "Duplicate remove should return false.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testRemoveNonExistent() {
        S stub = factory.createInstance();
        assertFalse(table.remove(stub), "Remove should return false.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testContains() {
        S stub = factory.createInstance();
        table.insert(stub);
        assertTrue(table.contains(stub), "Contains should return true.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testContainsNonExistent() {
        S stub = factory.createInstance();
        assertFalse(table.contains(stub), "Contains should return false.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testInsertAllUnique() {
        List<S> stubs = factory.createUniqueInstances();
        List<S> inserted = table.insertAll(stubs);
        assertTrue(ListUtils.haveSameElements(stubs, inserted),
                "Insert all unique elements should return the same elements.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testInsertAllWithDuplicates() {
        List<S> uniqueStubs = factory.createUniqueInstances();
        List<S> duplicateStubs = factory.createDuplicateInstances(uniqueStubs);
        List<S> inserted = table.insertAll(duplicateStubs);
        assertTrue(ListUtils.haveSameElements(inserted, uniqueStubs),
                "Insert all with duplicates elements should return only the unique elements.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testInsertAllWithEmptyList() {
        List<S> inserted = table.insertAll(Collections.emptyList());
        assertTrue(inserted.isEmpty(),
                "Insert all elements of an empty list should return an empty list.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testRemoveAllUnique() {
        List<S> stubs = factory.createUniqueInstances();
        table.insertAll(stubs);
        List<S> removed = table.removeAll(stubs);
        assertTrue(ListUtils.haveSameElements(stubs, removed),
                "Remove all unique elements should return the same elements.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void removeAllWithDuplicates() {
        List<S> uniqueStubs = factory.createUniqueInstances();
        List<S> duplicateStubs = factory.createDuplicateInstances(uniqueStubs);
        table.insertAll(duplicateStubs);
        List<S> removed = table.removeAll(duplicateStubs);
        assertTrue(ListUtils.haveSameElements(removed, uniqueStubs),
                "Remove all with duplicates elements should return only the inserted elements.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testRemoveAllWithEmptyList() {
        List<S> removed = table.insertAll(Collections.emptyList());
        assertTrue(removed.isEmpty(),
                "Remove all elements of an empty list should return an empty list.");
    }

    @RepeatedTest(REPEAT_COUNT)
    void testGetElements() {
        List<S> uniqueStubs = factory.createUniqueInstances();
        table.insertAll(uniqueStubs);
        List<S> elements = table.getElements();
        assertTrue(ListUtils.haveSameElements(elements, uniqueStubs));
    }

    @RepeatedTest(REPEAT_COUNT)
    void testGrow() {
        this.table = createTable(2);
        List<S> uniqueStubs = factory.createUniqueInstances();
        assertEquals(uniqueStubs.size(), table.insertAll(uniqueStubs).size(),
                "Hashtable should grow to accommodate all insertions.");
    }
}