package sort;

import sort.comparison.ComparisonSortType;
import sort.noncomparison.NonComparisonSortType;

/**
 * A common interface for sorting algorithm types.
 * <p>
 * Implemented by {@code NonComparisonSortType} and {@code ComparisonSortType}, this interface
 * provides a unified way to represent sorting types. It allows sorting algorithms to be
 * managed generically in collections such as {@code List<SortType>} and {@code Map<SortType, ?>}.
 * </p>
 */
public interface SortType {

    /**
     * Retrieves a sorting algorithm type based on a given code.
     * <p>
     * This method first attempts to retrieve a {@code ComparisonSortType} using the provided code.
     * If the code does not correspond to a {@code ComparisonSortType}, it falls back to trying a
     * {@code NonComparisonSortType}. If neither type matches, an {@code IllegalArgumentException}
     * may be thrown.
     * </p>
     *
     * @param code The unique identifier of the sorting algorithm.
     * @return The corresponding {@code SortType} instance.
     * @throws IllegalArgumentException if no matching sorting type is found.
     */
    static SortType getSortType(int code) {
        try {
            return ComparisonSortType.getByCode(code);
        } catch (IllegalArgumentException e) {
            return NonComparisonSortType.getByCode(code);
        }
    }

    /**
     * Returns the name of the sorting algorithm.
     *
     * @return A string representing the name of the sorting algorithm.
     */
    String getName();
}