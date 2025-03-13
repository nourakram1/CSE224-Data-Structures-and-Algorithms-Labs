package sort.noncomparison;

import sort.Sort;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements the Radix Sort algorithm, a non-comparison-based sorting method that sorts numbers
 * digit by digit using a positional system.
 * <p>
 * Radix Sort works by distributing numbers into buckets based on individual digits, processing
 * them from the least significant digit to the most significant digit. This implementation
 * supports sorting both positive and negative numbers.
 * </p>
 *
 * @param <T> The type of elements to be sorted, which must be a Number and extend Comparable.
 */
public class RadixSort<T extends Number & Comparable<T>> extends Sort<T> {
    private static final int BASE = 10; // The base used for digit extraction (decimal system)
    private final Queue<T>[] positiveBuckets; // Buckets for positive numbers
    private final Queue<T>[] negativeBuckets; // Buckets for negative numbers

    /**
     * Constructs a RadixSort instance.
     *
     * @param showSteps If true, the algorithm logs each step of the sorting process.
     */
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
     * <p>
     * The algorithm sorts numbers by processing each digit from the least significant to the most significant.
     * It handles negative numbers separately to maintain correct ordering.
     * </p>
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        if (arr.length == 0)
            return;

        long maxValue = 0;
        for (T num : arr) {
            maxValue = Math.max(maxValue, Math.abs(num.longValue()));
        }

        for (long place = 1; maxValue / place > 0; place *= BASE) {
            distribute(arr, place);
            collect(arr);
            addStep(arr);
        }
    }

    /**
     * Sorts the array using Radix Sort and a custom comparator.
     *
     * @param arr       The array to be sorted.
     * @param comparator The comparator defining the sort order.
     */
    @Override
    public void sort(T[] arr, Comparator<T> comparator) {
        throw new UnsupportedOperationException("RadixSort does not support custom comparators.");
    }

    /**
     * Distributes elements from the array into buckets based on the current digit place.
     *
     * @param arr   The array of numbers to distribute.
     * @param place The digit place being processed (1s, 10s, 100s, etc.).
     */
    private void distribute(T[] arr, long place) {
        for (T num : arr) {
            long digit = Math.abs(num.longValue() / place) % BASE; // Extract the digit at 'place'
            if (num.longValue() < 0) {
                negativeBuckets[(int) digit].add(num);
            } else {
                positiveBuckets[(int) digit].add(num);
            }
        }
    }

    /**
     * Collects elements from the buckets and places them back into the array.
     * <p>
     * Negative numbers are collected first, in reverse order (most negative to least negative).
     * Then, positive numbers are collected in normal order.
     * </p>
     *
     * @param arr The array to store the sorted elements.
     */
    private void collect(T[] arr) {
        int index = 0;

        // Collect from negative buckets in reverse order to maintain descending order
        for (int i = BASE - 1; i >= 0; i--) {
            while (!negativeBuckets[i].isEmpty()) {
                arr[index++] = negativeBuckets[i].poll();
            }
        }

        // Collect from positive buckets in ascending order
        for (int i = 0; i < BASE; i++) {
            while (!positiveBuckets[i].isEmpty()) {
                arr[index++] = positiveBuckets[i].poll();
            }
        }
    }
}
