//package test; //uncomment this line to run in eclipse

//Guanghua Zhang, week 4 assignment - 2/2


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
	//private Board initial;
	private SearchNode finalSearchNode;
	//private boolean isSolvable;
	//private int moves;
	
	public Solver(Board initial){    // find a solution to the initial board (using the A* algorithm)
		if(initial==null)
			throw new java.lang.NullPointerException("null argument");
		
		//this.initial=initial;
		
		//if(this.isSolvable()){ // have to test, otherwise while() below is a dead loop
	
		//StdOut.println(initial.toString());
		
		SearchNode initialSearchNode =new SearchNode(initial, 0, null, false);
		SearchNode initialSearchNodeTwin =new SearchNode(initial.twin(), 0, null, true);
		pq.insert(initialSearchNode);
		pq.insert(initialSearchNodeTwin);
		
		//StdOut.println("debug 1");
		
		//SearchNode curSearchNode=initialSearchNode;
		int moves=0;
		
		//StdOut.println("debug 2");
		
		//StdOut.println(curSearchNode.cur.isGoal());
		SearchNode curSearchNode =pq.min();
		
		while(! curSearchNode.cur.isGoal()){
			pq.delMin();
			moves++;
			//StdOut.println("moves: "+moves);
			for(Board neighbor:curSearchNode.cur.neighbors()){
				if( curSearchNode.pre == null){
					//StdOut.println("debug 4: "+ true);
					pq.insert(new SearchNode(neighbor, curSearchNode.curmoves+1, curSearchNode,curSearchNode.isTwin));
				}
				else{
					if(! neighbor.equals(curSearchNode.pre.cur))
						pq.insert(new SearchNode(neighbor, curSearchNode.curmoves+1, curSearchNode,curSearchNode.isTwin));
				}
			}
			curSearchNode=pq.min();
			//StdOut.println("curSearchNode: "+curSearchNode.cur.toString());
		}
		StdOut.println("moves: "+moves);
		this.finalSearchNode =curSearchNode;
		/*
		if(!curSearchNode.isTwin){
			this.isSolvable = true;
			this.finalSearchNode =curSearchNode;
			this.moves=curSearchNode.curmoves;
		}
		*/
		//StdOut.println("debug 5: "+moves);
		
		//} //close if
	} // close constructor
	
	public boolean isSolvable(){     // is the initial board solvable?
		//return isSolvable;
		return !finalSearchNode.isTwin;
	}
	
	 public int moves(){    // min number of moves to solve initial board; -1 if unsolvable
		 if(isSolvable()){
			 return finalSearchNode.curmoves;
		 }
		 else
			 return -1;
	 }
	 
	 public Iterable<Board> solution(){  // sequence of boards in a shortest solution; null if unsolvable
		 if(! isSolvable())
			 return null;
		 
		 Stack<Board> mystack = new Stack<Board>();
		 //Stack<Board> mystack2 = new Stack<Board>();
		 
		 SearchNode curSearchNode=finalSearchNode;
		 while(curSearchNode != null){
			 mystack.push(curSearchNode.cur);
			 curSearchNode=curSearchNode.pre;
		 }
		 // reverse the order //not necessary
		// while(!mystack.isEmpty())
		//	 mystack2.push(mystack.pop());
		 
		 //this.N=mystack.size();
		 
		 return mystack;
	 }
	
	private class SearchNode implements Comparable<SearchNode>{
		private Board cur;
		private int curmoves;
		private SearchNode pre;
		private boolean isTwin;
		
		public SearchNode(Board cur, int curmoves, SearchNode pre, boolean isTwin){
			this.cur=cur;
			this.curmoves=curmoves;
			this.pre=pre;
			this.isTwin=isTwin;
		}
		
		public int priority(){
			return cur.manhattan()+ curmoves;
		}
		
		public int compareTo(SearchNode that){
			return this.priority()-that.priority();
		}
	}
	
	public static void main(String[] args) {

	    // create initial board from file
	    In in = new In("testing_data/8puzzle/puzzle3x3-25.txt");
	    int n = in.readInt();
	    StdOut.println("n="+n);
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	    
	    //StdOut.println(initial.toString());
	    //StdOut.println(initial.twin().toString());

	   // solve the puzzle
	    Solver solver = new Solver(initial);
	    
	    
	   StdOut.println("isSolvable: "+solver.isSolvable());
	    
	   StdOut.println("moves: "+solver.moves());

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}

}
