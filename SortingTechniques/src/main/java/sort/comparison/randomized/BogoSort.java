package sort.comparison.randomized;

import java.util.Random;

import sort.Sort;
import sort.SortUtil;

/**
 * Implementation of the highly inefficient BogoSort algorithm.
 * BogoSort repeatedly shuffles the array until it happens to be sorted.
 *
 * @param <T> The type of elements to be sorted, which must extend Comparable.
 */
public class BogoSort<T extends Comparable<T>> extends Sort<T> {

    public BogoSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Sorts the given array using BogoSort.
     * The algorithm repeatedly shuffles the array until it is sorted.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
            addStep(arr);
        }
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
     * Checks whether the given array is sorted in ascending order.
     *
     * @param arr The array to check.
     * @return {@code true} if the array is sorted, otherwise {@code false}.
     */
    private boolean isSorted(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}