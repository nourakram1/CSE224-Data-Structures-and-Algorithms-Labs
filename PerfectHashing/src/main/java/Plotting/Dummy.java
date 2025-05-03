package Plotting;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import representation.BinaryRepresentable;

public class Dummy implements BinaryRepresentable {
    private final int id;
    private final int length;

    public Dummy(int id, int length) {
        this.id = id;
        this.length = length;
    }

    @Override
    public String getBinaryRepresentation() {
        return String.format("%" + length + "s", Integer.toBinaryString(id)).replace(' ', '0');
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Dummy && ((Dummy) o).id == id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public static List<Dummy> createDummies(int n, int binlen) {
        return IntStream.range(0, n)
                .mapToObj(i -> new Dummy(i, binlen))
                .collect(Collectors.toList());
    }
}
