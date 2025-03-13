package sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sort.Sort;
import sort.SortUtil;
import sort.comparison.ComparisonSortType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IntegerSortingTest {

    static List<Sort<Integer>> integerSortingAlgorithms() {
        return SortUtil.getIntegralSortingAlgorithms(0, type -> type != ComparisonSortType.BOGOSORT, false);
    }

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
    void testEvenLengthArray(Sort<Integer> sorter) {
        Integer[] input = {8, 6, 7, 5, 3, 0, 9, 2};
        Integer[] expected = {0, 2, 3, 5, 6, 7, 8, 9};
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("integerSortingAlgorithms")
    void testOddLengthArray(Sort<Integer> sorter) {
        Integer[] input = {8, 6, 7, 5, 3, 0, 2};
        Integer[] expected = {0, 2, 3, 5, 6, 7, 8};
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
}