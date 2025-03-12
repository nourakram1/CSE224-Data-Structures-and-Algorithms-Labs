package sort.ComparisonBased.Deterministic;

import sort.Sort;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HeapSort <T extends Comparable<T>> extends Sort<T> {

    public HeapSort(boolean showSteps) {
        super(showSteps);
    }
    
    @Override
    public void sort(T[] arr) {
        PriorityQueue<T> pq = new PriorityQueue<>();

        pq.addAll(Arrays.asList(arr));

        for (int i = 0; i < arr.length; i++) {
            arr[i] = pq.poll();
        }
    }
}
