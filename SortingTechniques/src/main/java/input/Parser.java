package input;

import java.util.Arrays;

public class Parser {

    public static Integer[] parseIntegers(String values,
                                          String delimiter) {
        return Arrays.stream(values.split(delimiter))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }
}
