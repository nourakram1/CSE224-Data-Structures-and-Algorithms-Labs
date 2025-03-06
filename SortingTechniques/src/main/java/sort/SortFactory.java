package sort;

import sort.ComparisonBased.Randomized.BogoSort;
import sort.ComparisonBased.Deterministic.BubbleSort;
import sort.ComparisonBased.Deterministic.MergeSort;
import sort.ComparisonBased.Randomized.QuickSort;
import sort.NonComparisonBased.RadixSort;

public class SortFactory {

    public static <T extends Comparable<T>> Sort<T> getSort(SortType sortType) {
        return switch (sortType) {
            case SortType.BUBBLE_SORT -> new BubbleSort<>();
            case SortType.MERGE_SORT -> new MergeSort<>();
            case SortType.QUICKSORT -> new QuickSort<>();
            case SortType.RADIX_SORT -> new RadixSort<>();
            case SortType.BOGO_SORT -> new BogoSort<>();
        };
    }
}
