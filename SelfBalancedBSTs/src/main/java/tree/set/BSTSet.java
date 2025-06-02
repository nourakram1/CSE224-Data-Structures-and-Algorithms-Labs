package tree.set;

import java.util.List;

import tree.map.BSTMap;

public abstract class BSTSet<K extends Comparable<K>> {

    protected BSTMap<K, ?> bstMap;

    public List<K> insertAll(List<K> keys) {
        return keys.stream().filter(this::insert).toList();
    }

    public List<K> removeAll(List<K> keys) {
        return keys.stream().filter(this::delete).toList();
    }

    public boolean insert(K key) {
        return bstMap.insert(key, null);
    }

    public boolean delete(K key) {
        return bstMap.delete(key);
    }

    public boolean search(K key) {
        return bstMap.contains(key);
    }

    public int height() {
        return bstMap.height();
    }

    public int size() {
        return bstMap.size();
    }

    public BSTMap.Node<K, ?> getRoot() {
        return bstMap.getRoot();
    }
}
