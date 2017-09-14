//package test;//uncomment this line to run in eclipse

// week 2 assignment - 3/3

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	public static void main(String[] args){
		RandomizedQueue<String> myrandomizedqueue= new RandomizedQueue<String>();
		
		//StdOut.println("k is: ");
		int k = StdIn.readInt();
		
		//StdOut.println("n is: ");
		//int n = StdIn.readInt();
		
		for(int i=1; i<=4;i++){  //put all the stdin into myrandomizedqueue
		//while(!StdIn.isEmpty()){
		//while(mystring != null){
			String mystring = StdIn.readString();
			//StdOut.println("Read in string is: "+mystring);
			myrandomizedqueue.enqueue(mystring);
			//for(String s : myrandomizedqueue){
			//	StdOut.println(s);
			//}
		}
		
		
		//StdOut.println(StdIn.readAll());
		/*
		for(int i=0;i< n;i++){  //randomly print out k of them
			String mystring = StdIn.readString();
			StdOut.println("Read in string is: "+mystring);
		}
		*/
		
		for(int i=0;i< k;i++){  //randomly print out k of them
			StdOut.println(myrandomizedqueue.dequeue());
		}
	}

}
