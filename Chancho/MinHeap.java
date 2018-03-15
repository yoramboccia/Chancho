import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class implements a min-heap abstract data type (as described by the
 * generic interface IMinHeap<T extends Comparable<T>>) using a fixed array of
 * size MinHeap.MAXIMUM_HEAP_SIZE.
 */
public class MinHeap<T extends Comparable<T>> implements IMinHeap<T> {

  private int size;
  private HeapEntry<T>[] elements;
  private final int MAXIMUM_HEAP_SIZE = 52;

  public MinHeap() {
    elements = new HeapEntry[MAXIMUM_HEAP_SIZE];

    for (int i = 0; i < elements.length; i++) {
      elements[i] = new HeapEntry(null, i);
    }
  }


  @Override
  public void add(T element) throws HeapException {
    if (size >= MAXIMUM_HEAP_SIZE) {
      throw new HeapException("The heap is full");
    }
    elements[size].setItem(element);
    int cursor = size;
    while (cursor > 0) {
      if (elements[cursor].compareTo(elements[getParent(cursor)]) < 0) {
        swap(cursor, getParent(cursor));
        cursor = getParent(cursor);
      } else {
        break;
      }
    }
    size++;
  }

  private void swap(int i, int j) {
    HeapEntry<T> temp = elements[i];
    elements[i] = elements[j];
    elements[j] = temp;
  }

  private int getParent(int index) {
    return (index - 1) / 2;
  }

  @Override
  public T removeMin() {
    T min = getMin();
    elements[0] = elements[size - 1];
    MHRebuild(0);
    size--;
    return min;
  }

  private void MHRebuild(int root) {
    int cursor = root;

    while (!isLeaf(cursor)) {
      int minChild = getLeftChild(cursor);
      if (getRightChild(cursor) < size && elements[getRightChild(cursor)]
          .compareTo(elements[getLeftChild(cursor)]) < 0) {
        minChild = getRightChild(cursor);
      }
      if (elements[minChild].compareTo(elements[cursor]) < 0) {
        swap(minChild, cursor);
        cursor = minChild;
      } else {
        break;
      }
    }
  }

  private boolean isLeaf(int index) {
    return getLeftChild(index) >= size;
  }

  private int getLeftChild(int index) {
    return (index * 2) + 1;
  }

  private int getRightChild(int index) {
    return (index * 2) + 2;
  }

  @Override
  public T getMin() {
    return !isEmpty() ? elements[0].getItem() : null;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }
	
}