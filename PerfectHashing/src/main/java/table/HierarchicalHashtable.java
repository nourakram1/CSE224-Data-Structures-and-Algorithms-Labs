package table;

import representation.BinaryRepresentable;
import util.ListUtils;

import java.util.List;

public class HierarchicalHashtable<T extends BinaryRepresentable>
        extends AbstractHashtable<T> {

    private static final int DEFAULT_CAPACITY = 8;
    private final List<Hashtable<T>> directory;

    public HierarchicalHashtable() {
        this(DEFAULT_CAPACITY);
    }

    public HierarchicalHashtable(int initialCapacity) {
        super(initialCapacity);
        this.directory = ListUtils.arrayList(capacity, Hashtable::new);
    }

    @Override
    protected boolean insert(int hashCode, T value) {
        if (size == capacity)
            grow();

        return getHashtable(universalHash.getHashCode(value)).insert(value);
    }

    @Override
    protected boolean remove(int hashCode, T value) {
        boolean removed = getHashtable(hashCode).remove(value);
        if (removed) size--;
        return removed;
    }

    @Override
    public boolean contains(int hashCode, T value) {
        return getHashtable(hashCode).contains(value);
    }

    @Override
    public List<T> getElements() {
        return directory.stream().flatMap(t -> t.getElements().stream()).toList();
    }

    @Override
    protected void ensureCapacity(int capacity) {
        if (this.capacity < capacity) {
            setCapacity(capacity);
            ListUtils.ensureSize(directory, this.capacity, Hashtable::new);
            rehash();
        }
    }

    private void grow() {
        capacity *= 2;
        ListUtils.ensureSize(directory, capacity, Hashtable::new);
        rehash();
    }

    private void rehash() {
        if (size == 0)
            return;

        List<T> list = getElements();
        directory.forEach(Hashtable::clear);
        universalHash = getUniversalHash();
        insertAll(list);
    }

    private Hashtable<T> getHashtable(int hashCode) {
        return directory.get(hashCode);
    }
}