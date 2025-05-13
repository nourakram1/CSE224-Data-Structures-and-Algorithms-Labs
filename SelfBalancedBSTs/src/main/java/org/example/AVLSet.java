package org.example;

public class AVLSet<K extends Comparable<K>> extends BSTSet<K>{
    public AVLSet() {bst = new AVL<>();}
}
