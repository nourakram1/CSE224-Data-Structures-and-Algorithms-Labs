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
    public List<T> getElements() {
        return ListUtils.nonNull(elements);
    }

    protected void grow() {
        capacity *= 2;
        ListUtils.ensureSize(elements, capacity);
        rehash();
    }

    protected void rehash() {
        List<T> elements = getElements();
        UniversalHash<T> universalHash = getUniversalHash();
        List<T> hashed = hash(elements, universalHash);

        while (hashed == null) {
            universalHash = getUniversalHash();
            hashed = hash(elements, universalHash);
        }

        this.universalHash = universalHash;
        ListUtils.replaceWith(this.elements, hashed);
    }

    protected List<T> hash(List<T> elements, UniversalHash<T> universalHash) {
        List<T> hashed = ListUtils.arrayList(capacity);

        for (T element : getElements()) {
            int hashCode = universalHash.getHashCode(element);
            if (hashed.get(hashCode) != null)
                return null;

            hashed.set(hashCode, element);
        }

        return hashed;
    }

    protected boolean collision(T value) {
        int hashCode = universalHash.getHashCode(value);
        T t = elements.get(hashCode);
        return t != null && !t.equals(value);
    }
}