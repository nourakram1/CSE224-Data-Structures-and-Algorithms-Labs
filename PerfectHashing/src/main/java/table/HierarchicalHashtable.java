package table;

import representation.BinaryRepresentable;
import util.ListUtils;

import java.util.List;

public class HierarchicalHashtable<T extends BinaryRepresentable>
        extends AbstractHashtable<T> {

    private static final int DEFAULT_CAPACITY = 8;
    private final List<Hashtable<T>> hashTable;

    public HierarchicalHashtable() {
        this(DEFAULT_CAPACITY);
    }

    public HierarchicalHashtable(int initialCapacity) {
        super(initialCapacity);
        this.hashTable = ListUtils.arrayList(capacity, Hashtable::new);
    }

    @Override
    protected boolean insert(int hashCode, T value) {
        return hashTable.get(hashCode).insert(value);
    }

    @Override
    protected boolean remove(int hashCode, T value) {
        boolean removed = hashTable.get(hashCode).remove(value);
        if (removed) size--;
        return removed;
    }

    @Override
    public boolean contains(int hashCode, T value) {
        return hashTable.get(hashCode).contains(value);
    }

    @Override
    public List<T> getElements() {
        return hashTable.stream().flatMap(t -> t.getElements().stream()).toList();
    }
}