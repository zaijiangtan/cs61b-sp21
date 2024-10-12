package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> {
    private class Node{
        T data;
        Node next;
        Node prev;

        public Node(T item){
            data = item;
            next = null;
            prev = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public LinkedListDeque(){
        front.next = rear;
        front.prev = null;
        rear.prev = front;
        rear.next = null;
        front.data = null;
        rear.data = null;
        size = 0;
    }

    public void addFirst(T item){
        Node newNode = new Node(item);
        newNode.next = front.next;
        front.next.prev = newNode;
        newNode.prev = front;
        front.next = newNode;

        size += 1;
    }

    public void addLast(T item){
        Node newNode = new Node(item);
        newNode.next = rear;
        rear.prev.next = newNode;
        newNode.prev = rear.prev;
        rear.prev = newNode;

        size += 1;
    }

    public boolean isEmpty(){
        if(front.next == rear){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p = front.next;
        while(p != rear){
            System.out.print(p.data + " ");
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty()) {
            return null;
        }
        T item = front.next.data;
        front.next.data = null;
        front.next.next.prev = front;
        front.next = front.next.next;
        return item;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T item = rear.prev.data;
        rear.prev.data = null;
        rear.prev.prev.next = rear;
        rear.prev = rear.prev.prev;
        return item;
    }

    public T get(int index){
        if(isEmpty()){
            return null;
        }
        Node current = front.next;
        while(current != rear && index != 0){
            current = current.next;
            index -= 1;
        }
        return current.data;
    }

    public T getRecursive(int index){
        if(isEmpty()){
            return null;
        }

        if(index == 0){
            return front.next.data;
        }

        index -= 1;
        return getRecursive(index);
    }

//    public Iterator<T> iterator(){
//
//    }
//
//    public boolean equals(Object o){
//
//    }
}
