package util;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtils {
    public static <T> ArrayList<T> arrayList(int size) {
        return arrayList(size, (T) null);
    }

    public static <T> ArrayList<T> arrayList(int size, T value) {
        return new ArrayList<>(Collections.nCopies(size, value));
    }

    public static <T> ArrayList<T> arrayList(int size, Supplier<T> supplier) {
        return Stream.generate(supplier).limit(size).collect(Collectors.toCollection(ArrayList::new));
    }

    public static <T> void ensureSize(List<T> list, int desiredSized) {
        ensureSize(list, desiredSized, null);
    }

    public static <T> void ensureSize(List<T> list, int desiredSized, T padding) {
        if (desiredSized > list.size()) {
            list.addAll(Collections.nCopies(desiredSized, padding));
        }
    }

    public static <T> void replaceWith(List<T> list, List<T> with) {
        list.clear();
        list.addAll(with);
    }

    public static <T> List<T> nonNull(List<T> list) {
        return list.stream().filter(Objects::nonNull).toList();
    }

    public static <T> void nullOut(List<T> list) {
        Collections.fill(list, null);
    }
}
