package sort;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SortType {
    BUBBLE_SORT (1, "Bubble sort"),
    MERGE_SORT  (2, "Merge sort"),
    QUICKSORT   (3, "Quicksort"),
    RADIX_SORT  (4, "Radix sort"),
    BOGO_SORT   (5, "Bogo Sort");

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
