package sort;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Utility class providing helper methods for sorting algorithms.
 */
public class SortUtil {

    /**
     * A set containing integral types that are supported by non-comparison sorting algorithms.
     */
    private static final Set<Class<?>> INTEGRAL_TYPES = Set.of(
            Integer.class, Long.class, Short.class, Byte.class
    );

    /**
     * Checks if the given object is of an integral type.
     *
     * @param o the object to check
     * @return {@code true} if the object is an instance of an integral type, {@code false} otherwise
     */
    public static boolean isIntegral(Object o) {
        return o != null && INTEGRAL_TYPES.contains(o.getClass());
    }

    /**
     * Retrieves a list of sorting algorithms applicable to integral types.
     *
     * @param instance  an instance of the integral type
     * @param filter    a predicate to filter the sorting algorithms
     * @param showSteps whether to show the sorting steps
     * @param <E>       the type of elements, extending {@code Number} and {@code Comparable<E>}
     * @return a list of sorting algorithms that support integral types
     */
    public static <E extends Number & Comparable<E>> List<Sort<E>> getIntegralSortingAlgorithms(E instance, Predicate<SortType> filter, boolean showSteps) {
        return Stream.concat(SortFactory.<E>getNonComparisonSortsStream(filter, showSteps),
                SortFactory.getComparisonSortsStream(instance, filter, showSteps)).toList();
    }

    /**
     * Retrieves a list of sorting algorithms applicable to non-integral types.
     *
     * @param filter    a predicate to filter the sorting algorithms
     * @param showSteps whether to show the sorting steps
     * @param <E>       the type of elements, extending {@code Comparable<E>}
     * @return a list of sorting algorithms that support non-integral types
     */
    public static <E extends Comparable<E>> List<Sort<E>> getNonIntegralSortingAlgorithms(Predicate<SortType> filter, boolean showSteps) {
        return SortFactory.<E>getNonComparisonSortsStream(filter, showSteps).toList();
    }

    /**
     * Swaps two elements in an array.
     *
     * @param arr    the array in which elements should be swapped
     * @param index1 the index of the first element
     * @param index2 the index of the second element
     * @param <T>    the type of elements in the array, extending {@code Comparable<T>}
     */
    public static <T extends Comparable<T>> void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * Finds the index of the smallest element in an array starting from a given index.
     *
     * @param arr   the array to search
     * @param start the starting index for the search
     * @param <T>   the type of elements in the array, extending {@code Comparable<T>}
     * @return the index of the minimum element from the start index onward
     */
    public static <T extends Comparable<T>> int indexOfMin(T[] arr, int start) {
        int n = arr.length;
        int index = start;
        for (int i = start; i < n; i++) {
            if (arr[i].compareTo(arr[index]) < 0) index = i;
        }
        return index;
    }
}