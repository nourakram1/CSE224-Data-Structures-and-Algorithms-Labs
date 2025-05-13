package table;

import representation.BinaryRepresentable;
import util.ListUtils;

import java.util.List;

public class HierarchicalHashtable<T extends BinaryRepresentable>
        extends AbstractHashtable<T> {

    private static final int DEFAULT_CAPACITY = 1_000_000;
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

        return directory.get(hashCode).insert(value);
    }

    @Override
    protected boolean remove(int hashCode, T value) {
        boolean removed = directory.get(hashCode).remove(value);
        if (removed) size--;
        return removed;
    }

    @Override
    public boolean contains(int hashCode, T value) {
        return directory.get(hashCode).contains(value);
    }

    @Override
    public List<T> getElements() {
        return directory.stream().flatMap(t -> t.getElements().stream()).toList();
    }

    private void grow() {
        capacity *= 2;
        List<T> list = getElements();

        ListUtils.replaceWith(this.directory, capacity, this::getHashtable);
        this.universalHash = getUniversalHash();

        insertAll(list);
    }

    private Hashtable<T> getHashtable() {
        return new Hashtable<>(BINARY_REPRESENTATION_LENGTH);
    }
}