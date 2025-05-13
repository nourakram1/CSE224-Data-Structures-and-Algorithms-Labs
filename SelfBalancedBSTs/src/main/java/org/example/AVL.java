package org.example;


public class AVL<K extends Comparable<K>, V> extends BST<K, V> {
   private static final int EMPTY_HEIGHT = -1;

   protected class AVLNode extends Node<K, V> {
      int height;
      AVLNode left, right;

      AVLNode(K key, V value) {
         super(key, value);
         this.height = 0;
      }
   }

   @Override
   protected Node<K, V> insert(Node<K, V> node, K key, V value) {
      if (node == null) return new AVLNode(key, value);

      AVLNode n = (AVLNode) node;
      int cmp = compare(key, n.key);

      if       (cmp < 0)   n.left = (AVLNode) insert(n.left, key, value);
      else if  (cmp > 0)   n.right = (AVLNode) insert(n.right, key, value);
      else                 n.value = value;

      updateHeight(n);
      return balance(n);
   }

   @Override
   protected Node<K, V> delete(Node<K, V> node, K key) {
      if (node == null) return null;

      AVLNode n = (AVLNode) node;
      int cmp = compare(key, n.key);

      if       (cmp < 0)   n.left = (AVLNode) delete(n.left, key);
      else if  (cmp > 0)   n.right = (AVLNode) delete(n.right, key);
      else {
         if (n.left == null)  return n.right;
         if (n.right == null) return n.left;

         AVLNode successor = (AVLNode) getMinNode(n.right);
         n.key = successor.key;
         n.value = successor.value;
         n.right = (AVLNode) delete(n.right, successor.key);
      }

      updateHeight(n);
      return balance(n);
   }

   private AVLNode balance(AVLNode node) {
      int bf = balanceFactor(node);
      if (bf < -1) {
         if (balanceFactor(node.left) > 0)
            node.left = rotateLeft(node.left);
         return rotateRight(node);
      } else if (bf > 1) {
         if (balanceFactor(node.right) < 0)
            node.right = rotateRight(node.right);
         return rotateLeft(node);
      }
      return node;
   }

   private AVLNode rotateLeft(Node<K, V> x) {
      AVLNode a = (AVLNode) x;
      AVLNode b = a.right;
      a.right = b.left;
      b.left = a;
      updateHeight(a);
      updateHeight(b);
      return b;
   }

   private AVLNode rotateRight(Node<K, V> x) {
      AVLNode a = (AVLNode) x;
      AVLNode b = a.left;
      a.left = b.right;
      b.right = a;
      updateHeight(a);
      updateHeight(b);
      return b;
   }

   private int balanceFactor(AVLNode node) {
      return height(node.right) - height(node.left);
   }

   private int height(AVLNode node) {
      return node == null ? EMPTY_HEIGHT : node.height;
   }

   private void updateHeight(AVLNode node) {
      node.height = 1 + Math.max(height(node.left), height(node.right));
   }
}
