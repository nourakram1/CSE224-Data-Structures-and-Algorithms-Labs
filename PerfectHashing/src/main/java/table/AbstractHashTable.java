package table;

import hash.UniversalHash;
import representation.BinaryRepresentable;

import java.util.List;

public abstract class AbstractHashTable<T extends BinaryRepresentable> implements Table<T> {
    protected final int BINARY_REPRESENTATION_LENGTH;
    protected int capacity;
    protected int size;
    protected UniversalHash<T> universalHash;

    protected AbstractHashTable(int initialCapacity, int binaryRepresentationLength) {
        BINARY_REPRESENTATION_LENGTH = binaryRepresentationLength;
        capacity = initialCapacity;
        universalHash = new UniversalHash<>(initialCapacity, binaryRepresentationLength);
    }

    @Override
    public List<T> insertAll(List<T> values) {
        return values.stream().filter(this::insert).toList();
    }

    @Override
    public List<T> removeAll(List<T> values) {
        return values.stream().filter(this::remove).toList();
    }
}
