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
     * @param <E>       the type of elements, extending {@code Number} and {@code Comparable<? super E>}
     * @return a list of sorting algorithms that support integral types
     */
    public static <E extends Number & Comparable<? super E>> List<Sort<E>> getIntegralSortingAlgorithms(E instance, Predicate<SortType> filter, boolean showSteps) {
        return Stream.concat(SortFactory.<E>getNonComparisonSortsStream(filter, showSteps),
                SortFactory.getComparisonSortsStream(instance, filter, showSteps)).toList();
    }

    /**
     * Retrieves a list of sorting algorithms applicable to non-integral types.
     *
     * @param filter    a predicate to filter the sorting algorithms
     * @param showSteps whether to show the sorting steps
     * @param <E>       the type of elements, extending {@code Comparable<? super E>}
     * @return a list of sorting algorithms that support non-integral types
     */
    public static <E extends Comparable<? super E>> List<Sort<E>> getNonIntegralSortingAlgorithms(Predicate<SortType> filter, boolean showSteps) {
        return SortFactory.<E>getNonComparisonSortsStream(filter, showSteps).toList();
    }

    /**
     * Swaps two elements in an array.
     *
     * @param arr    the array in which elements should be swapped
     * @param index1 the index of the first element
     * @param index2 the index of the second element
     * @param <E>    the type of elements in the array, extending {@code Comparable<? super E>}
     */
    public static <E extends Comparable<? super E>> void swap(E[] arr, int index1, int index2) {
        E temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}