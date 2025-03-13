package sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sort.Sort;
import sort.SortUtil;
import sort.comparison.ComparisonSortType;
import sort.noncomparison.NonComparisonSortType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class SortingByComparatorsTest {

    static Stream<Sort<Integer>> sortingAlgorithms() {
        return SortUtil.getIntegralSortingAlgorithms(0,
                type -> type != ComparisonSortType.BOGOSORT
                        && type != NonComparisonSortType.COUNTING_SORT
                        && type != NonComparisonSortType.RADIX_SORT,
                false).stream();
    }

    private void testSorting(Sort<Integer> sorter, Comparator<Integer> comparator, String testName) {
        Integer[] array = {5, -3, 9, 2, -8, 0, 7};
        Integer[] expected = array.clone();
        Arrays.sort(expected, comparator);

        sorter.sort(array, comparator);

        assertArrayEquals(expected, array, "Failed: " + testName);
    }

    @ParameterizedTest
    @MethodSource("sortingAlgorithms")
    void testNaturalOrder(Sort<Integer> sorter) {
        testSorting(sorter, Comparator.naturalOrder(), "Natural Order");
    }

    @ParameterizedTest
    @MethodSource("sortingAlgorithms")
    void testReverseOrder(Sort<Integer> sorter) {
        testSorting(sorter, Comparator.reverseOrder(), "Reverse Order");
    }

    @ParameterizedTest
    @MethodSource("sortingAlgorithms")
    void testAbsoluteValueOrder(Sort<Integer> sorter) {
        testSorting(sorter, Comparator.comparingInt(Math::abs), "Absolute Value Order");
    }

    @ParameterizedTest
    @MethodSource("sortingAlgorithms")
    void testEvenFirstOrder(Sort<Integer> sorter) {
        testSorting(sorter,
                Comparator.comparingInt((Integer n) -> (n % 2 == 0) ? -1 : 1)
                        .thenComparingInt(n -> n),
                "Even First Order"
        );
    }
}
