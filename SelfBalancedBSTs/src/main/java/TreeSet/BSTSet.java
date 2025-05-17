package TreeSet;

import TreeMap.BST;

public abstract class BSTSet<K extends Comparable<K>> {

    protected BST<K, ?> bst;

    public boolean insert(K key) {return bst.insert(key, null);}
    public boolean delete(K key) {return bst.delete(key);}
    public boolean search(K key) {return bst.contains(key);}
    public int height()          {return bst.height();}
    public int size()            {return bst.size();}
}
