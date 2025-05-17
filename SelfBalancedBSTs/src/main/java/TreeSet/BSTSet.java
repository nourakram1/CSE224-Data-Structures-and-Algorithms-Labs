package TreeSet;

import java.util.ArrayList;
import java.util.List;

import TreeMap.BST;

public abstract class BSTSet<K extends Comparable<K>> {

    protected BST<K, ?> bst;

    public List<K> insertAll(List<K> keys) {
        List<K> uninserted = new ArrayList<>();
        for (K key : keys) {
            boolean inserted = bst.insert(key, null);
            if (!inserted) {
                uninserted.add(key);
            }
        }
        return uninserted;
    }
    
    public List<K> insertAll(K...keys) {
        List<K> uninserted = new ArrayList<>();
        for (K key : keys) {
            boolean inserted = bst.insert(key, null);
            if (!inserted) {
                uninserted.add(key);
            }
        }
        return uninserted;
    }

    public boolean insert(K key) {
        return bst.insert(key, null);
    }

    public boolean delete(K key) {
        return bst.delete(key);
    }

    public boolean search(K key) {
        return bst.contains(key);
    }

    public int height() {
        return bst.height();
    }

    public int size() {
        return bst.size();
    }
}
