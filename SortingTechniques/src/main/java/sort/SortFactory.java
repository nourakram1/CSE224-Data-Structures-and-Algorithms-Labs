package sort;

public class SortFactory {

    public static <T extends Comparable<T>> Sort<T> getSort(SortType sortType) {
        return switch (sortType) {
            case SortType.BUBBLE_SORT -> new BubbleSort<>();
            case SortType.MERGE_SORT -> new MergeSort<>();
            case SortType.QUICKSORT -> new QuickSort<>();
            case SortType.RADIX_SORT -> new RadixSort<>();
        };
    }
}
