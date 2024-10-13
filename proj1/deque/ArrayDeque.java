package deque;

import java.util.Iterator;

public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int front;
    private int rear;

    public ArrayDeque(){
        array = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 0;
    }

    private void resize(int newSize){
        T[] newArr = (T[]) new Object[newSize];
        for(int i = 0; i < size; i++){
            newArr[i] = array[i];
            array[i] = null;
        }
        array = newArr;
    }

    public void addFirst(T item){
        if(size == array.length){
            resize(size * 2);
        }

        array[front] = item;
        front = (front + array.length - 1) % array.length;
        size += 1;
    }

    public void addLast(T item){
        if(size == array.length){
            resize(size * 2);
        }

        array[rear] = item;
        rear = (rear + 1) & array.length;
        size += 1;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if(!isEmpty()){
            while(front != rear){
                System.out.print(array[front] + " ");
                front = (front + 1) % array.length;
            }
        }
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }

        front = (front + 1) % array.length;
        T item = array[front];
        array[front] = null;
        size -= 1;
        return item;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }

        rear = (rear + array.length - 1) % array.length;
        T item = array[rear];
        array[rear] = null;
        size -= 1;
        return item;
    }

    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        index = (front + 1 + index) % array.length;
        return array[index];
    }

//    public Iterator<T> iterator(){
//
//    }
//
//    public boolean equals(Object o){
//
//    }

}
