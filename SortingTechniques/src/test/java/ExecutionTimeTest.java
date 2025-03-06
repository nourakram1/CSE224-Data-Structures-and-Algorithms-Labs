import org.junit.jupiter.api.TestInstance;

import org.junit.jupiter.api.*;
import sort.SortType;
import sort.Sorter;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExecutionTimeTest {

    private static final int[] ARRAY_SIZES = {10, 100, 1_000, 5_000, 10_000, 50_000, 100_000, 1_000_000, 10_000_000};

    @Test
    void testSortingExecutionTimes() {
        for (int size : ARRAY_SIZES) {
            Integer[] randomArray = generateRandomArray(size);
            System.out.println("\n=== Sorting " + size + " elements ===");

            for (SortType algorithm : SortType.values()) {
                //
                    if(algorithm != SortType.COUNTING_SORT && algorithm != SortType.RADIX_SORT) continue;
                //
                Integer[] arrayCopy = randomArray.clone();
                long startTime = System.nanoTime();
                Sorter.sort(arrayCopy, algorithm);
                long endTime = System.nanoTime();

                long durationMs = (endTime - startTime) / 1_000_000;
                System.out.printf("%s took %d ms%n", algorithm.getName(), durationMs);
                Assertions.assertTrue(isSorted(arrayCopy), algorithm.getName() + " did not sort correctly.");
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
