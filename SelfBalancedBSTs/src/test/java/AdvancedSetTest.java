
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TreeSet.AVLSet;
import TreeSet.BSTSet;
import TreeSet.RedBlackSet;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class AdvancedSetTest {
	private AVLSet<Integer> avlSet;
	private RedBlackSet<Integer> rbSet;

	@BeforeEach
	void setup() {
		avlSet = new AVLSet<>();
		rbSet = new RedBlackSet<>();
	}

	private List<BSTSet<Integer>> allSets() {
		return Arrays.asList(avlSet, rbSet);
	}

	@Test
	void testEmpty() {
		allSets().forEach(set -> assertTrue(set.size() == 0));
	}

	@Test
	void testSingleInsert() {
		allSets().forEach(set -> assertTrue(set.insert(1)));
	}

	@Test
	void testSingledelete() {
		allSets().forEach(set -> {
			set.insert(2);
			assertTrue(set.delete(2));
			assertFalse(set.search(2));
		});
	}

	@Test
	void testDuplicateInsert() {
		allSets().forEach(set -> {
			assertTrue(set.insert(3));
			assertFalse(set.insert(3));
		});
	}

	@Test
	void testNegativeNumbers() {
		allSets().forEach(set -> {
			assertTrue(set.insert(-10));
			assertTrue(set.search(-10));
		});
	}

	@Test
	void testZero() {
		allSets().forEach(set -> {
			assertTrue(set.insert(0));
			assertTrue(set.search(0));
		});
	}

	@Test
	void testLargeNumbers() {
		allSets().forEach(set -> {
			assertTrue(set.insert(Integer.MAX_VALUE));
			assertTrue(set.insert(Integer.MIN_VALUE));
			assertTrue(set.search(Integer.MAX_VALUE));
			assertTrue(set.search(Integer.MIN_VALUE));
		});
	}

	@Test
	void testBulkInsert() {
		allSets().forEach(set -> {
			for (int i = 0; i < 1000; i++)
				set.insert(i);
			assertEquals(1000, set.size());
		});
	}

	@Test
	void testBulkdelete() {
		allSets().forEach(set -> {
			for (int i = 0; i < 100; i++)
				set.insert(i);
			for (int i = 0; i < 100; i++)
				assertTrue(set.delete(i));
			assertEquals(0, set.size());
		});
	}

	@Test
	void testsearchAfterdelete() {
		allSets().forEach(set -> {
			set.insert(5);
			set.delete(5);
			assertFalse(set.search(5));
		});
	}

	@Test
	void testHeightAfterInsertions() {
		allSets().forEach(set -> {
			for (int i = 0; i < 100; i++)
				set.insert(i);
			assertTrue(set.height() < 30);
		});
	}

	@Test
	void testHeightAfterRemovals() {
		allSets().forEach(set -> {
			for (int i = 0; i < 100; i++)
				set.insert(i);
			for (int i = 0; i < 50; i++)
				set.delete(i);
			assertTrue(set.height() < 30);
		});
	}

	@Test
	void testRandomOrderInsert() {
		allSets().forEach(set -> {
			List<Integer> nums = new ArrayList<>();
			for (int i = 0; i < 100; i++)
				nums.add(i); // Fixed here
			Collections.shuffle(nums);
			nums.forEach(set::insert); // Fixed here
			assertEquals(100, set.size());
		});
	}

	@Test
	void testRandomOrderdelete() {
		allSets().forEach(set -> {
			List<Integer> nums = new ArrayList<>();
			for (int i = 0; i < 100; i++)
				nums.add(i);
			
			nums.forEach(set::insert); // Fixed here
			Collections.shuffle(nums);
			nums.forEach(set::delete); // Fixed here
			assertEquals(0, set.size());
		});
	}


	@Test
	void testRemoveNonExistent() {
		allSets().forEach(set -> assertFalse(set.delete(9999)));
	}

	@Test
	void testAddRemoveinsert() {
		allSets().forEach(set -> {
			set.insert(7);
			set.delete(7);
			assertTrue(set.insert(7));
		});
	}

	@Test
	void testOrderInvariance() {
		allSets().forEach(set -> {
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
		allSets().forEach(set -> {
			set.insert(5);
			set.insert(3);
			set.insert(7);
			assertTrue(set.delete(5));
			assertFalse(set.search(5));
		});
	}

	@Test
	void testRemoveLeaf() {
		allSets().forEach(set -> {
			set.insert(5);
			set.insert(3);
			set.insert(7);
			assertTrue(set.delete(3));
			assertFalse(set.search(3));
		});
	}

	@Test
	void testRemoveInternalNode() {
		allSets().forEach(set -> {
			set.insert(5);
			set.insert(3);
			set.insert(7);
			set.insert(6);
			assertTrue(set.delete(7));
			assertFalse(set.search(7));
			assertTrue(set.search(6));
		});
	}

	@Test
	void testSizeAfterMixedOps() {
		allSets().forEach(set -> {
			set.insert(1);
			set.insert(2);
			set.delete(1);
			set.insert(3);
			assertEquals(2, set.size());
		});
	}

	@Test
	void testIsEmptyAfterClear() {
		allSets().forEach(set -> {
			for (int i = 0; i < 10; i++)
				set.insert(i);
			for (int i = 0; i < 10; i++)
				set.delete(i);
			assertTrue(set.size() == 0);
		});
	}

	@Test
	void testNullInsertThrows() {
		allSets().forEach(set -> assertThrows(NullPointerException.class, () -> set.insert(null)));
	}

	@Test
	void testNullRemoveThrows() {
		allSets().forEach(set -> assertThrows(NullPointerException.class, () -> set.delete(null)));
	}

	@Test
	void testsearchNull() {
		allSets().forEach(set -> assertThrows(NullPointerException.class, () -> set.search(null)));
	}

	@Test
	void testHeightSingleNode() {
		allSets().forEach(set -> {
			set.insert(1);
			assertEquals(0, set.height());
		});
	}

	@Test
	void testHeightEmpty() {
		allSets().forEach(set -> assertEquals(0, set.height()));
	}

	@Test
	void testStressInsertdelete() {
		allSets().forEach(set -> {
			for (int i = 0; i < 1000; i++)
				set.insert(i);
			for (int i = 0; i < 1000; i += 2)
				set.delete(i);
			assertEquals(500, set.size());
		});
	}

	@Test
	void testReAddAfterdelete() {
		allSets().forEach(set -> {
			set.insert(10);
			set.delete(10);
			assertTrue(set.insert(10));
		});
	}

	@Test
	void testMultipleDuplicates() {
		allSets().forEach(set -> {
			assertTrue(set.insert(20));
			assertFalse(set.insert(20));
			assertFalse(set.insert(20));
			assertEquals(1, set.size());
		});
	}

	@Test
	void testRemoveAllButOne() {
		allSets().forEach(set -> {
			set.insert(1);
			set.insert(2);
			set.insert(3);
			set.delete(1);
			set.delete(2);
			assertEquals(1, set.size());
		});
	}

	@Test
	void testAddRemoveMix() {
		allSets().forEach(set -> {
			set.insert(1);
			set.insert(2);
			set.delete(1);
			set.insert(3);
			set.delete(2);
			assertTrue(set.search(3));
			assertFalse(set.search(1));
			assertFalse(set.search(2));
		});
	}

	@Test
	void testAddRemoveRandomized() {
		allSets().forEach(set -> {
			Random rand = new Random(42);
			Set<Integer> reference = new HashSet<>();
			for (int i = 0; i < 200; i++) {
				int v = rand.nextInt(100);
				set.insert(v);
				reference.add(v);
			}
			for (int i = 0; i < 100; i++) {
				int v = rand.nextInt(100);
				set.delete(v);
				reference.remove(v);
			}
			for (int v = 0; v < 100; v++) {
				assertEquals(reference.contains(v), set.search(v));
			}
		});
	}

	@Test
	void testSizeConsistency() {
		allSets().forEach(set -> {
			set.insert(1);
			set.insert(2);
			set.insert(3);
			set.delete(2);
			set.insert(4);
			assertEquals(3, set.size());
		});
	}

	@Test
	void testHeightAfterDuplicates() {
		allSets().forEach(set -> {
			set.insert(1);
			set.insert(1);
			set.insert(1);
			assertEquals(0, set.height());
		});
	}

	@Test
	void testRemoveFromEmpty() {
		allSets().forEach(set -> assertFalse(set.delete(123)));
	}

	@Test
	void testsearchAfterBulkInsert() {
		allSets().forEach(set -> {
			for (int i = 0; i < 50; i++)
				set.insert(i);
			for (int i = 0; i < 50; i++)
				assertTrue(set.search(i));
		});
	}

	@Test
	void testRemoveAll() {
		allSets().forEach(set -> {
			for (int i = 0; i < 20; i++)
				set.insert(i);
			for (int i = 0; i < 20; i++)
				set.delete(i);
			assertTrue(set.size() == 0);
		});
	}
}