package sort;

import lombok.Getter;
import java.util.Arrays;


@Getter
public enum SortType {
    BUBBLE_SORT     (1, "Bubble sort"),
    INSERTION_SORT  (2, "Insertion sort"),
    SELECTION_SORT  (3, "Selection sort"),
    MERGE_SORT      (4, "Merge sort"),
    QUICKSORT       (5, "Quicksort"),
    RADIX_SORT      (6, "Radix sort"),
    COUNTING_SORT   (7, "Counting Sort"),
    BOGOSORT        (8, "Bogosort");


    private final int code;
    private final String name;

    SortType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SortType getByCode(int code) {
        return  Arrays.stream(SortType.values())
                .filter(sortType -> sortType.code == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
