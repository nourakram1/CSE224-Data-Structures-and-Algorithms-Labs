package sort;

import sort.comparison.ComparisonSortType;
import sort.comparison.deterministic.*;
import sort.noncomparison.NonComparisonSortType;
import sort.comparison.randomized.BogoSort;
import sort.comparison.randomized.QuickSort;
import sort.noncomparison.CountingSort;
import sort.noncomparison.RadixSort;

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
     * @param <T>                the type of elements to be sorted, which must implement {@code Comparable<T>}.
     * @return an instance of the specified comparison-based sorting algorithm.
     */
    public static <T extends Comparable<T>> Sort<T> getComparisonSort(ComparisonSortType comparisonSortType, boolean showSteps) {
        return switch (comparisonSortType) {
            case ComparisonSortType.HEAPSORT       -> new HeapSort<>(showSteps);
            case ComparisonSortType.BUBBLE_SORT    -> new BubbleSort<>(showSteps);
            case ComparisonSortType.INSERTION_SORT -> new InsertionSort<>(showSteps);
            case ComparisonSortType.SELECTION_SORT -> new SelectionSort<>(showSteps);
            case ComparisonSortType.MERGE_SORT     -> new MergeSort<>(showSteps);
            case ComparisonSortType.QUICKSORT      -> new QuickSort<>(showSteps);
            case ComparisonSortType.BOGOSORT       -> new BogoSort<>(showSteps);
        };
    }

    /**
     * Returns an instance of a non-comparison-based sorting algorithm.
     *
     * @param instance            an instance of the type to be sorted.
     * @param nonComparisonSortType the type of non-comparison-based sorting algorithm.
     * @param showSteps           whether to record intermediate sorting steps.
     * @param <T>                 the type of elements to be sorted, which must be a subclass of {@code Number} and implement {@code Comparable<T>}.
     * @return an instance of the specified non-comparison-based sorting algorithm.
     * @throws IllegalArgumentException if the instance is not of an integral type.
     */
    public static <T extends Number & Comparable<T>> Sort<T> getNonComparisonSort(Object instance, NonComparisonSortType nonComparisonSortType, boolean showSteps) {
        if (!SortUtil.isIntegral(instance)) {
            throw new IllegalArgumentException("Instance is not of integral type");
        }
        return switch (nonComparisonSortType) {
            case NonComparisonSortType.COUNTING_SORT -> new CountingSort<>(showSteps);
            case NonComparisonSortType.RADIX_SORT -> new RadixSort<>(showSteps);
        };
    }
}