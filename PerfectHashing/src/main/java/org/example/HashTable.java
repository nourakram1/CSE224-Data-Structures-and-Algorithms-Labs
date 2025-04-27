package org.example;

public abstract class HashTable<T> {
    int n, m;
    private long[][] h;

    public HashTable(int n) {
        this.n = n;
    }

    int hash(T key) {
        return 0;
    }

    abstract void insert(T key);
    abstract void delete(T key);
    abstract boolean search(T key);

    void batchInsert(String path) {

    }

    void batchDelete(String path) {

    }
}