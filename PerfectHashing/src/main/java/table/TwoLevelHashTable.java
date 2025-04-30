package table;

import hash.UniversalHash;
import representation.BinaryRepresentable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoLevelHashTable<T extends BinaryRepresentable> extends AbstractHashTable<T> {
    private final List<HashTable<T>> hashTable;
    private int capacity;
    private int size;

    public TwoLevelHashTable(int initialCapacity, int binaryRepresentationLength) {
        super(binaryRepresentationLength);
        this.hashTable = new ArrayList<>();
        this.capacity = initialCapacity;
        for(int i = 0; i < initialCapacity; i++) {
            hashTable.add(new HashTable<>(binaryRepresentationLength));
        }
    }

    public TwoLevelHashTable(int binaryRepresentationLength) {
        this(DEFAULT_CAPACITY, binaryRepresentationLength);
    }

    @Override
    public boolean insert(T value) {
        ensureUniversalHash();
        if (size >= capacity) rebuild();
        boolean success = hashTable.get(getHashCode(value)).remove(value);
        if (success) size++;
        return success;
    }

    @Override
    public boolean remove(T value) {
        boolean success = hashTable.get(getHashCode(value)).remove(value);
        if (success) size--;
        return success;
    }

    @Override
    public boolean contains(T value) {
        return hashTable.get(getHashCode(value)).contains(value);
    }

    @Override
    public List<T> getElements() {
        return hashTable.stream().flatMap(t -> t.getElements().stream()).toList();
    }

    private void rebuild() {
        capacity *= 2;
        List<HashTable<T>> newTable = new ArrayList<>(Collections.nCopies(capacity,
                                            new HashTable<>(BINARY_REPRESENTATION_LENGTH)));
        UniversalHash newUniversalHash = new UniversalHash(capacity, BINARY_REPRESENTATION_LENGTH);

        for (HashTable<T> bucket : hashTable) {
            for (T element : bucket.getElements()) {
                int newIndex = newUniversalHash.hash(element.getBinaryRepresentation());
                newTable.get(newIndex).insert(element);
            }
        }

        hashTable.clear();
        hashTable.addAll(newTable);
        universalHash = newUniversalHash;
    }
}
