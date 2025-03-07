package sort.ComparisonBased.Randomized;

import sort.Sort;

import java.util.Random;


public class BogoSort<T extends Comparable<T>> extends Sort<T> {
    
    public BogoSort(boolean showSteps) {
        super(showSteps);
    }

    @Override
    public void sort(T[] arr) {
        while(!isSorted(arr)) {
            shuffle(arr);
            addStep(arr);
        }
    }

    private void shuffle(T[] arr) {
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    private boolean isSorted(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}
