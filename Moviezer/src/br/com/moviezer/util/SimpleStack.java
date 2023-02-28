package br.com.moviezer.util;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
	
	private Node top;
	private int size;
	
	private class Node{
		private T item;
		private Node next;
	}
	
	public SimpleStack(){
		top = null;
		size = 0;
	}
	
	public void push(T item){
		Node node = new Node();
		node.item = item;
		node.next = top;
		top = node;
		size++;
	}
	
	public T pop(){
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        T item = top.item;        // save item to return
        top = top.next;            // delete first node
        size--;
        return item;   
	}
	
	public boolean isEmpty(){
		return top == null;
	}
	
	public int size(){
		return size;
	}
	
	

}
