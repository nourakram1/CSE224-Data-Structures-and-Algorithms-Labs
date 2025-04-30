package table;

import hash.UniversalHash;
import representation.BinaryRepresentable;
import util.ListUtils;

import java.util.List;

public class HierarchicalHashTable<T extends BinaryRepresentable> extends AbstractHashTable<T> {
    private static final int DEFAULT_CAPACITY = 8;
    private final List<HashTable<T>> hashTable;

    public HierarchicalHashTable(int binaryRepresentationLength) {
        this(DEFAULT_CAPACITY, binaryRepresentationLength);
    }

    public HierarchicalHashTable(int initialCapacity, int binaryRepresentationLength) {
        super(initialCapacity, binaryRepresentationLength);
        this.hashTable = ListUtils.arrayList(initialCapacity, () -> new HashTable<>(binaryRepresentationLength));
    }

    @Override
    public boolean insert(T value) {
        boolean inserted = getBucket(value).insert(value);
        if (inserted) size++;
        return inserted;
    }

    @Override
    public boolean remove(T value) {
        boolean removed = getBucket(value).remove(value);
        if (removed) size--;
        return removed;
    }

    @Override
    public boolean contains(T value) {
        return getBucket(value).contains(value);
    }

    @Override
    public List<T> getElements() {
        return hashTable.stream().flatMap(t -> t.getElements().stream()).toList();
    }

    private HashTable<T> getBucket(T value) {
        return getBucket(value, hashTable, universalHash);
    }

    private HashTable<T> getBucket(T value, List<HashTable<T>> hashTable, UniversalHash<T> universalHash) {
        return hashTable.get(universalHash.getHashCode(value));
    }
}
