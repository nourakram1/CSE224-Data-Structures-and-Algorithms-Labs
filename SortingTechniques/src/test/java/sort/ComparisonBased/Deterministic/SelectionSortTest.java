package sort.ComparisonBased.Deterministic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    private SelectionSort<Integer> selectionSort = new SelectionSort<>(false);

    @Test
    void testPositiveNonSortedArray() {
        var arr = new Integer[]{5, 9, 8, 7, 9};
        selectionSort.sort(arr);
        assertArrayEquals(new Integer[]{5, 7, 8, 9, 9}, arr);
    }

    @Test
    void testNegativeNonSortedArray() {
        var arr = new Integer[]{-5, -9, -8, -7, -9};
        selectionSort.sort(arr);
        assertArrayEquals(new Integer[]{-9, -9, -8, -7, -5}, arr);
    }

    @Test
    void testNonSortedArray() {
        var arr = new Integer[]{14, 0, -3, -5, 9, 8, -7, 9};
        selectionSort.sort(arr);
        assertArrayEquals(new Integer[]{-7, -5, -3, 0, 8, 9, 9, 14}, arr);
    }

    @Test
    void testEmptyArray() {
        var arr = new Integer[]{};
        selectionSort.sort(arr);
        assertArrayEquals(new Integer[]{}, arr);
    }

    @Test
    void testSortedArray() {
        var arr = new Integer[]{1, 2, 2, 5, 8, 12};
        selectionSort.sort(arr);
        assertArrayEquals(new Integer[]{1, 2, 2, 5, 8, 12}, arr);

    }
}