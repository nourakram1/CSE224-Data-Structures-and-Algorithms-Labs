package util;

import java.util.*;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ListUtils {

    static <T> ArrayList<T> arrayList(int size) {
        return arrayList(size, (T) null);
    }

    static <T> ArrayList<T> arrayList(int size, T value) {
        return new ArrayList<>(Collections.nCopies(size, value));
    }

    static <T> ArrayList<T> arrayList(int size, Supplier<T> supplier) {
        return Stream.generate(supplier).limit(size).collect(Collectors.toCollection(ArrayList::new));
    }

    static <T> void ensureSize(List<T> list, int desiredSized) {
        ensureSize(list, desiredSized, () -> null);
    }

    static <T> void ensureSize(List<T> list, int desiredSized, Supplier<T> supplier) {
        int actualSize = list.size();

        for (int i = 0; i < desiredSized - actualSize; i++)
            list.add(supplier.get());
    }

    static <T> void nullOut(List<T> list) {
        Collections.fill(list, null);
    }

    static <T> List<T> nonNull(List<T> list) {
        return list.stream().filter(Objects::nonNull).toList();
    }

    static <T> List<T> unique(List<T> list) {
        return list.stream().distinct().toList();
    }

    @SafeVarargs
    static <T> List<T> merge(List<T>... lists) {
        return Arrays.stream(lists).flatMap(List::stream).toList();
    }

    static <T> boolean haveSameElements(List<T> list1, List<T> list2) {
        return list1.size() == list2.size()
                && list1.containsAll(list2)
                && list2.containsAll(list1);
    }
}