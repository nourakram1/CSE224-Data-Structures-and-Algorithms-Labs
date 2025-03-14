package sort.noncomparison;

import sort.Sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implements the Counting Sort algorithm, an efficient non-comparison-based sorting technique
 * for sorting numbers within a known range.
 *
 * @param <T> The type of elements to be sorted, which must be a Number and extend Comparable.
 */
public class CountingSort<T extends Number & Comparable<? super T>> extends Sort<T> {

    public CountingSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Sorts the given array using Counting Sort.
     * If the range of values exceeds {@code Integer.MAX_VALUE}, it falls back to Arrays.sort().
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        if (arr.length == 0)
            return;

        int minValue = min(arr);
        int maxValue = max(arr);
        long range = (long) maxValue - minValue + 1;

        // Check for range exceeding Integer.MAX_VALUE (practical limitation)
        if (range > Integer.MAX_VALUE) {
            Arrays.sort(arr);
            return;
        }

        // Create the counting array
        int[] count = new int[(int) range];

        // Count occurrences
        for (T num : arr) {
            count[num.intValue() - minValue]++;
        }

        // Reconstruct the sorted array
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                arr[index++] = toT(i + minValue, arr[0]);
            }
        }

        addStep(arr);
    }

    @Override
    public void sort(T[] arr, Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("Counting Sort does not support custom comparators.");
    }

    /**
     * Finds the minimum value in the array.
     *
     * @param arr The input array.
     * @return The minimum integer value in the array.
     */
    private int min(T[] arr) {
        int minVal = arr[0].intValue();
        for (T num : arr) {
            minVal = Math.min(minVal, num.intValue());
        }
        return minVal;
    }

    /**
     * Finds the maximum value in the array.
     *
     * @param arr The input array.
     * @return The maximum integer value in the array.
     */
    private int max(T[] arr) {
        int maxVal = arr[0].intValue();
        for (T num : arr) {
            maxVal = Math.max(maxVal, num.intValue());
        }
        return maxVal;
    }

    /**
     * Converts an integer value back to the appropriate Number type.
     *
     * @param value    The integer value to convert.
     * @param instance An instance of the generic type {@code T} to determine the correct type.
     * @return The converted value as type {@code T}.
     * @throws IllegalStateException If an unsupported type is encountered.
     */
    @SuppressWarnings("unchecked")
    private T toT(int value, T instance) {
        return switch (instance) {
            case Integer ignored -> (T) Integer.valueOf(value);
            case Long ignored -> (T) Long.valueOf(value);
            case Short ignored -> (T) Short.valueOf((short) value);
            case Byte ignored -> (T) Byte.valueOf((byte) value);
            default -> throw new IllegalStateException("Unexpected type: " + instance.getClass());
        };
    }
}