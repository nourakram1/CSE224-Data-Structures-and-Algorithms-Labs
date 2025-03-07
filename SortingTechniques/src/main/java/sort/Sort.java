package sort;

import java.util.List;
import java.util.ArrayList;

/**
 * An abstract class that provides a framework for sorting arrays.
 *
 * <p>This class is designed for sorting an array of elements that implement the
 * {@code Comparable} interface. During the sorting process, the algorithm should
 * add each intermediate state of the array to the private {@code steps} list.
 * These intermediate arrays can later be retrieved using the {@link #getIntermediateArrays()}
 * method.</p>
 *
 * @param <T> the type of elements to be sorted, which must implement {@code Comparable<T>}.
 */
public abstract class Sort<T extends Comparable<T>> {

    /**
     * A list that stores the intermediate states of the array during sorting.
     * Each element of the list represents an intermediate version of the array
     * as it was being sorted.
     */
    protected final List<T[]> steps;

    /**
     * boolean that entered by client to determine whether steps
     * will be added or not 
     */
    protected final boolean showSteps;

    /**
     * Constructs a new {@code Sort} instance.
     */
    public Sort(boolean showSteps) {
        this.showSteps = showSteps;
        if(showSteps)
            steps = new ArrayList<>();
        else
            steps = null;
    }

    /**
     * Sorts the specified array.
     *
     * <p>The implementation of this method should perform the sorting of the given array
     * and add each intermediate state of the array to the {@code steps} list.</p>
     *
     * @param arr the array to be sorted.
     */
    abstract public void sort(T[] arr);

    /**
     * Returns the list of intermediate arrays recorded during the sorting process.
     *
     * <p>Each element in the returned list represents a state of the array at a certain
     * step during the sorting process.</p>
     *
     * @return a {@code List} containing the intermediate states of the array.
     */
    public List<T[]> getIntermediateArrays() {
        return steps;
    }

    /**
     * stores the current step in sorting algorithm
     * @param arr intermediate arr
     */
    protected void addStep(T[] arr) {
        if(showSteps) {
            steps.add(arr.clone());
        }
    }

    protected void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
