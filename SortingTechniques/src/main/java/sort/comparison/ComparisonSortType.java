package sort.comparison;

import lombok.Getter;
import sort.SortType;

import java.util.Arrays;

/**
 * Enum representing different types of comparison-based sorting algorithms.
 */
@Getter
public enum ComparisonSortType implements SortType {
    BUBBLE_SORT(1, "Bubble sort"),
    INSERTION_SORT(2, "Insertion sort"),
    SELECTION_SORT(3, "Selection sort"),
    MERGE_SORT(4, "Merge sort"),
    QUICKSORT(5, "Quicksort"),
    HEAPSORT(6, "Heap sort"),
    BOGOSORT(7, "Bogosort");

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
        return Arrays.stream(values())
                .filter(sortType -> sortType.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}