//package test;//uncomment this line to run in eclipse

// week 4 assignment - 1/2

import edu.princeton.cs.algs4.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdRandom;

public class Board {
	//private int[] boardarray;
	private int[] blocks;
	private int dimension;

	public Board(int[][] blocks) {   // construct a board from an n-by-n array of blocks
		this.dimension=blocks.length;
		
		this.blocks=new int[this.dimension*this.dimension+1]; //the 0th element is not used
		//this.blocks=new int[this.dimension][this.dimension];
		for(int i=0; i< this.dimension; i++){
			for(int j=0; j< this.dimension; j++){
				//this.boardarray[i*this.dimension+j+1]=blocks[i][j];
				this.blocks[i*this.dimension+j+1]=blocks[i][j];
			}
		}
	} // close constructor
	
	public int dimension(){       // board dimension n
		return dimension;
	}
	 
	public int hamming(){       // number of blocks out of place
		int ncount=0;
		for(int k=1; k< blocks.length-1; k++){
					if(blocks[k] != k)
						ncount++;
		} //close 1st for
		return ncount;
	}
	
	public int manhattan() {  // sum of Manhattan distances between blocks and goal
		int distancecount =0;
		for(int k=1; k< blocks.length; k++){
				//StdOut.println("row, column "+ i+", "+j);
				if(blocks[k] !=0  && blocks[k] != k){
					int i=(k-1)/dimension;
					int j=k - 1 - i*dimension;
					int row = (blocks[k]-1)/dimension;
					int column = blocks[k] - 1 - row*dimension;
					int distance =Math.abs(row-i)+Math.abs(column-j);
					//StdOut.println("distance: "+ distance);
					distancecount+=distance;
				}
		} //close 1nd for
		return distancecount;
	}
	
	public boolean isGoal() {     // is this board the goal board?
		boolean isGoal=true;
		for(int k=1; k< blocks.length-1; k++){
				if(  blocks[k] != k ){	
						isGoal=false;
						break;
				}
		} //close 1st for
		return isGoal;
	}
	
	public Board twin(){  // a board that is obtained by exchanging any pair of blocks
		int[] copyblocks= new int[blocks.length];
		
		for(int i=1; i< blocks.length; i++){
			copyblocks[i]=blocks[i];
	    }
		//find 0
		int index0=0;
		for(int k=1; k< blocks.length; k++){
				if(blocks[k] ==0){
					index0=k;
					break;
				}
		} //close for
		int k1,k2;
		if(index0 +1 <blocks.length){
			k1=index0+1;
			if(index0+2<blocks.length)
				k2=index0+2;
			else
				k2=index0-1;
		}
		else{
			k1=index0-1;
			k2=index0-2;
		}
		int swap=copyblocks[k1];
		copyblocks[k1] = copyblocks[k2];
		copyblocks[k2] = swap;
		
		return new Board(convert2D(copyblocks));
	}
	
	public boolean equals(Object y) {      // does this board equal y?
		if (this == y) return true;
		if (y == null) return false;
		if (this.getClass() != y.getClass()) return false;
		Board that = (Board) y;
		if (this.dimension != that.dimension) return false;
		//check:
		for(int i=1; i< blocks.length; i++){
				if(this.blocks[i] != that.blocks[i]){
					//StdOut.println("Check (i, j): "+i+", "+j);
					//break check; // not need to put break since return breaks the loop and returns from the method
					return false;
				}
		}
		
		return true;
	}
	
	public Iterable<Board> neighbors(){     // all neighboring boards
		Stack<Board> stack = new Stack<Board>();
		//Board copyboard= new Board(blocks);
		//find 0
		int index0=0;
		for(int k=1; k< blocks.length; k++){
				if(blocks[k] ==0){
					index0=k;
					break;
				}
		} //close for
		//StdOut.println("0 is at: "+row+", "+column);
		//switch 0 with nearby elements
		if(index0-dimension>0){
			int[] copyblocks= new int[blocks.length];
			for(int i=1; i< blocks.length; i++){
					copyblocks[i]=blocks[i];
			}
			copyblocks[index0]=copyblocks[index0-dimension];
			copyblocks[index0-dimension]=0;
			
			stack.push(new Board(convert2D(copyblocks)));
			//StdOut.println(neighbor.toString());
		}
		if(index0+dimension<blocks.length){
			int[] copyblocks= new int[blocks.length];
			for(int i=1; i< blocks.length; i++){
					copyblocks[i]=blocks[i];
			}
			copyblocks[index0]=copyblocks[index0+dimension];
			copyblocks[index0+dimension]=0;
			
			stack.push(new Board(convert2D(copyblocks)));
		}
			
		if(index0-1>((index0-1)/dimension)*dimension){
			int[] copyblocks= new int[blocks.length];
			for(int i=1; i< blocks.length; i++){
					copyblocks[i]=blocks[i];
			}
			copyblocks[index0]=copyblocks[index0-1];
			copyblocks[index0-1]=0;
			
			stack.push(new Board(convert2D(copyblocks)));
			//StdOut.println(neighbor.toString());
		}
		if(index0+1<=((index0-1)/dimension+1)*dimension){
			int[] copyblocks= new int[blocks.length];
			for(int i=1; i< blocks.length; i++){
					copyblocks[i]=blocks[i];
			}
			copyblocks[index0]=copyblocks[index0+1];
			copyblocks[index0+1]=0;
			
			stack.push(new Board(convert2D(copyblocks)));
			//StdOut.println(neighbor.toString());
		}
		
		return stack;
	}
	
	public String toString(){          // string representation of this board (in the output format specified below)
		StringBuilder s = new StringBuilder();
	    s.append(dimension + "\n");
	    for (int k = 0; k < dimension; k++) {
	        for (int i = 1; i <= dimension; i++) {
	            s.append(String.format("%2d ", blocks[k*dimension+i]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}
	
	private int[][] convert2D(int[] oneDblocks){
		int[][] twoDblocks =new int[dimension][dimension];
		for(int k=1; k< blocks.length; k++){
				int i=(k-1)/dimension;
				int j=k - 1 - i*dimension;
				twoDblocks[i][j]=oneDblocks[k];
		}
		return twoDblocks;
	}
	
	public static void main(String[] args) {

	    // create initial board from file
	    In in = new In("testing_data/8puzzle/puzzle01.txt");
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	    StdOut.println(initial.toString());
	    
	    //StdOut.println("hamming(): "+initial.hamming());
	    StdOut.println("manhattan(): "+initial.manhattan());
	    
	    //StdOut.println("isGoal(): "+initial.isGoal());
	    
	    Board twin = initial.twin();
	    StdOut.println("twin(): "+twin.toString());
	    	
	    
	    StdOut.println("equals(): "+initial.equals(twin));
	    
	    for(Board board:initial.neighbors())
	    	StdOut.println(board.toString());
	}

}
