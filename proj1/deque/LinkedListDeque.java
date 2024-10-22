package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T item) {
            data = item;
            next = null;
            prev = null;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int index;

        LinkedListDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T nextItem = get(index);
            index += 1;
            return nextItem;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public LinkedListDeque() {
        front = new Node(null);
        rear = new Node(null);
        front.next = rear;
        rear.prev = front;
        size = 0;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item);
        newNode.next = front.next;
        front.next.prev = newNode;
        newNode.prev = front;
        front.next = newNode;

        size += 1;
    }

    public void addLast(T item) {
        Node newNode = new Node(item);
        newNode.next = rear;
        rear.prev.next = newNode;
        newNode.prev = rear.prev;
        rear.prev = newNode;

        size += 1;
    }


    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = front.next;
        while (p != rear) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = front.next.data;
        front.next.data = null;
        front.next = front.next.next;
        front.next.prev = front;
        size -= 1;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T item = rear.prev.data;
        rear.prev.data = null;
        rear.prev = rear.prev.prev;
        rear.prev.next = rear;
        size -= 1;
        return item;
    }

    public T get(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
        Node current = front.next;
        while (current != rear && index != 0) {
            current = current.next;
            index -= 1;
        }
        return current.data;
    }

    private T getRecursiveHelper(int index, Node current) {
        if (index == 0) {
            return current.data;
        }
        return getRecursiveHelper(index - 1, current.next);
    }

    public T getRecursive(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }

        return getRecursiveHelper(index, front.next);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof ArrayDeque)) {
            return false;
        }

        LinkedListDeque<T> deque = (LinkedListDeque<T>) o;
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
