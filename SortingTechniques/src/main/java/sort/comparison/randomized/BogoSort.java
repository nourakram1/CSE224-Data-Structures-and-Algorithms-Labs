package sort.comparison.randomized;

import sort.Sort;
import sort.SortUtil;

import java.util.Comparator;
import java.util.Random;

/**
 * Implementation of the highly inefficient BogoSort algorithm.
 * BogoSort repeatedly shuffles the array until it happens to be sorted.
 *
 * @param <T> The type of elements to be sorted, which must implement Comparable<T>.
 */
public class BogoSort<T extends Comparable<T>> extends Sort<T> {

    public BogoSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Sorts the given array using BogoSort with natural ordering.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        sort(arr, Comparator.naturalOrder());
    }

    /**
     * Sorts the given array using BogoSort with a custom comparator.
     *
     * @param arr        The array to be sorted.
     * @param comparator Comparator defining the sorting order.
     */
    @Override
    public void sort(T[] arr, Comparator<T> comparator) {
        while (!isSorted(arr, comparator)) {
            addStep(arr);
            shuffle(arr);
        }
        addStep(arr);
    }

    /**
     * Randomly shuffles the elements of the given array.
     *
     * @param arr The array to shuffle.
     */
    private void shuffle(T[] arr) {
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            SortUtil.swap(arr, i, j);
        }
    }

    /**
     * Checks whether the given array is sorted using the provided comparator.
     *
     * @param arr        The array to check.
     * @param comparator Comparator to determine order.
     * @return {@code true} if the array is sorted, otherwise {@code false}.
     */
    private boolean isSorted(T[] arr, Comparator<T> comparator) {
        for (int i = 1; i < arr.length; i++) {
            if (comparator.compare(arr[i], arr[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}
