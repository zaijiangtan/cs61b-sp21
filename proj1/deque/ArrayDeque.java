package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int size;
    private int front;
    private int rear;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 1;
    }

    private class ArrayDequeIterator implements Iterator {
        private int index;
        public ArrayDequeIterator(){
            index = 0;
        }

        public boolean hasNext(){
            return index < size;
        }

        public T next(){
            T nextItem = get(index);
            index += 1;
            return nextItem;
        }
    }

    private void resize(int newSize) {
        T[] newArr = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newArr[i] = get(i);
        }
        front = newSize - 1;
        rear = size;
        array = newArr;
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resize(size * 2);
        }

        array[front] = item;
        front = (front + array.length - 1) % array.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resize(size * 2);
        }

        array[rear] = item;
        rear = (rear + 1) % array.length;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                System.out.print(get(i) + " ");
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        front = (front + 1) % array.length;
        T item = array[front];
        array[front] = null;
        size -= 1;

        if (size < array.length && array.length > 16) {
            resize(array.length / 2);
        }
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        rear = (rear + array.length - 1) % array.length;
        T item = array[rear];
        array[rear] = null;
        size -= 1;

        if (size < array.length && array.length > 16) {
            resize(array.length / 2);
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        index = (front + 1 + index) % array.length;
        return array[index];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof LinkedListDeque)) {
            return false;
        }

        ArrayDeque deque = (ArrayDeque) o;
        if (this == deque) {
            return true;
        }
        if (this.size() != deque.size()) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) != deque.get(i)) {
                return false;
            }
        }
        return true;
    }

}
