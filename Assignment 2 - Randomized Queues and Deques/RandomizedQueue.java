//package test;//uncomment this line to run in eclipse

//Guanghua Zhang, week 2 assignment - 2/3

import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>  {
	private Item[] s;
	private int N = 0;
	
	public RandomizedQueue(){    // construct an empty randomized queue
		s = (Item[]) new Object[1];
	}
	
	private void resize(int capacity){
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++)
			copy[i] = s[i];
		s = copy;
	}
	
	public boolean isEmpty(){  // is the deque empty?
		return N == 0;
	}
	
	public int size() {   // return the number of items on the deque
		return N;
	}
	
	public void enqueue(Item item){  // add the item
		if (N == s.length) resize(2 * s.length);
		s[N++] = item;
	}
	
	public Item dequeue() {  // remove and return a random item
		if(isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		int index = StdRandom.uniform(N);
		//StdOut.println("dequeue index: "+index);
		Item item=s[index];
		s[index]=s[N-1]; // remove the Nth element here so it won't be lost in resizing.
		N--;
		if (N > 0 && N == s.length/4) resize(s.length/2);
		return item;
	}
	
	public Item sample() {    // return (but do not remove) a random item
		if(isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		int index = StdRandom.uniform(N);
		Item item=s[index];
		return item;
	}
	

	public Iterator<Item> iterator(){ return new ListArrayIterator(); }

	private class ListArrayIterator implements Iterator<Item>{
		private Item[] r = s;
		//StdRandom.shuffle( r);
		public ListArrayIterator() {  
	        r = (Item[]) new Object[N];  
	        for(int i=0; i<N; i++)  
	        r[i] = s[i];  
	        StdRandom.shuffle(r);  
	    }  
		private int i = 0;
		public boolean hasNext() { return i < N; }
		public void remove() { throw new java.lang.UnsupportedOperationException(); }
		public Item next() { 
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}
			return s[i++]; 
		}
	}
/*
	private class ListArrayIterator implements Iterator<Item>{
		private Item[] r = s;
		//StdRandom.shuffle((Object[]) r);
		private int index = StdRandom.uniform(N);
		public boolean hasNext() { return N > 1; }
		public void remove() { throw new java.lang.UnsupportedOperationException(); }
		public Item next() { 
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}
			return r[index]; 
		}
	}
*/
	public static void main(String[] args){   // unit testing (optional)
		RandomizedQueue<String> myrandomizedqueue= new RandomizedQueue<String>();
		myrandomizedqueue.enqueue("A");
		myrandomizedqueue.enqueue("B");
		myrandomizedqueue.enqueue("C");
		myrandomizedqueue.enqueue("D");
		StdOut.println(myrandomizedqueue.size());
		
		StdOut.println("mydeque is: ");
		for(String s : myrandomizedqueue){
			StdOut.println(s);
		}
		
		StdOut.println("dequeue: "+myrandomizedqueue.dequeue());
		StdOut.println("sample is: "+myrandomizedqueue.sample());
		StdOut.println("mydeque is: ");
		for(String s : myrandomizedqueue){
			StdOut.println(s);
		}
	}

}
