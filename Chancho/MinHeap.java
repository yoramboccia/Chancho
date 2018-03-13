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

    if (isEmpty()) {
      elements[0].setItem(element);
      size++;
      return;
    }

    int i = 0;
    while (element.compareTo(elements[i].getItem()) > 0) {
      i++;
      if (i >= MAXIMUM_HEAP_SIZE) {
        throw new HeapException("No space on the Heap");
      }
      if (i == size) {
        break;
      }
    }

    for (int j = size; j > i; j++) {
      elements[j] = elements[j - 1];
    }
    elements[i].setItem(element);
    size++;
  }

  @Override
  public T removeMin() {
    T min = getMin();
    for (int i = 0; i < size - 1; i++) {
      elements[i] = elements[i + 1];
    }
    size--;
    return min;
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