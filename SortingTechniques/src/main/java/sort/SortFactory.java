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

    public static <T extends Number & Comparable<T>> Sort<T> getSort(SortType sortType) {
        return switch (sortType) {
            case SortType.BUBBLE_SORT -> new BubbleSort<>();
            case SortType.INSERTION_SORT -> new InsertionSort<>();
            case SortType.SELECTION_SORT -> new SelectionSort<>();
            case SortType.MERGE_SORT -> new MergeSort<>();
            case SortType.QUICK_SORT -> new QuickSort<>();
            case SortType.RADIX_SORT -> new RadixSort<>();
            case SortType.COUNTING_SORT -> new CountingSort<>();
            case SortType.BOGO_SORT -> new BogoSort<>();
        };
    }
}
