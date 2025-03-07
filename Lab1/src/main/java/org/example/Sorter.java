package org.example;

import java.util.Stack;

public abstract class Sorter<T> {
   @SuppressWarnings("rawtypes")
   private Stack<Comparable[]> steps;

   public abstract static void sort(Comparable[] arr);
}
