package TreeMap;

public class RedBlack<K extends Comparable<K>, V> extends BST<K, V> {

    private class RedBlackNode extends Node<K, V> {
        public static final boolean RED = true;
        public static final boolean BLACK = false;

        public boolean color;

        public RedBlackNode(K key, V value) {
            super(key, value);
            this.color = RED;
        }

    }

    private boolean isRed(Node<K, V> x) {
        if (x == null)
            return false;
        return ((RedBlackNode) x).color == RedBlackNode.RED;
    }

    private RedBlackNode rotateRight(RedBlackNode node) {
        RedBlackNode x = (RedBlackNode) node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RedBlackNode.RED;
        return x;
    }

    private RedBlackNode rotateLeft(RedBlackNode node) {
        RedBlackNode x = (RedBlackNode) node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RedBlackNode.RED;
        return x;
    }

    private void flipColors(RedBlackNode node) {
        node.color = !node.color;
        ((RedBlackNode) node.left).color = !((RedBlackNode) node.left).color;
        ((RedBlackNode) node.right).color = !((RedBlackNode) node.right).color;
    }

    private RedBlackNode balance(RedBlackNode h) {
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        return h;
    }

    @Override
    protected Node<K, V> insert(Node<K, V> node, K key, V value) {
        RedBlackNode root = insert((RedBlackNode) node, key, value);
        root.color = RedBlackNode.BLACK;
        return root;
    }

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

        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);

        if (isRed(node.left) && isRed(((RedBlackNode) node.left).left))
            node = rotateRight(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

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

    private RedBlackNode delete(RedBlackNode h, K key) {
        if (compare(key, h.key) < 0) {
            if (!isRed(h.left) && (h.left == null || !isRed(((RedBlackNode) h.left).left)))
                h = moveRedLeft(h);
            h.left = delete((RedBlackNode) h.left, key);

        } else {
            if (isRed(h.left))
                h = rotateRight(h);

            if (compare(key, h.key) == 0 && h.right == null) {
                return null;
            }

            if (!isRed(h.right) && (h.right == null || !isRed(((RedBlackNode) h.right).left)))
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

    private RedBlackNode deleteMin(Node<K, V> node) {
        if (node.left == null) {
            return null;
        }

        RedBlackNode h = (RedBlackNode) node;

        if (!isRed(h.left) && (h.left == null || !isRed(((RedBlackNode) h.left).left)))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    private RedBlackNode moveRedLeft(RedBlackNode h) {
        flipColors(h);
        if (isRed(((RedBlackNode) h.right).left)) {
            h.right = rotateRight((RedBlackNode) h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private RedBlackNode moveRedRight(RedBlackNode h) {
        flipColors(h);
        if (isRed(((RedBlackNode) h.left).left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
}
