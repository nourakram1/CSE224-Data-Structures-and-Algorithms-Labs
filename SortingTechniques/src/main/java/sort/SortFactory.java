package sort;

import sort.comparison.ComparisonSortType;
import sort.comparison.deterministic.*;
import sort.comparison.randomized.BogoSort;
import sort.comparison.randomized.QuickSort;
import sort.noncomparison.CountingSort;
import sort.noncomparison.NonComparisonSortType;
import sort.noncomparison.RadixSort;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A factory class for creating instances of different sorting algorithms.
 *
 * <p>
 * This factory provides methods to obtain both comparison-based and non-comparison-based sorting algorithms
 * based on the specified type.
 * </p>
 */
public class SortFactory {

    /**
     * Returns an instance of a comparison-based sorting algorithm.
     *
     * @param comparisonSortType the type of comparison-based sorting algorithm.
     * @param showSteps          whether to record intermediate sorting steps.
     * @param <T>                the type of elements to be sorted, which must implement {@code Comparable<? super T>}.
     * @return an instance of the specified comparison-based sorting algorithm.
     */
    public static <T extends Comparable<? super T>> Sort<T> getComparisonSort(ComparisonSortType comparisonSortType, boolean showSteps) {
        return switch (comparisonSortType) {
            case ComparisonSortType.HEAPSORT -> new HeapSort<>(showSteps);
            case ComparisonSortType.BUBBLE_SORT -> new BubbleSort<>(showSteps);
            case ComparisonSortType.INSERTION_SORT -> new InsertionSort<>(showSteps);
            case ComparisonSortType.SELECTION_SORT -> new SelectionSort<>(showSteps);
            case ComparisonSortType.MERGE_SORT -> new MergeSort<>(showSteps);
            case ComparisonSortType.QUICKSORT -> new QuickSort<>(showSteps);
            case ComparisonSortType.BOGOSORT -> new BogoSort<>(showSteps);
        };
    }

    /**
     * Returns an instance of a non-comparison-based sorting algorithm.
     *
     * @param instance              an instance of the type to be sorted.
     * @param nonComparisonSortType the type of non-comparison-based sorting algorithm.
     * @param showSteps             whether to record intermediate sorting steps.
     * @param <T>                   the type of elements to be sorted, which must be a subclass of {@code Number} and implement {@code Comparable<? super T>}.
     * @return an instance of the specified non-comparison-based sorting algorithm.
     * @throws IllegalArgumentException if the instance is not of an integral type.
     */
    public static <T extends Number & Comparable<? super T>> Sort<T> getNonComparisonSort(Object instance, NonComparisonSortType nonComparisonSortType, boolean showSteps) {
        if (!SortUtil.isIntegral(instance)) {
            throw new IllegalArgumentException("Instance is not of integral type");
        }
        return switch (nonComparisonSortType) {
            case NonComparisonSortType.COUNTING_SORT -> new CountingSort<>(showSteps);
            case NonComparisonSortType.RADIX_SORT -> new RadixSort<>(showSteps);
        };
    }

    /**
     * Returns a stream of non-comparison sorting algorithm instances based on the provided number instance and filter criteria.
     *
     * @param <T>       The type of number to be sorted, which must extend Number and be Comparable.
     * @param instance  An instance of the number type to be sorted.
     * @param filter    A predicate to filter the sorting algorithms based on their SortType.
     * @param showSteps A boolean flag indicating whether to show the steps of the sorting process.
     * @return A Stream of Sort<T> instances that match the given number type and filter criteria.
     */
    public static <T extends Number & Comparable<? super T>> Stream<Sort<T>> getComparisonSortsStream(T instance, Predicate<SortType> filter,
                                                                                              boolean showSteps) {
        return Arrays.stream(NonComparisonSortType.values())
                .filter(filter)
                .map(type -> getNonComparisonSort(instance, type, showSteps));
    }

    /**
     * Returns a stream of sorting algorithm instances based on the provided filter criteria.
     *
     * @param <T>       The type of elements to be sorted, which must extend Comparable.
     * @param filter    A predicate to filter the sorting algorithms based on their SortType.
     * @param showSteps A boolean flag indicating whether to show the steps of the sorting process.
     * @return A Stream of Sort<T> instances that match the filter criteria.
     */
    public static <T extends Comparable<? super T>> Stream<Sort<T>> getNonComparisonSortsStream(Predicate<SortType> filter,
                                                                                        boolean showSteps) {
        return Arrays.stream(ComparisonSortType.values())
                .filter(filter)
                .map(type -> getComparisonSort(type, showSteps));
    }
}