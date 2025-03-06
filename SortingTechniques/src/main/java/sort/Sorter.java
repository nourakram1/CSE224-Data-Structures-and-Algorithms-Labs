/**
 * The {@code Sorter} class provides an API for sorting arrays of elements that implement {@link Comparable}.
 * It offers multiple sorting algorithms that can be invoked using their specific method names,
 * or through a generic {@code sort} method by specifying the sorting type.
 *
 * <p>This API supports sorting by:</p>
 * <ul>
 *   <li>Direct method calls like {@code mergeSort(arr)}</li>
 *   <li>String-based sorting types like {@code sort(arr, "MERGE_SORT")}</li>
 *   <li>Integer-based sorting codes like {@code sort(arr, 1)}</li>
 * </ul>
 *
 */
package sort;

import java.util.EnumSet;

public class Sorter {
    /**
     * Sorts the array using Merge Sort.
     * @param arr the array to be sorted
     */
    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        sort(arr, SortType.MERGE_SORT);
    }

    /**
     * Sorts the array using Bubble Sort.
     * @param arr the array to be sorted
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        sort(arr, SortType.BUBBLE_SORT);
    }

    /**
     * Sorts the array using Insertion Sort.
     * @param arr the array to be sorted
     */
    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        sort(arr, SortType.INSERTION_SORT);
    }

    /**
     * Sorts the array using Selection Sort.
     * @param arr the array to be sorted
     */
    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        sort(arr, SortType.SELECTION_SORT);
    }

    /**
     * Sorts the array using Bogo Sort (inefficient, for demonstration purposes).
     * @param arr the array to be sorted
     */
    public static <T extends Comparable<T>> void bogoSort(T[] arr) {
        sort(arr, SortType.BOGO_SORT);
    }

    /**
     * Sorts the array using Radix Sort.
     * @param arr the array to be sorted
     */
    public static <T extends Comparable<T>> void radixSort(T[] arr) {
        sort(arr, SortType.RADIX_SORT);
    }

    /**
     * Sorts the array using Counting Sort.
     * @param arr the array to be sorted
     */
    public static <T extends Comparable<T>> void countingSort(T[] arr) {
        sort(arr, SortType.COUNTING_SORT);
    }

    /**
     * Sorts the array using Quick Sort.
     * @param arr the array to be sorted
     */
    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        sort(arr, SortType.QUICK_SORT);
    }

    /**
     * Sorts the given array using the specified {@code SortType}.
     * @param arr the array to be sorted
     * @param sortType the sorting algorithm to use
     * @param <T> the type of elements in the array
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void sort(T[] arr, SortType sortType) {
        Sort<T> sorter = (Sort<T>) SortFactory.getSort(sortType);
        sorter.sort(arr);
    }

    /**
     * Sorts the given array based on a string representation of a {@code SortType}.
     * @param arr the array to be sorted
     * @param sortType the sorting algorithm as a string
     * @param <T> the type of elements in the array
     * @throws IllegalArgumentException if the sort type string is invalid
     */
    public static <T extends Comparable<T>> void sort(T[] arr, String sortType) {
        sort(arr, getSortTypeFromString(sortType));
    }

    /**
     * Sorts the given array based on an integer representation of a {@code SortType}.
     * @param arr the array to be sorted
     * @param sortType the sorting algorithm as an integer
     * @param <T> the type of elements in the array
     * @throws IllegalArgumentException if the integer code is invalid
     */
    public static <T extends Comparable<T>> void sort(T[] arr, int sortType) {
        sort(arr, getSortTypeFromInt(sortType));
    }

    /**
     * Converts a string into a valid {@code SortType}.
     * @param sortType the sorting algorithm as a string
     * @return the corresponding {@code SortType}
     * @throws IllegalArgumentException if the string does not match a valid sort type
     */
    private static SortType getSortTypeFromString(String sortType) {
        try {
            return SortType.valueOf(sortType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sort type: " + sortType, e);
        }
    }

    /**
     * Converts an integer into a valid {@code SortType}.
     * @param sortType the sorting algorithm as an integer
     * @return the corresponding {@code SortType}
     * @throws IllegalArgumentException if the integer does not match a valid sort type
     */
    private static SortType getSortTypeFromInt(int sortType) {
        return EnumSet.allOf(SortType.class).stream()
                .filter(type -> type.getCode() == sortType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid sort type: " + sortType));
    }
}
