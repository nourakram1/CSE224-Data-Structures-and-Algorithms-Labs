package sort.ComparisonBased.Randomized;

import sort.Sort;

import java.util.Random;

public class BogoSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] arr, boolean trackSteps) {
        while(!isSorted(arr)) {
            shuffle(arr);
            steps.add(arr.clone());
        }
    }

    private void shuffle(T[] arr) {
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    private void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
