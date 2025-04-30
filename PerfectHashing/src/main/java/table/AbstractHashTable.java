package table;

import hash.UniversalHash;
import representation.BinaryRepresentable;

import java.util.List;

public abstract class AbstractHashTable<T extends BinaryRepresentable> implements Table<T> {
    protected final int BINARY_REPRESENTATION_LENGTH;
    protected final static int DEFAULT_CAPACITY = 10;
    protected int capacity;
    protected int size;
    protected UniversalHash universalHash;

    protected AbstractHashTable(int binaryRepresentationLength) {
        BINARY_REPRESENTATION_LENGTH = binaryRepresentationLength;
    }

    @Override
    public List<T> insertAll(List<T> values) {
        return values.stream().filter(this::insert).toList();
    }

    @Override
    public List<T> removeAll(List<T> values) {
        return values.stream().filter(this::remove).toList();
    }

    public void ensureUniversalHash() {
        if (universalHash == null) {
            universalHash = new UniversalHash(capacity, BINARY_REPRESENTATION_LENGTH);
        }
    }

    protected int getHashCode(T value) {
        return getHashCode(value, universalHash);
    }

    protected int getHashCode(T value, UniversalHash universalHash) {
        return universalHash.hash(value.getBinaryRepresentation());
    }
}
