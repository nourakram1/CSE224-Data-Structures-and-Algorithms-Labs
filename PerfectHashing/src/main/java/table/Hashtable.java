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
    protected boolean remove(int hashCode, T value) {
        T t = elements.get(hashCode);
        if (!value.equals(t)) return false;
        elements.set(hashCode, null);
        return true;
    }

    @Override
    protected boolean insert(int hashCode, T value) {
        T t = elements.get(hashCode);
        if (value.equals(t)) return false;

        if (size * size >= capacity)
            grow();

        while (elements.get(universalHash.getHashCode(value)) != null)
            rehash();

        elements.set(universalHash.getHashCode(value), value);
        return true;
    }

    @Override
    protected boolean contains(int hashCode, T value) {
        return value.equals(elements.get(hashCode));
    }

    @Override
    public List<T> getElements() {
        return ListUtils.nonNull(elements);
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

            for (T element : getElements()) {
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