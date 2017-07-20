//Author: Saul Garza
//performs the calculations of the desired expression

import java.util.EmptyStackException;
public class Calculator {
 private final int ListSize = 100;
 private Object[] items;
 private int top;
 public Calculator() {
  items = new Object[ListSize];
  top = -1;
 }
 public boolean IsEmpty() {
  return (top < 0);
 }
 public void push(Object newItem) throws RuntimeException {
  if (top < ListSize - 1) {
   items[++top] = newItem;
  } else {
   throw new RuntimeException("The List is Full");
  }
 }
 public Object pop() throws EmptyStackException {
  if (!IsEmpty()) {
   return (items[top--]);
  } else {
   throw new EmptyStackException();
  }
 }
 public Object peek() throws EmptyStackException {
  if (!IsEmpty()) {
   return items[top];
  } else {
   throw new EmptyStackException();
  }
 }
}
