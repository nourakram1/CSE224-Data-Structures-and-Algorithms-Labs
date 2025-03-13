package sort.noncomparison;

import lombok.Getter;
import sort.SortType;

import java.util.Arrays;

/**
 * Enum representing different types of non-comparison-based sorting algorithms.
 */
@Getter
public enum NonComparisonSortType implements SortType {
    COUNTING_SORT(8, "Counting Sort"),
    RADIX_SORT(9, "Radix sort");


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
     * Retrieves a sorting algorithm type by its unique code.
     *
     * @param code The unique code of the sorting algorithm.
     * @return The corresponding {@code NonComparisonSortType}.
     * @throws IllegalArgumentException if no matching code is found.
     */
    public static NonComparisonSortType getByCode(int code) {
        return Arrays.stream(values())
                .filter(sortType -> sortType.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}