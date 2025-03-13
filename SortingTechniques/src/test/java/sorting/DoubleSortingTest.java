package sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sort.Sort;
import sort.SortUtil;
import sort.comparison.ComparisonSortType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DoubleSortingTest {

    static List<Sort<Double>> doubleSortingAlgorithms() {
        return SortUtil.getNonIntegralSortingAlgorithms(type -> type != ComparisonSortType.BOGOSORT, false);
    }

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
}