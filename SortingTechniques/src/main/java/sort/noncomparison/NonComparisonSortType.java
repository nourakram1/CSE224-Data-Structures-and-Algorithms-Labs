package sort.noncomparison;

import lombok.Getter;
import sort.Sort;
import sort.SortFactory;
import sort.SortType;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Enum representing different types of non-comparison-based sorting algorithms.
 */
@Getter
public enum NonComparisonSortType implements SortType {
    COUNTING_SORT   (7, "Counting Sort"),
    RADIX_SORT      (8, "Radix sort");


    private final int code;
    private final String name;

    /**
     * Constructor for NonComparisonSortType.
     *
     * @param code The unique code representing the sorting algorithm.
     * @param name The name of the sorting algorithm.
     */
    NonComparisonSortType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Returns a stream of non-comparison sorting algorithm instances based on the provided number instance and filter criteria.
     *
     * @param <T>        The type of number to be sorted, which must extend Number and be Comparable.
     * @param instance   An instance of the number type to be sorted.
     * @param filter     A predicate to filter the sorting algorithms based on their SortType.
     * @param showSteps  A boolean flag indicating whether to show the steps of the sorting process.
     * @return           A Stream of Sort<T> instances that match the given number type and filter criteria.
     */
    public static <T extends Number & Comparable<T>> Stream<Sort<T>> getSortingAlgorthimsStream(T instance, Predicate<SortType> filter,
                                                                                       boolean showSteps) {
        return Arrays.stream(NonComparisonSortType.values())
                .filter(filter)
                .map(type -> SortFactory.getNonComparisonSort(instance, type, showSteps));
    }
}