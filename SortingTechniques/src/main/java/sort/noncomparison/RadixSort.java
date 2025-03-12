package sort.noncomparison;

import java.util.LinkedList;
import java.util.Queue;

import sort.Sort;

/**
 * Implements the Radix Sort algorithm, a non-comparison-based sorting method that sorts numbers
 * digit by digit using a positional system.
 *
 * @param <T> The type of elements to be sorted, which must be a Number and extend Comparable.
 */
public class RadixSort<T extends Number & Comparable<T>> extends Sort<T> {
    private static final int BASE = 10;
    private final Queue<T>[] positiveBuckets;
    private final Queue<T>[] negativeBuckets;

    @SuppressWarnings("unchecked")
    public RadixSort(boolean showSteps) {
        super(showSteps);
        positiveBuckets = (Queue<T>[]) new Queue[BASE];
        negativeBuckets = (Queue<T>[]) new Queue[BASE];
        for (int i = 0; i < BASE; i++) {
            positiveBuckets[i] = new LinkedList<>();
            negativeBuckets[i] = new LinkedList<>();
        }
    }

    /**
     * Sorts the given array using Radix Sort.
     * The algorithm sorts numbers by processing each digit from the least significant to the most significant.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        if (arr.length <= 1)
            return;

        // Find the maximum absolute value to determine digit count
        long maxValue = 0;
        for (T num : arr) {
            maxValue = Math.max(maxValue, Math.abs(num.longValue()));
        }

        // Sort by each digit place
        for (long place = 1; maxValue / place > 0; place *= BASE) {
            distribute(arr, place);
            collect(arr);
            if (this.showSteps)
                addStep(arr);
        }
    }

    /**
     * Distributes the numbers into buckets based on the current digit place.
     *
     * @param arr   The array of numbers to distribute.
     * @param place The digit place being processed.
     */
    private void distribute(T[] arr, long place) {
        for (T element : arr) {
            int digit = (int) ((Math.abs(element.longValue()) / place) % BASE);
            if (element.longValue() < 0) {
                negativeBuckets[digit].add(element);
            } else {
                positiveBuckets[digit].add(element);
            }
        }
    }

    /**
     * Collects the numbers from the buckets and places them back into the array.
     * Negative numbers are collected in reverse order, while positive numbers are collected in normal order.
     *
     * @param arr The array to store the sorted elements.
     */
    private void collect(T[] arr) {
        int index = 0;

        // Collect from negative buckets in reverse order
        for (int i = BASE - 1; i >= 0; i--) {
            while (!negativeBuckets[i].isEmpty()) {
                arr[index++] = negativeBuckets[i].poll();
            }
        }

        // Collect from positive buckets in normal order
        for (int i = 0; i < BASE; i++) {
            while (!positiveBuckets[i].isEmpty()) {
                arr[index++] = positiveBuckets[i].poll();
            }
        }
    }
}