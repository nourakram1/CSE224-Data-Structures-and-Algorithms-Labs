package tree.map;

/**
 * Left Leaning Red-Black Tree implementation extending a generic Binary Search Tree (BST).
 * Maintains balance using color rules and rotations to ensure logarithmic time
 * operations.
 * Robert Sedgwick paper 2008: <a href="https://sedgewick.io/wp-content/themes/sedgewick/papers/2008LLRB.pdf">...</a>
 * reference code: <a href="https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html">...</a>
 */
public class RedBlackMap<K extends Comparable<K>, V> extends BSTMap<K, V> {

    /**
     * Internal node class for Red-Black Tree nodes.
     * Extends the generic Node class and adds color information.
     */
    private class RedBlackNode extends Node<K, V> {
        public static final boolean RED = true;
        public static final boolean BLACK = false;

        public boolean color;

        public RedBlackNode(K key, V value) {
            super(key, value);
            this.color = RED; // New nodes are initially red
        }
    }

    /**
     * Returns true if the given node is red.
     * 
     * @param x Node to check
     * @return true if red, false otherwise
     */
    private boolean isRed(Node<K, V> x) {
        if (x == null)
            return false;
        return ((RedBlackNode) x).color == RedBlackNode.RED;
    }

    /**
     * Performs a right rotation on the given node.
     * 
     * @param node Node to rotate
     * @return New root after rotation
     */
    private RedBlackNode rotateRight(RedBlackNode node) {
        RedBlackNode x = (RedBlackNode) node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RedBlackNode.RED;
        return x;
    }

    /**
     * Performs a left rotation on the given node.
     * 
     * @param node Node to rotate
     * @return New root after rotation
     */
    private RedBlackNode rotateLeft(RedBlackNode node) {
        RedBlackNode x = (RedBlackNode) node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RedBlackNode.RED;
        return x;
    }

    /**
     * Flips the color of the node and its children.
     * Used when both children are red to split 4-nodes.
     * 
     * @param node Node to flip colors on
     */
    private void flipColors(RedBlackNode node) {
        node.color = !node.color;
        ((RedBlackNode) node.left).color = !((RedBlackNode) node.left).color;
        ((RedBlackNode) node.right).color = !((RedBlackNode) node.right).color;
    }

    /**
     * Balances the subtree rooted at the given node.
     * Applies rotations and color flips as necessary.
     * 
     * @param h Node to balance
     * @return Balanced node
     */
    private RedBlackNode balance(RedBlackNode h) {
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        return h;
    }

    /**
     * Inserts a key-value pair into the tree and ensures the root is black.
     * 
     * @param node  Root node
     * @param key   Key to insert
     * @param value Value to insert
     * @return Updated root node
     */
    @Override
    protected Node<K, V> insert(Node<K, V> node, K key, V value) {
        RedBlackNode root = insert((RedBlackNode) node, key, value);
        root.color = RedBlackNode.BLACK; // Root must always be black
        return root;
    }

    /**
     * Recursive insertion of key-value pair.
     * Applies balancing after insertion.
     */
    private RedBlackNode insert(RedBlackNode node, K key, V value) {
        if (node == null) {
            size++;
            return new RedBlackNode(key, value);
        }

        int cmp = compare(key, node.key);
        if (cmp < 0)
            node.left = insert((RedBlackNode) node.left, key, value);
        else if (cmp > 0)
            node.right = insert((RedBlackNode) node.right, key, value);
        else
            node.value = value;

        return balance(node);
    }

    /**
     * Deletes the node with the given key.
     * 
     * @param node Root node
     * @param key  Key to delete
     * @return Updated root node
     */
    @Override
    protected Node<K, V> delete(Node<K, V> node, K key) {
        if (node == null)
            return null;

        RedBlackNode root = (RedBlackNode) node;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RedBlackNode.RED;

        RedBlackNode result = delete(root, key);
        if (result != null)
            result.color = RedBlackNode.BLACK;
        return result;
    }

    /**
     * Recursive deletion logic using left-leaning red-black tree rules.
     * 
     * @param h   Node to delete from
     * @param key Key to delete
     * @return Updated node
     */
    private RedBlackNode delete(RedBlackNode h, K key) {
        if (compare(key, h.key) < 0) {
            if (!isRed(h.left) && (h.left == null || !isRed(h.left.left)))
                h = moveRedLeft(h);
            h.left = delete((RedBlackNode) h.left, key);

        } else {
            if (isRed(h.left))
                h = rotateRight(h);

            if (compare(key, h.key) == 0 && h.right == null) {
                return null;
            }

            if (!isRed(h.right) && (h.right == null || !isRed(h.right.left)))
                h = moveRedRight(h);

            if (compare(key, h.key) == 0) {
                RedBlackNode min = (RedBlackNode) getMinNode(h.right);
                h.key = min.key;
                h.value = min.value;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete((RedBlackNode) h.right, key);
            }
        }

        return balance(h);
    }

    /**
     * Deletes the minimum node in the subtree.
     * 
     * @param node Root of the subtree
     * @return Updated root after deletion
     */
    private RedBlackNode deleteMin(Node<K, V> node) {
        if (node.left == null) {
            return null;
        }

        RedBlackNode h = (RedBlackNode) node;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * Moves a red link left to prepare for deletion.
     * Used when left child is not red.
     * 
     * @param h Node to modify
     * @return Modified node
     */
    private RedBlackNode moveRedLeft(RedBlackNode h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight((RedBlackNode) h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * Moves a red link right to prepare for deletion.
     * Used when right child is not red.
     * 
     * @param h Node to modify
     * @return Modified node
     */
    private RedBlackNode moveRedRight(RedBlackNode h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
}
