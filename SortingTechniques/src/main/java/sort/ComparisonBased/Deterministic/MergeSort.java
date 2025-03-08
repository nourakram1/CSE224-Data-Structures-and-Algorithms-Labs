package sort.ComparisonBased.Deterministic;

import java.lang.reflect.Array;

import sort.Sort;


public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    public MergeSort(boolean showSteps) {
        super(showSteps);
    }
    
    private void merge(T[] arr,int l,int m,int r){
        int n1 = m - l + 1;
        int n2 = r - m;
        T[] L = (T[]) Array.newInstance(arr.getClass().getComponentType(), n1);
        T[] R = (T[]) Array.newInstance(arr.getClass().getComponentType(), n2);
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

        addStep(arr);
    }
    private void divide(T[] arr,int l,int r){
        if(l<r){
            int m = (l+r)/2;
            divide(arr,l,m);
            divide(arr,m+1,r);
            merge(arr,l,m,r);
        }
    }

    @Override
    public void sort(T[] arr) {
        divide(arr, 0, arr.length-1);
    }
}
