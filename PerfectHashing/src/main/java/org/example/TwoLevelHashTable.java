package org.example;


public class TwoLevelHashTable<T> extends HashTable<T> {
    private final T[][] hashTable;

    /**
     * @param n number of elements to be inserted in hash table n = |S|,
     *          s.t. S belongs to U, and U is the universe
     */
    @SuppressWarnings("unchecked")
    public TwoLevelHashTable(int n) {
        super(n);
        this.m = this.n;

        hashTable = (T[][]) new Object[m][];
        for(int i = 0; i < hashTable.length; i++) {
            hashTable[i] = (T[]) new Object[5];
        }
    }

    @Override
    void insert(T key) {

    }

    @Override
    void delete(T key) {

    }

    @Override
    boolean search(T key) {
        return false;
    }
}
