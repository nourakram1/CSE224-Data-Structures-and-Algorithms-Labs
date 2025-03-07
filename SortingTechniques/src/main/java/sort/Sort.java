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
     * Constructs a new {@code Sort} instance.
     */
    public Sort() {
        steps = new ArrayList<>();
    }

    /**
     * Sorts the specified array.
     *
     * <p>The implementation of this method should perform the sorting of the given array
     * and add each intermediate state of the array to the {@code steps} list.</p>
     *
     * @param arr        the array to be sorted.
     * @param trackSteps
     */
    abstract public void sort(T[] arr, boolean trackSteps);

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
}
