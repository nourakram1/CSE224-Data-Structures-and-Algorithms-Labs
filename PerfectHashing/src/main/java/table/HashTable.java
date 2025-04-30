package table;

import hash.UniversalHash;
import representation.BinaryRepresentable;
import util.ListUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HashTable<T extends BinaryRepresentable> extends AbstractHashTable<T> {
    private static final int DEFAULT_CAPACITY = 4;
    private final List<T> elements;

    public HashTable(int binaryRepresentationLength) {
        this(DEFAULT_CAPACITY, binaryRepresentationLength);
    }

    public HashTable(int initialCapacity, int binaryRepresentationLength) {
        super(initialCapacity, binaryRepresentationLength);
        elements = ListUtils.arrayList(initialCapacity);
    }

    @Override
    public boolean insert(T value) {
        Objects.requireNonNull(value);
        boolean inserted = insert(universalHash.getHashCode(value), value);
        if (inserted) size++;
        return inserted;
    }

    @Override
    public boolean remove(T value) {
        Objects.requireNonNull(value);
        boolean removed = remove(universalHash.getHashCode(value));
        if (removed) size--;
        return removed;
    }

    @Override
    public boolean contains(T value) {
        Objects.requireNonNull(value);
        return elements.get(universalHash.getHashCode(value)) != null;
    }

    @Override
    public List<T> getElements() {
        return Collections.unmodifiableList(elements);
    }

    private boolean remove(int index) {
        T oldValue = elements.set(index, null);
        return oldValue != null;
    }

    private boolean insert(int index, T value) {
        T t = elements.get(index);
        if (value.equals(t)) return false;

        if (size * size > capacity)
            grow();

        if (t != null)
            rehash();

        elements.set(universalHash.getHashCode(value), value);
        return true;
    }

    private void grow() {
        capacity *= 2;
        ListUtils.ensureSize(elements, capacity);
        rehash();
    }

    private void rehash() {
        UniversalHash<T> hash;
        List<T> elements = ListUtils.arrayList(capacity);
        boolean collision;

        do {
            hash = new UniversalHash<>(capacity, BINARY_REPRESENTATION_LENGTH);
            collision = false;

            for (T element : ListUtils.nonNull(elements)) {
                int hashCode = hash.getHashCode(element);
                if (elements.get(hashCode) != null) {
                    collision = true;
                    ListUtils.nullOut(elements);
                    break;
                }
                elements.set(hashCode, element);
            }
        } while (collision);

        universalHash = hash;
        ListUtils.replaceWith(this.elements, elements);
    }
}