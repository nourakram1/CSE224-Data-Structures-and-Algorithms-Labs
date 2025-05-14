package table;

import hash.UniversalHash;
import representation.BinaryRepresentable;
import util.ListUtils;

import java.util.List;

public class Hashtable<T extends BinaryRepresentable>
        extends AbstractHashtable<T> {

    private static final int DEFAULT_CAPACITY = 4;
    private final List<T> elements;

    public Hashtable() {
        this(DEFAULT_CAPACITY);
    }

    public Hashtable(int initialCapacity) {
        super(initialCapacity);
        elements = ListUtils.arrayList(capacity);
    }

    @Override
    protected boolean insert(int hashCode, T value) {
        if (contains(hashCode, value))
            return false;

        if (size * size >= capacity)
            grow();

        while (collision(value))
            rehash();

        elements.set(universalHash.getHashCode(value), value);
        return true;
    }

    @Override
    protected boolean remove(int hashCode, T value) {
        if (!contains(hashCode, value))
            return false;

        elements.set(hashCode, null);
        return true;
    }

    @Override
    protected boolean contains(int hashCode, T value) {
        return value.equals(elements.get(hashCode));
    }

    @Override
    protected void ensureCapacity(int capacity) {
        if (this.capacity < capacity) {
            setCapacity(capacity);
            ListUtils.ensureSize(elements, this.capacity);
            rehash();
        }
    }

    @Override
    public List<T> getElements() {
        return ListUtils.nonNull(elements);
    }

    protected void clear() {
        elements.clear();
        ListUtils.ensureSize(elements, DEFAULT_CAPACITY);
        this.capacity = DEFAULT_CAPACITY;
        this.universalHash = null;
    }

    protected void grow() {
        capacity *= 2;
        ListUtils.ensureSize(elements, capacity);
        rehash();
    }

    protected void rehash() {
        if (size == 0)
            return;

        List<T> elements = getElements();
        UniversalHash<T> universalHash = getUniversalHash();
        ListUtils.nullOut(this.elements);

        boolean hashed = hash(elements, this.elements, universalHash);

        while (!hashed) {
            universalHash = getUniversalHash();
            ListUtils.nullOut(this.elements);
            hashed = hash(elements, this.elements, universalHash);
        }

        this.universalHash = universalHash;
    }

    protected boolean hash(List<T> elements, List<T> into, UniversalHash<T> universalHash) {
        if (into.size() != capacity)
            throw new IllegalArgumentException("into.size() != capacity");

        for (T element : elements) {
            int hashCode = universalHash.getHashCode(element);

            if (into.get(hashCode) != null)
                return false;

            into.set(hashCode, element);
        }

        return true;
    }

    protected boolean collision(T value) {
        int hashCode = universalHash.getHashCode(value);
        T t = elements.get(hashCode);
        return t != null && !t.equals(value);
    }
}