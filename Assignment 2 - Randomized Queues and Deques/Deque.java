//package test;//uncomment this line to run in eclipse

// week 2 assignment - 1/3

import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
	private Node first, last;
	private int N =0;
	
	public Deque(){  // construct an empty deque
		first =null;
		last = null;
	}

	private class Node{
		Item item;
		Node next;
	}
	
	public boolean isEmpty(){  // is the deque empty?
		return first == null;
	}
	
	public int size() {   // return the number of items on the deque
		return N;
	}
	
	public void addFirst(Item item) {  // add the item to the front
		if(item == null){
			throw new java.lang.NullPointerException();
		}
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		if (last == null){
			last = first;
		}
		N++;
		//StdOut.println("the 1st item is: "+first.item);
		//if(last != null)
		//	StdOut.println("the last item is: "+last.item);
	}
	
	public void addLast(Item item) {  // add the item to the end
		if(item == null){
			throw new java.lang.NullPointerException();
		}
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (first == null){ 
			first = last;
		}
		else{
			oldlast.next = last;
		}
		N++;
		//if(first != null)
		//	StdOut.println("the 1st item is: "+first.item);
		//if(last != null)
		//	StdOut.println("the last item is: "+last.item);
	}
	
	public Item removeFirst() {  // remove and return the item from the front
		Item item = first.item;
		if(N==0){
			throw new java.util.NoSuchElementException();
		}
	    first = first.next;
	    	N--;
	    return item;
	}
	
	public Item removeLast() {  // remove and return the item from the end
		Item item = last.item;
		if(N==0){
			throw new java.util.NoSuchElementException();
		}
		//last = null;
		//	N--;
		//return item;
		Node x=first;
		while(x.next.next != null){
			x=x.next;
		}
		x.next=null;
		N--;
		return item;
	}
	
	public Iterator<Item> iterator() { return new ListIterator(); }
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() { return current != null; }
		public void remove() { 
			throw new java.lang.UnsupportedOperationException();
		}
		public Item next(){
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	public static void main(String[] args) {   // unit testing (optional)
		Deque<String> mydeque = new Deque<String>();
		mydeque.addFirst("A");
		//StdOut.println(mydeque.size());
		StdOut.println("mydeque is: ");
		for(String s : mydeque){
			StdOut.println(s);
		}
		
		mydeque.addFirst("AA");
		//StdOut.println(mydeque.size());
		StdOut.println("mydeque is: ");
		for(String s : mydeque){
			StdOut.println(s);
		}
		
		mydeque.addLast("B");
		//StdOut.println(mydeque.size());
		StdOut.println("mydeque is: ");
		for(String s : mydeque){
			StdOut.println(s);
		}
		
		mydeque.addLast("C");
		//StdOut.println(mydeque.size());
		StdOut.println("mydeque is: ");
		for(String s : mydeque){
			StdOut.println(s);
		}
		
		mydeque.removeLast();
		//StdOut.println(mydeque.size());
		StdOut.println("mydeque is: ");
		for(String s : mydeque){
			StdOut.println(s);
		}
		
		mydeque.removeFirst();
		//StdOut.println(mydeque.size());
		StdOut.println("mydeque is: ");
		for(String s : mydeque){
			StdOut.println(s);
		}
		mydeque.addFirst("E");
		//StdOut.println(mydeque.size());
		StdOut.println("mydeque is: ");
		for(String s : mydeque){
			StdOut.println(s);
		}
	}
	
}
