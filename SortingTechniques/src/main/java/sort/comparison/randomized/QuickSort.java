package sort.comparison.randomized;

import sort.Sort;
import sort.SortUtil;

import java.util.Comparator;
import java.util.Random;

/**
 * Implementation of the QuickSort algorithm.
 * This version supports both natural ordering and custom comparators.
 *
 * @param <T> The type of elements to be sorted, must implement Comparable<T>.
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    private final Random random;

    public QuickSort(boolean showSteps) {
        super(showSteps);
        this.random = new Random();
    }

    /**
     * Sorts the given array using QuickSort with natural ordering.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        sort(arr, 0, arr.length - 1, Comparator.naturalOrder());
        addStep(arr);
    }

    /**
     * Sorts the given array using QuickSort and a custom comparator.
     *
     * @param arr        The array to be sorted.
     * @param comparator The comparator to determine element order.
     */
    @Override
    public void sort(T[] arr, Comparator<T> comparator) {
        sort(arr, 0, arr.length - 1, comparator);
        addStep(arr);
    }

    /**
     * Recursive function to perform QuickSort on a portion of the array.
     *
     * @param arr        Array to sort.
     * @param left       Left index of the current partition range.
     * @param right      Right index of the current partition range.
     * @param comparator Custom comparator to define sorting order.
     */
    private void sort(T[] arr, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            addStep(arr);

            // Partition the array and get the pivot index
            int index = partition(arr, left, right, comparator);

            // Recursively sort the two partitions
            sort(arr, left, index - 1, comparator);
            sort(arr, index + 1, right, comparator);
        }
    }

    /**
     * Partitions the array using a randomly selected pivot and the provided comparator.
     *
     * @param arr        Array to partition.
     * @param left       Left index of the partition range.
     * @param right      Right index of the partition range.
     * @param comparator Comparator to use for element comparison.
     * @return The final index of the pivot.
     */
    private int partition(T[] arr, int left, int right, Comparator<T> comparator) {
        int randomIndex = random.nextInt(right - left + 1) + left;
        SortUtil.swap(arr, randomIndex, right);
        T pivot = arr[right];
        int index = left;

        for (int i = left; i < right; i++) {
            if (comparator.compare(arr[i], pivot) < 0) {
                SortUtil.swap(arr, i, index);
                index++;
            }
        }

        SortUtil.swap(arr, index, right);
        return index;
    }

    /**
     * Helper method to check if an array is sorted.
     *
     * @param arr        The array to check.
     * @param comparator The comparator for sorting order.
     * @return True if sorted, false otherwise.
     */
    private boolean isSorted(T[] arr, Comparator<T> comparator) {
        for (int i = 1; i < arr.length; i++) {
            if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
