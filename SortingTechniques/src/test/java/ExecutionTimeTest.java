import org.junit.jupiter.api.TestInstance;
import sort.ComparisonBased.Deterministic.BubbleSort;
import sort.ComparisonBased.Deterministic.InsertionSort;
import sort.ComparisonBased.Deterministic.MergeSort;
import sort.ComparisonBased.Deterministic.SelectionSort;
import sort.ComparisonBased.Randomized.QuickSort;
import sort.NonComparisonBased.CountingSort;
import sort.NonComparisonBased.RadixSort;
import sort.Sort;

import java.util.List;

import org.junit.jupiter.api.*;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExecutionTimeTest {

    private static final int[] ARRAY_SIZES = {10, 100, 1_000, 5_000, 10_000, 50_000, 100_000, 1_000_000, 10_000_000};
    private List<Sort<Integer>> sortingAlgorithms;

    @BeforeAll
    void setup() {
        sortingAlgorithms = List.of(
//                new BubbleSort<>(),
//                new InsertionSort<>(),
//                new SelectionSort<>(),
//                new QuickSort<>(),
//                new MergeSort<>(),
                new RadixSort<>(),
                new CountingSort<>()
        );
    }

    @Test
    void testSortingExecutionTimes() {
        for (int size : ARRAY_SIZES) {
            Integer[] randomArray = generateRandomArray(size);
            System.out.println("\n=== Sorting " + size + " elements ===");

            for (Sort<Integer> algorithm : sortingAlgorithms) {
                Integer[] arrayCopy = randomArray.clone();
                long startTime = System.nanoTime();
                algorithm.sort(arrayCopy);
                long endTime = System.nanoTime();

                long durationMs = (endTime - startTime) / 1_000_000;
                System.out.printf("%s took %d ms%n", algorithm.getClass().getSimpleName(), durationMs);
                Assertions.assertTrue(isSorted(arrayCopy), algorithm.getClass().getSimpleName() + " did not sort correctly.");
            }
        }
    }

    private Integer[] generateRandomArray(int size) {
        Random rand = new Random();
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000);
        }
        return array;
    }

    private boolean isSorted(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) return false;
        }
        return true;
    }
}
