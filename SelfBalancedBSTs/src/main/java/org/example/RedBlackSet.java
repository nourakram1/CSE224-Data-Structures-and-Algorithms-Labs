package org.example;

public class RedBlackSet<K extends Comparable<K>> extends BSTSet<K> {
    public RedBlackSet() {bst = new RedBlack<>();}
}
