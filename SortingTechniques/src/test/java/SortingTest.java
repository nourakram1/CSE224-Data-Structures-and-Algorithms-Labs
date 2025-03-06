import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import sort.*;
import sort.ComparisonBased.Randomized.BogoSort;
import sort.ComparisonBased.Deterministic.BubbleSort;
import sort.ComparisonBased.Deterministic.MergeSort;
import sort.ComparisonBased.Randomized.QuickSort;
import sort.NonComparisonBased.CountingSort;
import sort.NonComparisonBased.RadixSort;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SortingTest {

    static List<Sort<Integer>> integerSortingAlgorithms() {
        return List.of(new BubbleSort<>(), new QuickSort<>(), new MergeSort<>(), new RadixSort<>(), new CountingSort<>());
    }

    static List<Sort<Long>> longSortingAlgorithms() {
        return List.of(new BubbleSort<>(), new QuickSort<>(), new MergeSort<>(), new RadixSort<>());
    }

    static List<Sort<Double>> doubleSortingAlgorithms() {
        return List.of(new BubbleSort<>(), new QuickSort<>(), new MergeSort<>());
    }

    static List<Sort<String>> stringSortingAlgorithms() {
        return List.of(new BubbleSort<>(), new QuickSort<>(), new MergeSort<>());
    }

    static List<Sort<Person>> personSortingAlgorithms() {
        return List.of(new BubbleSort<>(), new QuickSort<>(), new MergeSort<>());
    }

    // -------------------- Integer Tests --------------------

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testIntegerSorting(Sort<Integer> sorter) {
        Integer[] input = {5, 3, 10, 8, 4, 2, -2};
        Integer[] expected = {-2, 2, 3, 4, 5, 8, 10};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testIntegerReversedArray(Sort<Integer> sorter) {
        Integer[] input = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testIntegerEmptyArray(Sort<Integer> sorter) {
        Integer[] input = {};
        Integer[] expected = {};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testSingleElementArray(Sort<Integer> sorter) {
        Integer[] input = {42};
        Integer[] expected = {42};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testAllIdenticalElements(Sort<Integer> sorter) {
        Integer[] input = {7, 7, 7, 7, 7};
        Integer[] expected = {7, 7, 7, 7, 7};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testAlreadySortedArray(Sort<Integer> sorter) {
        Integer[] input = {1, 2, 3, 4, 5, 6, 7};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testAlternatingHighLowValues(Sort<Integer> sorter) {
        Integer[] input = {10, -10, 9, -9, 8, -8, 7, -7, 6, -6};
        Integer[] expected = {-10, -9, -8, -7, -6, 6, 7, 8, 9, 10};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testExtremeValues(Sort<Integer> sorter) {
        Integer[] input = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -1, 1};
        Integer[] expected = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testOddLengthArray(Sort<Integer> sorter) {
        Integer[] input = {3, 1, 4, 1, 5, 9, 2};
        Integer[] expected = {1, 1, 2, 3, 4, 5, 9};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testEvenLengthArray(Sort<Integer> sorter) {
        Integer[] input = {8, 6, 7, 5, 3, 0, 9, 2};
        Integer[] expected = {0, 2, 3, 5, 6, 7, 8, 9};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testBigArraySorting(Sort<Integer> sorter) {
        Random rand = new Random();
        int SIZE = 10_000;

        Integer[] input = new Integer[SIZE];
        for (int i = 0; i < SIZE; i++) {
            input[i] = rand.nextInt(100_000) - 50_000;
        }

        Integer[] expected = input.clone();
        Arrays.sort(expected);

        sorter.sort(input);

        assertArrayEquals(expected, input);
    }

    // -------------------- Long Tests --------------------

    @ParameterizedTest
    @MethodSource("longSortingAlgorithms")
    void testLongSorting(Sort<Long> sorter) {
        Long[] input = {5000000000L, 2000000000L, 8000000000L, 1000000000L};
        Long[] expected = {1000000000L, 2000000000L, 5000000000L, 8000000000L};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("longSortingAlgorithms")
    void testLongNegativeNumbers(Sort<Long> sorter) {
        Long[] input = {-100L, -50L, -200L, -10L};
        Long[] expected = {-200L, -100L, -50L, -10L};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    // -------------------- String Tests --------------------

    @ParameterizedTest
    @MethodSource("stringSortingAlgorithms")
    void testStringSorting(Sort<String> sorter) {
        String[] input = {"Banana", "Apple", "Cherry"};
        String[] expected = {"Apple", "Banana", "Cherry"};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("stringSortingAlgorithms")
    void testStringWithDuplicates(Sort<String> sorter) {
        String[] input = {"apple", "banana", "apple", "cherry"};
        String[] expected = {"apple", "apple", "banana", "cherry"};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("stringSortingAlgorithms")
    void testEmptyStrings(Sort<String> sorter) {
        String[] input = {"", "hello", "", "world"};
        String[] expected = {"", "", "hello", "world"};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }


    // -------------------- Double Tests --------------------

    @ParameterizedTest
    @MethodSource("doubleSortingAlgorithms")
    void testDoubleSorting(Sort<Double> sorter) {
        Double[] input = {3.5, 1.2, 4.8, 2.3};
        Double[] expected = {1.2, 2.3, 3.5, 4.8};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("doubleSortingAlgorithms")
    void testDoubleNegativeNumbers(Sort<Double> sorter) {
        Double[] input = {-2.5, -1.1, -3.7, -0.4};
        Double[] expected = {-3.7, -2.5, -1.1, -0.4};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }


    // -------------------- Custom Object Tests --------------------

    static class Person implements Comparable<Person> {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }
    }

    @ParameterizedTest
    @MethodSource("personSortingAlgorithms")
    void testPersonSorting(Sort<Person> sorter) {
        Person[] input = {
                new Person("Alice", 25),
                new Person("Bob", 20),
                new Person("Charlie", 30)
        };
        Person[] expected = {
                new Person("Bob", 20),
                new Person("Alice", 25),
                new Person("Charlie", 30)
        };
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("personSortingAlgorithms")
    void testPersonSortingWithDuplicates(Sort<Person> sorter) {
        Person[] input = {
                new Person("Alice", 25),
                new Person("Bob", 20),
                new Person("Charlie", 25),
                new Person("David", 30)
        };
        Person[] expected = {
                new Person("Bob", 20),
                new Person("Alice", 25),
                new Person("Charlie", 25),
                new Person("David", 30)
        };
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }
}