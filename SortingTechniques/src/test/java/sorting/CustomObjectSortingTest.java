package sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sort.Sort;
import sort.SortUtil;
import sort.comparison.ComparisonSortType;
import stub.Person;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CustomObjectSortingTest {

    static List<Sort<Person>> personSortingAlgorithms() {
        return SortUtil.getNonIntegralSortingAlgorithms(type -> type != ComparisonSortType.BOGOSORT, false);
    }

    @ParameterizedTest
    @MethodSource("personSortingAlgorithms")
    void testPersonSorting(Sort<Person> sorter) {
        Person[] input = {
                new Person("Alice", 25),
                new Person("Bob", 20),
                new Person("Charlie", 30)
        };
        Person[] expected = input.clone();
        Arrays.sort(expected);
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
        Person[] expected = input.clone();
        Arrays.sort(expected);
        sorter.sort(input);
        assertArrayEquals(expected, input);
    }
}