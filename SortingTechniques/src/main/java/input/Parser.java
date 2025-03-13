package input;

import sort.lang.ComparableNumber;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * Utility class for parsing delimited strings into arrays of values.
 */
public class Parser {

    /**
     * Parses a delimited string into an array of values of type T.
     *
     * <p>This method splits the input string using the provided delimiter, trims each substring,
     * filters out any empty substrings, and applies the converter function to convert each substring
     * into an instance of type T. The resulting values are then collected into an array, which is created
     * using the provided array generator function.</p>
     *
     * @param values the string containing delimited values
     * @param delimiter the delimiter used to split the values
     * @param converter a function that converts a trimmed string into an instance of T
     * @param generator an array generator function that creates a new array of type T with the given length
     * @param <T> the type of the elements in the resulting array, which must implement {@link Comparable}
     * @return an array of parsed values of type T
     */
    public static <T extends Comparable<? super T>> T[]
    parseValues(String values,
                String delimiter,
                Function<String, T> converter,
                IntFunction<T[]> generator) {
        return Arrays.stream(values.split(delimiter))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(converter)
                .toArray(generator);
    }

    /**
     * Parses a delimited string of numerical values into an array of {@link ComparableNumber} objects.
     * <p>
     * This method splits the input string using the specified delimiter, trims each segment,
     * and attempts to parse it into a {@link Number}. If parsing fails, an {@link IllegalArgumentException}
     * is thrown.
     * </p>
     *
     * @param values    the string containing numerical values separated by the given delimiter
     * @param delimiter the delimiter used to split the input string
     * @return an array of {@code ComparableNumber} objects representing the parsed numbers
     * @throws IllegalArgumentException if any value cannot be parsed into a {@code Number}
     */
    public static ComparableNumber[]
    parseNumbers(String values,
                 String delimiter) {
        return Arrays.stream(values.split(delimiter))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    try {
                        return new ComparableNumber(NumberFormat.getInstance().parse(s));
                    }
                    catch (ParseException e) {
                        System.out.println(e.getMessage());
                        throw new IllegalArgumentException();
                    }
                })
                .toArray(ComparableNumber[]::new);
    }
}
