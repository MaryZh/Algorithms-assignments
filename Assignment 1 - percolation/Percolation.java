//package test; //uncomment this line to run in eclipse

// week 1 assignment-percolation

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int dimension;	// dimension of the grid =n
	private int[] state; //state = blocked(0)/open(1)
	private int count =0;	// the number of open sites
	private WeightedQuickUnionUF uf;

	public Percolation(int n){	// create n-by-n grid, with all sites blocked
		if(n<=0){
			throw new java.lang.IllegalArgumentException();
		}
		dimension=n;
		uf = new WeightedQuickUnionUF(n*n+2);	// +2 for two auxiliary sites
		state = new int[n*n+2];
		for(int i = 0; i<= n*n+1; i++){
			state[i]=0;
		}
	 }
	
	public void open(int row, int col){    // open site (row, col) if it is not open already
		if(row < 1 || row > dimension || col < 1 || col >dimension){
			throw new java.lang.IndexOutOfBoundsException();
		}
		int index = dimension*(row-1) +col;
		if(state[index]==0){
			state[index]=1;
			count++;
			// if at 1st or last row, connext it with auxiliary sites
			if(row ==1)
				uf.union(0,index);
			if(row ==dimension)
				uf.union(dimension*dimension+1,index);
			// connect it with adjacent open sites
			if(col -1 >= 1 && state[index-1]==1){
					uf.union(index,index-1);	
			}
			if(col+1 <= dimension && state[index+1]==1){
					uf.union(index,index+1);
			}
			if(row -1 >=1 && state[index-dimension]==1){
					uf.union(index,index-dimension);
			}
			if(row +1 <=dimension && state[index+dimension]==1){
					uf.union(index,index+dimension);
			}
		}
	}
	
	public boolean isOpen(int row, int col){  // is site (row, col) open?
		if(row < 1 || row > dimension || col < 1 || col >dimension){
			throw new java.lang.IndexOutOfBoundsException();
		}
		int index = dimension*(row-1)+col;
		return state[index]==1;	// index = n*row+col
	}
	
	public boolean isFull(int row, int col){  // is site (row, col) full?
		if(row < 1 || row > dimension || col < 1 || col >dimension){
			throw new java.lang.IndexOutOfBoundsException();
		}
		int index = dimension*(row-1)+col;
		return uf.connected(0, index);
	}

	public int numberOfOpenSites(){       // number of open sites
		return count;
	}
	
	public boolean percolates(){             // does the system percolate?
		return uf.connected(0,dimension*dimension+1);
	}
	
	public static void main(String[] args){   // test client (optional)
		int n=50;
		Percolation mypercolation = new Percolation(n);
		
		mypercolation.open(2, 1);
		mypercolation.isOpen(1, 1);
		StdOut.println(mypercolation.isOpen(1, 1));
		StdOut.println(mypercolation.isFull(1,1));
		StdOut.println(mypercolation.numberOfOpenSites());
		StdOut.println(mypercolation.percolates());
	}

}
