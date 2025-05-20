package tree.set;

import tree.map.AVLMap;

public class AVLSet<K extends Comparable<K>> extends BSTSet<K>{
    public AVLSet() {
        bstMap = new AVLMap<>();}
}
