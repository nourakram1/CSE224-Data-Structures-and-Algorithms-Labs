package sort;

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
     * Returns the name of the sorting algorithm.
     *
     * @return A string representing the name of the sorting algorithm.
     */
    String getName();
}