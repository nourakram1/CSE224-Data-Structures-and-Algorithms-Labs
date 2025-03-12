package sort.comparison;

import lombok.Getter;
import sort.Sort;
import sort.SortFactory;
import sort.SortType;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Enum representing different types of comparison-based sorting algorithms.
 */
@Getter
public enum ComparisonSortType implements SortType {
    BUBBLE_SORT     (1, "Bubble sort"),
    INSERTION_SORT  (2, "Insertion sort"),
    SELECTION_SORT  (3, "Selection sort"),
    MERGE_SORT      (4, "Merge sort"),
    QUICKSORT       (5, "Quicksort"),
    BOGOSORT        (6, "Bogosort");

    private final int code;
    private final String name;


    /**
     * Constructor for the ComparisonSortType enum.
     *
     * @param code Unique identifier for the sorting algorithm.
     * @param name Name of the sorting algorithm.
     */
    ComparisonSortType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Retrieves a sorting algorithm type by its unique code.
     *
     * @param code The unique code of the sorting algorithm.
     * @return The corresponding {@code ComparisonSortType}.
     * @throws IllegalArgumentException if no matching code is found.
     */
    public static ComparisonSortType getByCode(int code) {
        return  Arrays.stream(values())
                .filter(sortType -> sortType.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Returns a stream of sorting algorithm instances based on the provided filter criteria.
     *
     * @param <T> The type of elements to be sorted, which must extend Comparable.
     * @param filter A predicate to filter the sorting algorithms based on their SortType.
     * @param showSteps A boolean flag indicating whether to show the steps of the sorting process.
     * @return A Stream of Sort<T> instances that match the filter criteria.
     */
    public static <T extends Comparable<T>> Stream<Sort<T>> getSortingAlgorthimsStream(Predicate<SortType> filter,
                                                                                       boolean showSteps) {
        return Arrays.stream(ComparisonSortType.values())
                .filter(filter)
                .map(type -> SortFactory.getComparisonSort(type, showSteps));
    }
}