package sort.NonComparisonBased;

import sort.Sort;

import java.util.Arrays;

public class CountingSort<T extends Number & Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] arr, boolean trackSteps) {
        if (arr.length == 0) return;

        // Ensure CountingSort is only used for Integer types
        if (!(arr[0] instanceof Integer)) {
            throw new UnsupportedOperationException("CountingSort only supports Integer type.");
        }

        int minValue = min(arr);
        int maxValue = max(arr);
        long range = (long) maxValue - minValue + 1; // Use long to avoid overflow

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
                arr[index++] = convertToT(i + minValue, arr[0]);
            }
        }
        steps.add(arr.clone());
    }

    private int min(T[] arr) {
        int minVal = arr[0].intValue();
        for (T num : arr) {
            minVal = Math.min(minVal, num.intValue());
        }
        return minVal;
    }

    private int max(T[] arr) {
        int maxVal = arr[0].intValue();
        for (T num : arr) {
            maxVal = Math.max(maxVal, num.intValue());
        }
        return maxVal;
    }

    @SuppressWarnings("unchecked")
    private T convertToT(int value, T example) {
        return (T) Integer.valueOf(value);
    }
}
