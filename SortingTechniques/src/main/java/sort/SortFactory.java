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

    public static <T extends Number & Comparable<T>> Sort<T> getSort(SortType sortType, boolean showSteps) {
        return switch (sortType) {
            case SortType.BUBBLE_SORT   -> new BubbleSort<>(showSteps);
            case SortType.MERGE_SORT    -> new MergeSort<>(showSteps);
            case SortType.QUICK_SORT    -> new QuickSort<>(showSteps);
            case SortType.RADIX_SORT    -> new RadixSort<>(showSteps);
            case SortType.COUNTING_SORT -> new CountingSort<>(showSteps);
            case SortType.BOGO_SORT     -> new BogoSort<>(showSteps);
            default                     -> new QuickSort<>(showSteps);
        };
    }
}
