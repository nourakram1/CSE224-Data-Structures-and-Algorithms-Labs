package table;

import java.util.List;

import hash.UniversalHash;
import representation.BinaryRepresentable;
import util.MathUtils;

public abstract class AbstractHashtable<T extends BinaryRepresentable>
        implements Table<T> {

    protected static final int MIN_CAPACITY = 2;
    protected int BINARY_REPRESENTATION_LENGTH;
    protected int capacity;
    protected long size;
    protected UniversalHash<T> universalHash;

    public AbstractHashtable(int capacity) {
        setCapacity(capacity);
    }

    @Override
    public boolean insert(T value) {
        ensureUniversalHash(value.getBinaryRepresentation().length());
        boolean inserted = insert(universalHash.getHashCode(value), value);
        if (inserted) size++;
        return inserted;
    }

    @Override
    public boolean remove(T value) {
        ensureUniversalHash(value.getBinaryRepresentation().length());
        boolean removed = remove(universalHash.getHashCode(value), value);
        if (removed) size--;
        return removed;
    }

    @Override
    public boolean contains(T value) {
        ensureUniversalHash(value.getBinaryRepresentation().length());
        return contains(universalHash.getHashCode(value), value);
    }

    @Override
    public List<T> insertAll(List<T> values) {
        ensureCapacity(values.size());
        return values.stream().filter(this::insert).toList();
    }

    @Override
    public List<T> removeAll(List<T> values) {
        return values.stream().filter(this::remove).toList();
    }

    protected abstract boolean insert(int hashCode, T value);

    protected abstract boolean remove(int hashCode, T value);

    protected abstract boolean contains(int hashCode,T value);

    protected void ensureUniversalHash(int binaryRepresentationLength) {
        if (universalHash == null) {
            BINARY_REPRESENTATION_LENGTH = binaryRepresentationLength;
            this.universalHash = getUniversalHash();
        }
    }

    protected UniversalHash<T> getUniversalHash() {
        return new UniversalHash<>(capacity, BINARY_REPRESENTATION_LENGTH);
    }

    protected void setCapacity(int capacity) {
        if (capacity < MIN_CAPACITY)
            throw new IllegalArgumentException(String.format("capacity < %d", MIN_CAPACITY));

        this.capacity = MathUtils.nextPowerOfTwo(capacity);
    }

    abstract protected void ensureCapacity(int capacity);
}