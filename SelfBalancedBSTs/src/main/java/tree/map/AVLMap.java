package tree.map;

public class AVLMap<K extends Comparable<K>, V> extends BSTMap<K, V> {
   private static final int EMPTY_HEIGHT = -1;

   protected class AVLNode extends Node<K, V> {
      int height;

      AVLNode(K key, V value) {
         super(key, value);
         this.height = 0;
      }

      @Override
      public String toString() {
         return key + " (h=" + height + ")";
      }
   }

   @Override
   protected Node<K, V> insert(Node<K, V> node, K key, V value) {
      return insert(cast(node), key, value);
   }

   private AVLNode insert(AVLNode node, K key, V value) {
      if (node == null) {
         this.size++;
         return new AVLNode(key, value);
      }

      int cmp = compare(key, node.key);
      if (cmp < 0)
         node.left = insert(cast(node.left), key, value);
      else if (cmp > 0)
         node.right = insert(cast(node.right), key, value);
      else
         node.value = value;

      updateHeight(node);
      return balance(node);
   }

   @Override
   protected Node<K, V> delete(Node<K, V> node, K key) {
      AVLNode n = cast(node);
      if (n == null)
         return null;

      int cmp = compare(key, n.key);
      if (cmp < 0)
         n.left = delete(n.left, key);
      else if (cmp > 0)
         n.right = delete(n.right, key);
      else {
         if (n.left == null)
            return n.right;
         if (n.right == null)
            return n.left;

         AVLNode successor = getMinNode(n.right);
         n.key = successor.key;
         n.value = successor.value;
         n.right = delete(n.right, successor.key);
      }

      updateHeight(n);
      return balance(n);
   }

   private AVLNode balance(AVLNode node) {
      int bf = balanceFactor(node);
      if (bf < -1) {
         if (balanceFactor(cast(node.left)) > 0)
            node.left = rotateLeft(cast(node.left));
         return rotateRight(node);
      } else if (bf > 1) {
         if (balanceFactor(cast(node.right)) < 0)
            node.right = rotateRight(cast(node.right));
         return rotateLeft(node);
      }
      return node;
   }

   private AVLNode rotateLeft(AVLNode a) {
      if (a == null || a.right == null)
         return a;
      AVLNode b = cast(a.right);
      a.right = b.left;
      b.left = a;
      updateHeight(a);
      updateHeight(b);
      return b;
   }

   private AVLNode rotateRight(AVLNode a) {
      if (a == null || a.left == null)
         return a;
      AVLNode b = cast(a.left);
      a.left = b.right;
      b.right = a;
      updateHeight(a);
      updateHeight(b);
      return b;
   }

   private int balanceFactor(AVLNode node) {
      return height(cast(node.right)) - height(cast(node.left));
   }

   private int height(AVLNode node) {
      return node == null ? EMPTY_HEIGHT : node.height;
   }

   @Override
   public int height() {
      if(root == null) return 0;
      return height(cast(root));
   }

   private void updateHeight(AVLNode node) {
      node.height = 1 + Math.max(height(cast(node.left)), height(cast(node.right)));
   }

   protected AVLNode getMinNode(Node<K, V> node) {
      AVLNode current = cast(node);
      while (current.left != null)
         current = cast(current.left);
      return current;
   }

   /**
    * Safely casts Node<K, V> to AVLNode.
    */
   private AVLNode cast(Node<K, V> node) {
      return (AVLNode) node;
   }
}
