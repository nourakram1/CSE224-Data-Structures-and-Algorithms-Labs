package sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sort.Sort;
import sort.SortUtil;
import sort.comparison.ComparisonSortType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class LongSortingTest {

    static List<Sort<Long>> longSortingAlgorithms() {
        return SortUtil.getIntegralSortingAlgorithms(0L, type -> type != ComparisonSortType.BOGOSORT, false);
    }

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
}