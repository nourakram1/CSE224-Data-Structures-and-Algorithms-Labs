package sort.ComparisonBased.Deterministic;

import sort.Sort;


public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    
    public SelectionSort(boolean showSteps) {
        super(showSteps);
    }
    
    @Override
    public void sort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int index = indexOfMin(arr, i);
            swap(arr, i, index);
        }
    }

    private int indexOfMin(T[] arr, int start) {
        int n = arr.length;
        int index = start;
        for (int i = start; i < n; i++) {
            if (arr[i].compareTo(arr[index]) < 0) index = i;
        }
        return index;
    }
}
