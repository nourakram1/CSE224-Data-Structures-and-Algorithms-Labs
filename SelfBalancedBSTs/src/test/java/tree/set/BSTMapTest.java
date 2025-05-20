package tree.set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BSTMapTest {

	static Stream<BSTSet<Integer>> createBSTs() {
		return Stream.of(
					new RedBlackSet<>(),
					new AVLSet<>()
				);
	}

	@ParameterizedTest
	@MethodSource("createBSTs")
	public void testSet(BSTSet<Integer> set) {
		assertTrue(set.insert(10));
		assertTrue(set.insert(20));
		assertTrue(set.insert(30));
		assertTrue(set.insert(40));
		assertTrue(set.insert(50));
		assertTrue(set.insert(25));

		assertTrue(set.search(10));
		assertTrue(set.search(20));
		assertTrue(set.search(30));
		assertTrue(set.search(40));
		assertTrue(set.search(50));
		assertTrue(set.search(25));

		assertFalse(set.search(15));
	}

	@ParameterizedTest
	@MethodSource("createBSTs")
	public void testDuplicates(BSTSet<Integer> set) {
		assertTrue(set.insert(10));
		assertFalse(set.insert(10));
		assertTrue(set.insert(20));
		assertTrue(set.insert(30));
		assertTrue(set.insert(40));
		assertTrue(set.insert(50));
		assertTrue(set.insert(25));

		assertTrue(set.search(10));
		assertTrue(set.search(20));
		assertTrue(set.search(30));
		assertTrue(set.search(40));
		assertTrue(set.search(50));
		assertTrue(set.search(25));

		assertFalse(set.search(15));
	}

	@ParameterizedTest
	@MethodSource("createBSTs")
	public void testNegativeNumbers(BSTSet<Integer> set) {
		assertTrue(set.insert(-10));
		assertTrue(set.insert(-20));
		assertTrue(set.insert(-30));
		assertTrue(set.insert(-40));
		assertTrue(set.insert(-50));
		assertTrue(set.insert(-25));

		assertTrue(set.search(-10));
		assertTrue(set.search(-20));
		assertTrue(set.search(-30));
		assertTrue(set.search(-40));
		assertTrue(set.search(-50));
		assertTrue(set.search(-25));

		assertFalse(set.search(15));
	}

	@ParameterizedTest
	@MethodSource("createBSTs")
	public void testMixedNumbers(BSTSet<Integer> set) {
		assertTrue(set.insert(-10));
		assertTrue(set.insert(20));
		assertTrue(set.insert(-30));
		assertTrue(set.insert(40));
		assertTrue(set.insert(-50));
		assertTrue(set.insert(25));

		assertTrue(set.search(-10));
		assertTrue(set.search(20));
		assertTrue(set.search(-30));
		assertTrue(set.search(40));
		assertTrue(set.search(-50));
		assertTrue(set.search(25));

		assertFalse(set.search(15));
	}

	static Stream<BSTSet<String>> createStringBSTs() {
		return Stream.of(
				new RedBlackSet<>(),
				new AVLSet<>());
	}

	@ParameterizedTest
	@MethodSource("createStringBSTs")
	public void testStrings(BSTSet<String> set) {
		assertTrue(set.insert("apple"));
		assertTrue(set.insert("banana"));
		assertTrue(set.insert("cherry"));
		assertTrue(set.insert("date"));
		assertTrue(set.insert("elderberry"));
		assertTrue(set.insert("fig"));

		assertTrue(set.search("apple"));
		assertTrue(set.search("banana"));
		assertTrue(set.search("cherry"));
		assertTrue(set.search("date"));
		assertTrue(set.search("elderberry"));
		assertTrue(set.search("fig"));

		assertFalse(set.search("grape"));
	}

	@ParameterizedTest
	@MethodSource("createBSTs")
	public void testEmptySet(BSTSet<Integer> set) {
		assertFalse(set.search(10));
	}

	@ParameterizedTest
	@MethodSource("createBSTs")
	public void testNull(BSTSet<Integer> set) {
		assertThrows(NullPointerException.class, 
		() -> set.search(null));
	}

	@ParameterizedTest
	@MethodSource("createBSTs")
	public void testLargeDataSet(BSTSet<Integer> set) {
		for (int i = 0; i < 10000; i++) {
			assertTrue(set.insert(i));
		}
		for (int i = 0; i < 10000; i++) {
			assertTrue(set.search(i));
		}
		assertFalse(set.search(10001));
	}

	@ParameterizedTest
	@MethodSource("createBSTs")
	public void testRandomData(BSTSet<Integer> set) {
		for (int i = 0; i < 1000; i++) {
			int randomNum = (int) (Math.random() * 10000);
			set.insert(randomNum);
			assertTrue(set.search(randomNum));
		}
	}

	@ParameterizedTest
	@MethodSource("createStringBSTs")
	public void testLongStrings(BSTSet<String> set) {
		String longString = "a".repeat(10000);
		assertTrue(set.insert(longString));
		assertTrue(set.search(longString));
		assertFalse(set.search("nonexistent_string"));
	}

	static Stream<BSTSet<CustomObject>> createCustomBSTs() {
		return Stream.of(
				new RedBlackSet<>(),
				new AVLSet<>());
	}

	@ParameterizedTest
	@MethodSource("createCustomBSTs")
	public void testCustomObjects(BSTSet<CustomObject> set) {
		CustomObject obj1 = new CustomObject(1, "Object 1");
		CustomObject obj2 = new CustomObject(2, "Object 2");
		CustomObject obj3 = new CustomObject(3, "Object 3");

		assertTrue(set.insert(obj1));
		assertTrue(set.insert(obj2));
		assertTrue(set.insert(obj3));

		assertTrue(set.search(obj1));
		assertTrue(set.search(obj2));
		assertTrue(set.search(obj3));

		assertFalse(set.search(new CustomObject(4, "Nonexistent Object")));
	}

	public static class CustomObject implements Comparable<CustomObject> {
		int id;
		String name;

		CustomObject(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public int compareTo(CustomObject other) {
			return Integer.compare(this.id, other.id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof CustomObject other))
				return false;
            return this.id == other.id && this.name.equals(other.name);
		}

		@Override
		public int hashCode() {
			return 31 * id + name.hashCode();
		}
	}
}
