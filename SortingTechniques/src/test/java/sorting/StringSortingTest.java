package sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sort.Sort;
import sort.SortUtil;
import sort.comparison.ComparisonSortType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class StringSortingTest {

    static List<Sort<String>> stringSortingAlgorithms() {
        return SortUtil.getNonIntegralSortingAlgorithms(type -> type != ComparisonSortType.BOGOSORT, false);
    }

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
}