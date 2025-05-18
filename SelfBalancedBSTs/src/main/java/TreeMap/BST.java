package TreeMap;

import java.util.Comparator;
import java.util.Objects;

public abstract class BST<K extends Comparable<K>, V> {
   protected Node<K, V> root;
   protected int size;
   protected final Comparator<? super K> comparator;

   protected abstract static class Node<K extends Comparable<K>, V> {
      public K key;
      public V value;
      public Node<K, V> left, right;

      Node(K key, V value) {
         this.key = key;
         this.value = value;
      }
   }

   public BST(Comparator<? super K> comparator) {
      this.root = null;
      this.size = 0;
      this.comparator = comparator;
   }

   public BST() {
      this(null);
   }

   protected abstract Node<K, V> insert(Node<K, V> node, K key, V value);
   protected abstract Node<K, V> delete(Node<K, V> node, K key);

   public V get(K key) {return find(root, key).value;}

   public boolean insert(K key, V value) {
      Objects.requireNonNull(key);
      int prevSize = size;
//      if(!contains(key)) size++;
      root = insert(root, key, value);
      return prevSize < size;
   }

   public boolean delete(K key) {
      Objects.requireNonNull(key);
      if(!contains(key)) return false;
      root = delete(root, key);
      size--;
      return true;
   }

   public boolean contains(K key) {
      Objects.requireNonNull(key);
      return find(root, key) != null;
   }

   protected Node<K, V> find(Node<K, V> node, K key) {
      if (node == null || key == null) return null;
      int cmp = compare(key, node.key);
      if       (cmp < 0) return find(node.left, key);
      else if  (cmp > 0) return find(node.right, key);
      else               return node;
   }

   protected int compare(K a, K b) {
      return comparator != null ? comparator.compare(a, b) : a.compareTo(b);
   }

   public int size() { return size; }

   public boolean isEmpty() {return size == 0;}

   protected Node<K, V> getMinNode(Node<K, V> node) {
      return (node == null || node.left == null) ? node : getMinNode(node.left);
   }

   protected Node<K, V> getMaxNode(Node<K, V> node) {
      return (node == null || node.right == null) ? node : getMaxNode(node.right);
   }

   protected Node<K, V> deleteMax(Node<K, V> node) {
      if (node.right == null)
         return node.left;
      node.right = deleteMax(node.right);
      return node;
   }

   public int height() {
      if(root == null) return 0;
      return height(root);
   }

   private int height(Node<K, V> node) {
      if(node == null) return -1;
      return 1 + Math.max(height(node.left), height(node.right));
   }
}
