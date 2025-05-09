package org.example;

import java.util.Comparator;

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

   public boolean insert(K key, V value) {
      if (key == null) throw new NullPointerException("Key must not be null");
      int prevSize = size;
      root = insert(root, key, value);
      return size > prevSize;
   }

   public boolean delete(K key) {
      if (key == null) throw new IllegalArgumentException("Key must not be null");
      Node<K, V> node = find(root, key);
      if (node == null) return false;
      root = delete(root, key);
      size--;
      return true;
   }

   public boolean contains(K key) {
      return find(root, key) != null;
   }

   protected Node<K, V> find(Node<K, V> node, K key) {
      if (node == null || key == null) return null;
      int cmp = compare(key, node.key);
      if (cmp < 0) return find(node.left, key);
      else if (cmp > 0) return find(node.right, key);
      else return node;
   }

   protected int compare(K a, K b) {
      return comparator != null ? comparator.compare(a, b) : a.compareTo(b);
   }

   public int size() {
      return size;
   }

   public boolean isEmpty() {
      return size == 0;
   }

   public K getMin() {
      return isEmpty() ? null : getMinNode(root).key;
   }

   protected Node<K, V> getMinNode(Node<K, V> node) {
      return node.left == null ? node : getMinNode(node.left);
   }

   public K getMax() {
      return isEmpty() ? null : getMaxNode(root).key;
   }

   protected Node<K, V> getMaxNode(Node<K, V> node) {
      return node.right == null ? node : getMaxNode(node.right);
   }

   public boolean deleteMin() {
      if (isEmpty()) return false;
      delete(getMin());
      return true;
   }

   protected Node<K, V> deleteMax(Node<K, V> node) {
      if (node.right == null)
         return node.left;
      node.right = deleteMax(node.right);
      return node;
   }
}
