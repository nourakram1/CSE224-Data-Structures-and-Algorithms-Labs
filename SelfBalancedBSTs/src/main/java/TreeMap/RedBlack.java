package TreeMap;

/**
 * Implementation of Left Leaning Red Black Binary Search Tree
 * Red Black Tree is a TreeMap.BST such that:
 * - No node has two red links connected to it.
 * - Every path from root to null link has the same number of black links.
 * (Perfect Balance)
 * - Red links lean left.
 */
public class RedBlack<K extends Comparable<K>, V> extends BST<K, V> {

   private class RedBlackNode extends Node<K, V> {
      public static final boolean RED = true;
      public static final boolean BLACK = false;

      public boolean color;

      public RedBlackNode(K key, V value) {
         super(key, value);
         this.color = RED;
      }

      public boolean isRed() {
         return color == RED;
      }
   }

   private RedBlackNode rotateRight(RedBlackNode node) {
      // assert node.left != null && ((RedBlackNode) node.left).isRed();
      RedBlackNode x = (RedBlackNode) node.left;
      node.left = x.right;
      x.right = node;

      x.color = node.color;
      node.color = RedBlackNode.RED;

      return x;
   }

   private RedBlackNode rotateLeft(RedBlackNode node) {
      // assert node.right != null && ((RedBlackNode) node.right).isRed();
      RedBlackNode x = (RedBlackNode) node.right;
      node.right = x.left;
      x.left = node;

      x.color = node.color;
      node.color = RedBlackNode.RED;

      return x;
   }

   private void flipColors(RedBlackNode node) {
      // assert node != null;
      // assert node.left != null && node.right != null;
      // assert !node.isRed() && ((RedBlackNode) node.left).isRed() && ((RedBlackNode) node.right).isRed();

      node.color = RedBlackNode.RED;
      ((RedBlackNode) node.left).color = RedBlackNode.BLACK;
      ((RedBlackNode) node.right).color = RedBlackNode.BLACK;
   }

   @Override
   protected Node<K, V> insert(Node<K, V> node, K key, V value) {
      return insert((RedBlackNode) node, key, value);
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

      if (node.right != null && ((RedBlackNode) node.right).isRed() &&
            (node.left == null || !((RedBlackNode) node.left).isRed()))
         node = rotateLeft(node);

      if (node.left != null && ((RedBlackNode) node.left).isRed() &&
            node.left.left != null && ((RedBlackNode) node.left.left).isRed())
         node = rotateRight(node);

      if (node.left != null && ((RedBlackNode) node.left).isRed() &&
            node.right != null && ((RedBlackNode) node.right).isRed())
         flipColors(node);

      return node;
   }

   protected Node<K, V> hibbardDelete(Node<K, V> node, K key) {
      if (node == null)
         return null;

      int cmp = compare(key, node.key);
      if (cmp < 0)
         node.left = delete(node.left, key);
      else if (cmp > 0)
         node.right = delete(node.right, key);
      else {
         if (node.left == null) return node.right;
         if (node.right == null) return node.left;

         RedBlackNode maxLeft = (RedBlackNode) getMaxNode(node.left);
         node.key = maxLeft.key;
         node.value = maxLeft.value;
         node.left = deleteMax(node.left);
      }

      return node;
   }

   @Override
   protected Node<K, V> delete(Node<K, V> node, K key) {
      if (node == null)
         return null;

      int cmp = compare(key, node.key);
      if (cmp < 0)
         node.left = delete(node.left, key);
      else if (cmp > 0)
         node.right = delete(node.right, key);
      else {
         if (node.left == null) return node.right;
         if (node.right == null) return node.left;

         RedBlackNode maxLeft = (RedBlackNode) getMaxNode(node.left);
         node.key = maxLeft.key;
         node.value = maxLeft.value;
         node.left = deleteMax(node.left);
      }

      return node;
   }

   private RedBlackNode moveRedLeft(RedBlackNode h) {
      flipColors(h);
      if (((RedBlackNode) h.right.left).isRed()) {
         h.right = (RedBlackNode) rotateRight((RedBlackNode) h.right);
         h = rotateLeft(h);
         flipColors(h);
      }
      return h;
   }

   private RedBlackNode moveRedRight(RedBlackNode h) {
      flipColors(h);
      if (((RedBlackNode) h.left.left).isRed()) {
         h = rotateRight(h);
         flipColors(h);
      }
      return h;
   }
}
