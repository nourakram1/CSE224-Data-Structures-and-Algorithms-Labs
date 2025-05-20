package tree.set;

import tree.map.RedBlackMap;

public class RedBlackSet<K extends Comparable<K>> extends BSTSet<K> {
    public RedBlackSet() {
        bstMap = new RedBlackMap<>();}
}
