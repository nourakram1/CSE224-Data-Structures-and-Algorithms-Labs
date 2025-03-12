package sort.comparison.randomized;

import java.util.Random;

import sort.Sort;
import sort.SortUtil;


/**
 * QuickSort is a highly efficient, comparison-based, divide-and-conquer sorting algorithm.
 * It works by selecting a pivot element from the array and partitioning the other elements 
 * into two sub-arrays: elements smaller than the pivot and elements greater than the pivot.
 * The sub-arrays are then sorted recursively.
 * <p>
 * This implementation uses a randomized pivot selection to improve performance on nearly 
 * sorted or identical elements, which helps avoid the worst-case scenario.
 * <p>
 * Time Complexity:
 * - Best Case: O(n log n) when the pivot divides the array evenly.
 * - Average Case: O(n log n) for a random distribution of elements.
 * - Worst Case: O(n²) when the smallest or largest element is always selected as the pivot 
 *   (without randomization).
 * <p>
 * Space Complexity:
 * - O(log n) due to the recursive stack calls.
 * <p>
 * This implementation supports generic types by extending Comparable<T>.
 *
 * @param <T> The type of elements to be sorted, must implement Comparable<T>.
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    private Random random;

    public QuickSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Partitions the array into two halves around a pivot element.
     * Elements smaller than the pivot go to the left, and larger elements go to the
     * right.
     * The pivot is selected randomly to improve performance on already sorted
     * arrays.
     *
     * @param arr   Array to partition
     * @param left  Left index of the partition range
     * @param right Right index of the partition range
     * @return The index of the pivot element after partitioning
     */
    private int partition(T[] arr, int left, int right) {
        // Select a random pivot index within the range and swap with the rightmost element
        int randomIndex = random.nextInt(left, right + 1);
        SortUtil.swap(arr, randomIndex, right);
        addStep(arr);
        
        T pivot = arr[right]; // Pivot is now the rightmost element
        int index = left; // Index to place the next smaller element

        // Traverse the array and move elements smaller than the pivot to the left side
        for (int i = left; i < right; i++) {
            if (arr[i].compareTo(pivot) < 0) {
                SortUtil.swap(arr, index, i);
                addStep(arr);
                index++;
            }
        }
        
        // Place the pivot at its correct position
        SortUtil.swap(arr, index, right);
        addStep(arr);
        return index;
    }

    /**
     * Recursively applies quicksort to partitioned sub arrays.
     * Uses tail recursion to optimize stack usage.
     *
     * @param arr   Array to sort
     * @param left  Left index of the current partition range
     * @param right Right index of the current partition range
     */
    private void quicksort(T[] arr, int left, int right) {
        if (left < right) {
            // Partition the array and get the pivot index
            int index = partition(arr, left, right);

            // Recursively sort the left partition
            quicksort(arr, left, index - 1);
            // Recursively sort the right partition
            quicksort(arr, index + 1, right);
        }
    }

    /**
     * Sorts the input array using the QuickSort algorithm.
     *
     * @param arr Array to sort
     */
    @Override
    public void sort(T[] arr) {
        random = new Random(); // Initialize the random number generator
        quicksort(arr, 0, arr.length - 1);
    }
}