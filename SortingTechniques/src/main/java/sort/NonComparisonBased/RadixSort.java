package sort.NonComparisonBased;

import sort.Sort;
import java.util.LinkedList;
import java.util.Queue;

public class RadixSort<T extends Number & Comparable<T>> extends Sort<T> {
    private static final int BASE = 10;
    private final Queue<T>[] positiveBuckets;
    private final Queue<T>[] negativeBuckets;

    @SuppressWarnings("unchecked")
    public RadixSort() {
        positiveBuckets = (Queue<T>[]) new Queue[BASE];
        negativeBuckets = (Queue<T>[]) new Queue[BASE];
        for (int i = 0; i < BASE; i++) {
            positiveBuckets[i] = new LinkedList<>();
            negativeBuckets[i] = new LinkedList<>();
        }
    }

    @Override
    public void sort(T[] arr, boolean trackSteps) {
        if (arr.length <= 1) return;

        // Find the maximum absolute value to determine digit count
        long maxValue = 0;
        for (T num : arr) {
            maxValue = Math.max(maxValue, Math.abs(num.longValue()));
        }

        // Sort by each digit place
        for (long place = 1; maxValue / place > 0; place *= BASE) {
            distribute(arr, place);
            collect(arr);
            steps.add(arr.clone());
        }
    }

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
