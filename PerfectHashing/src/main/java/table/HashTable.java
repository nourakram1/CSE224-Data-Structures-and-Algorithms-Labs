package table;

import hash.UniversalHash;
import representation.BinaryRepresentable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HashTable<T extends BinaryRepresentable> extends AbstractHashTable<T> {
    private final List<T> elements;

    public HashTable(int binaryRepresentationLength) {
        this(DEFAULT_CAPACITY, binaryRepresentationLength);
    }

    public HashTable(int initialCapacity, int binaryRepresentationLength) {
        super(binaryRepresentationLength);
        capacity = initialCapacity;
        elements = new ArrayList<>(Collections.nCopies(initialCapacity, null));
    }

    @Override
    public boolean insert(T value) {
        ensureUniversalHash();
        if (size * size > capacity)
            grow();
        boolean inserted = set(getHashCode(value), value);
        if (inserted) size++;
        return inserted;
    }

    @Override
    public boolean remove(T value) {
        boolean removed = set(getHashCode(value), null);
        if (removed) size--;
        return removed;
    }

    @Override
    public boolean contains(T value) {
        return elements.get(getHashCode(value)) != null;
    }

    @Override
    public List<T> getElements() {
        return Collections.unmodifiableList(elements);
    }

    private boolean set(int index, T value) {
        T oldValue = elements.get(index);
        if (oldValue != null) {
            if (!oldValue.equals(value))
                rehash(capacity);
            else return false;
        }
        elements.set(index, value);
        return true;
    }

    private void rehash(int capacity) {
        UniversalHash newUniversalHash = new UniversalHash(capacity, BINARY_REPRESENTATION_LENGTH);
        List<T> newElements = new ArrayList<>(Collections.nCopies(capacity, null));
        for (int i = 0; i < size; i++) {
            T t = elements.get(i);
            int hashCode = getHashCode(t, newUniversalHash);
            if (newElements.get(hashCode) != null) {
                i = -1;
                Collections.fill(newElements, null);
                newUniversalHash = new UniversalHash(capacity, BINARY_REPRESENTATION_LENGTH);
            }
            else {
                newElements.set(hashCode, t);
            }
        }
        universalHash = newUniversalHash;
    }

    private void grow() {
        updateCapacity(size * size);
        rehash(capacity);
    }

    private void updateCapacity(int newCapacity) {
        capacity = newCapacity;
        List<T> newElements = new ArrayList<>(Collections.nCopies(capacity, null));
        Collections.copy(elements, newElements);
        elements.clear();
        elements.addAll(newElements);
    }
}