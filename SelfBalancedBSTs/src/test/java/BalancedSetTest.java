import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TreeSet.AVLSet;
import TreeSet.BSTSet;
import TreeSet.RedBlackSet;

import static org.junit.jupiter.api.Assertions.*;

class BalancedSetTest {
	private RedBlackSet<Integer> rbSet;
	private AVLSet<Integer> avlSet;

	@BeforeEach
	void setUp() {
		rbSet = new RedBlackSet<>();
		avlSet = new AVLSet<>();
	}

	// Helper to test both sets
	private void testBoth(java.util.function.Consumer<BSTSet<Integer>> action) {
		action.accept(rbSet);
		action.accept(avlSet);
	}

	@Test
	void testEmpty() {
		testBoth(set -> assertTrue(set.size() == 0));
	}

	@Test
	void testInsertOne() {
		testBoth(set -> {
			assertTrue(set.insert(5));
			assertTrue(set.search(5));
			assertEquals(1, set.size());
		});
	}

	@Test
	void testInsertDuplicate() {
		testBoth(set -> {
			assertTrue(set.insert(10));
			assertFalse(set.insert(10));
			assertEquals(1, set.size());
		});
	}

	@Test
	void testInsertMany() {
		testBoth(set -> {
			for (int i = 0; i < 100; i++)
				set.insert(i);
			assertEquals(100, set.size());
			for (int i = 0; i < 100; i++)
				assertTrue(set.search(i));
		});
	}

	@Test
	void testDeleteOne() {
		testBoth(set -> {
			set.insert(1);
			assertTrue(set.delete(1));
			assertFalse(set.search(1));
			assertEquals(0, set.size());
		});
	}

	@Test
	void testDeleteNonExistent() {
		testBoth(set -> assertFalse(set.delete(999)));
	}

	@Test
	void testDeleteFromEmpty() {
		testBoth(set -> assertFalse(set.delete(0)));
	}

	@Test
	void testDeleteAll() {
		testBoth(set -> {
			for (int i = 0; i < 10; i++)
				set.insert(i);

			for (int i = 0; i < 10; i++)
				assertTrue(set.delete(i));
			
			assertTrue(set.size() == 0);
		});
	}

	@Test
	void testContainsAfterInsertDelete() {
		testBoth(set -> {
			set.insert(42);
			set.delete(42);
			assertFalse(set.search(42));
		});
	}

	@Test
	void testInsertNegativeNumbers() {
		testBoth(set -> {
			set.insert(-5);
			set.insert(-10);
			assertTrue(set.search(-5));
			assertTrue(set.search(-10));
		});
	}

	@Test
	void testInsertNullThrows() {
		testBoth(set -> assertThrows(NullPointerException.class, () -> set.insert(null)));
	}

	@Test
	void testRemoveNullThrows() {
		testBoth(set -> assertThrows(NullPointerException.class, () -> set.delete(null)));
	}

	@Test
	void testHeightSingleNode() {
		testBoth(set -> {
			set.insert(1);
			assertEquals(0, set.height());
		});
	}

	@Test
	void testHeightManyNodes() {
		testBoth(set -> {
			for (int i = 0; i < 100; i++)
				set.insert(i);
			assertTrue(set.height() < 30); // Should be balanced
		});
	}

	@Test
	void testAddRemoveinsert() {
		testBoth(set -> {
			set.insert(7);
			set.delete(7);
			assertTrue(set.insert(7));
		});
	}

	@Test
	void testOrderDoesNotMatter() {
		testBoth(set -> {
			set.insert(3);
			set.insert(1);
			set.insert(2);

			assertTrue(set.search(1));
			assertTrue(set.search(2));
			assertTrue(set.search(3));
		});
	}

	@Test
	void testRemoveRoot() {
		testBoth(set -> {
			set.insert(5);
			set.insert(3);
			set.insert(7);
			assertTrue(set.delete(5));
			assertFalse(set.search(5));
		});
	}

	@Test
	void testRemoveLeaf() {
		testBoth(set -> {
			set.insert(5);
			set.insert(3);
			set.insert(7);
			assertTrue(set.delete(3));
			assertFalse(set.search(3));
		});
	}

	@Test
	void testRemoveInternalNode() {
		testBoth(set -> {
			set.insert(5);
			set.insert(3);
			set.insert(7);
			set.insert(6);
			assertTrue(set.delete(7));
			assertFalse(set.search(7));
			assertTrue(set.search(6));
		});
	}
}