//package test; //uncomment this line to run in eclipse

// week 1 assignment-percolation

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double T;	//number of trials, make T double for computing confidence inverval
	private double[] xt;
	
	public PercolationStats(int n, int trials){    // perform trials independent experiments on an n-by-n grid
		if(n<=0 || trials <= 0){
			throw new java.lang.IllegalArgumentException();
		}
		T=trials;
		xt = new double[trials];
		for(int i=1; i<= trials; i++){
			Percolation mypercolation = new Percolation(n);
			// open a site
			while(mypercolation.percolates() != true){
				int row =StdRandom.uniform(1,n+1);
				int col =StdRandom.uniform(1,n+1);
				//StdOut.println(row+" "+col+" ");
				if(!mypercolation.isOpen(row,col)){
					mypercolation.open(row,col);
				}
			}
			double opensitefraction=((double) (mypercolation.numberOfOpenSites()))/((double) n*n);
			//StdOut.println(opensitefraction);
			xt[i-1]=opensitefraction;
			//StdOut.println(xt[i-1]);
		}
	}

	public double mean() {   // sample mean of percolation threshold
		return StdStats.mean(xt);
	}
	public double stddev(){    // sample standard deviation of percolation threshold
		return StdStats.stddev(xt);
	}
	public double confidenceLo(){   // low  endpoint of 95% confidence interval
		return StdStats.mean(xt)-1.96*StdStats.stddev(xt)/Math.sqrt(T);
	}
	public double confidenceHi(){    // high endpoint of 95% confidence interval
		return StdStats.mean(xt)+1.96*StdStats.stddev(xt)/Math.sqrt(T);
	}

	public static void main(String[] args){   // test client (optional)
		StdOut.print("% java percolation ");
		int n = StdIn.readInt();
		int trials = StdIn.readInt();
		PercolationStats mypercolationstats = new PercolationStats(n,trials);
		//Print out the statistic values
		StdOut.println("mean = "+ mypercolationstats.mean());
		StdOut.println("stddev = "+mypercolationstats.stddev());
		StdOut.println("95% condidence interval = "+"["+mypercolationstats.confidenceLo()
		+", "+mypercolationstats.confidenceHi()+"]");
	}

}


