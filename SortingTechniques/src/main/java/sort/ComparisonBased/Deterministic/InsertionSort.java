package sort.ComparisonBased.Deterministic;

import sort.Sort;


/**
 * InsertionSort is a simple, comparison-based sorting algorithm that builds the final sorted array 
 * one element at a time by repeatedly picking the next element and inserting it into its correct 
 * position within the sorted portion of the array.
 * <p>
 * The algorithm is efficient for small datasets and partially sorted arrays but becomes slow on 
 * large datasets due to its quadratic time complexity.
 * <p>
 * Time Complexity:
 * - Best Case: O(n) when the array is already sorted.
 * - Average Case: O(n²) due to the nested loop comparisons and shifts.
 * - Worst Case: O(n²) when the array is sorted in reverse order.
 * <p>
 * Space Complexity:
 * - O(1) since sorting is performed in-place without additional memory usage.
 * <p>
 * This implementation supports generic types by extending Comparable<T>.
 *
 * @param <T> The type of elements to be sorted, must implement Comparable<T>.
 */
public class InsertionSort<T extends Comparable<T>> extends Sort<T> {

   public InsertionSort(boolean showSteps) {
      super(showSteps);
   }

   /**
    * Sorts the input array using the Insertion Sort algorithm.
    *
    * @param arr Array to sort
    */
   @Override
   public void sort(T[] arr) {
      // Iterate through the array starting from the second element
      for (int i = 1; i < arr.length; i++) {
         T key = arr[i];      // Store the current element to be compared
         int j = i - 1;       // Initialize the index of the previous element

         // Shift elements of arr[0...i-1] that are greater than key
         // to one position ahead of their current position
         while (j >= 0 && key.compareTo(arr[j]) < 0) {
            arr[j + 1] = arr[j--];  // Move element to the right
         }


         // Insert the key into its correct position
         arr[j + 1] = key;

         addStep(arr);
      }
   }
}
