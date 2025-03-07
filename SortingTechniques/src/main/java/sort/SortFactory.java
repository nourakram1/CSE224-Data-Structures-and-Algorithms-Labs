package sort;

import sort.ComparisonBased.Deterministic.InsertionSort;
import sort.ComparisonBased.Deterministic.SelectionSort;
import sort.ComparisonBased.Randomized.BogoSort;
import sort.ComparisonBased.Deterministic.BubbleSort;
import sort.ComparisonBased.Deterministic.MergeSort;
import sort.ComparisonBased.Randomized.QuickSort;
import sort.NonComparisonBased.CountingSort;
import sort.NonComparisonBased.RadixSort;


public class SortFactory {

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> Sort<T> getSort(SortType sortType, boolean showSteps) {
        return switch (sortType) {
            case SortType.BUBBLE_SORT    -> new BubbleSort<>(showSteps);
            case SortType.INSERTION_SORT -> new InsertionSort<>(showSteps);
            case SortType.SELECTION_SORT -> new SelectionSort<>(showSteps);
            case SortType.MERGE_SORT     -> new MergeSort<>(showSteps);
            case SortType.QUICKSORT      -> new QuickSort<>(showSteps);
            case SortType.RADIX_SORT     -> (Sort<T>) new RadixSort<>(showSteps);
            case SortType.COUNTING_SORT  -> (Sort<T>) new CountingSort<>(showSteps);
            case SortType.BOGOSORT       -> new BogoSort<>(showSteps);
        };
    }
}
