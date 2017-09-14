//package test;//uncomment this to run in eclipse

/ week 5 assignment: dktree - 1/2

//import edu.princeton.cs.algs4.SET;
import java.util.TreeSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PointSET {
	private TreeSet<Point2D> set;
	
	public PointSET(){    // construct an empty set of points
		set = new TreeSet<Point2D>();
		// set = new SET<Point2D>; //or use SET is algs4
	}
		
	public boolean isEmpty(){  // is the set empty? 
		return set.isEmpty();
	}
	
	public int size(){     // number of points in the set 
		return set.size();
	}
	
	public void insert(Point2D p){   // add the point to the set (if it is not already in the set)
		if(p == null)
			throw new NullPointerException("Null argument!");
		if(!set.contains((Object) p) )
			set.add(p);
	}
	
	public boolean contains(Point2D p){   // does the set contain point p? 
		if(p == null)
			throw new NullPointerException("Null argument!");
		return set.contains((Object) p);
	}
	
	public void draw(){  // draw all points to standard draw 
		StdDraw.enableDoubleBuffering();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		for (Point2D p : set) {
	        p.draw();
	    }
	    StdDraw.show();
	}
	
	public Iterable<Point2D> range(RectHV rect){  // all points that are inside the rectangle 
		if(rect == null)
			throw new NullPointerException("Null argument!");
		Stack<Point2D> stack=new Stack<Point2D>();
		for (Point2D p : set) {
			if(rect.contains(p)){
				stack.push(p);
			}
		}
		return stack;
	}

	public  Point2D nearest(Point2D p){  // a nearest neighbor in the set to point p; null if the set is empty
		if(p == null)
			throw new NullPointerException("Null argument!");
		if(set.isEmpty())
			return null;
		Point2D q = new Point2D(0,0); //store the nearest point to p so far
		double dsquare=2; // the largest possible distance is sqrt(2)
		for (Point2D point : set) {
			double newdsquare=point.distanceSquaredTo(p);
			if(Double.compare(newdsquare, dsquare) <= 0){
				q = point;
				dsquare = newdsquare;
			}
		}
		return q;
	}

	public static void main(String[] args){  // unit testing of the methods (optional) 
		PointSET pointset = new PointSET();
		/*Point2D p1 = new Point2D(0.1,0.2);
		Point2D p2 = new Point2D(0.3,0.4);
		Point2D p3 = new Point2D(0.5,0.6);
		pointset.insert(p1);
		pointset.insert(p2);
		pointset.insert(p3);
		pointset.insert(p3);*/
		
		In in = new In("testing_data/kdtree/input10K.txt");
	    //int n = in.readInt();
	    //Point[] points = new Point[n];
	    for (int i = 0; i < 10000; i++) {
		//while(!StdIn.isEmpty()){
	        double x = in.readDouble();
	        double y = in.readDouble();
	        Point2D point = new Point2D(x,y);
	        pointset.insert(point);
	    }
		
		pointset.draw();
		
		//StdOut.println(pointset.contains(p1));
		StdOut.println(pointset.size());
		
		//Draw the 1x1 box
		RectHV box = new RectHV(0,0,1,1);
		box.draw();
		StdDraw.show();
		
		RectHV rect = new RectHV(0.1,0.2,1,0.4);
		StdDraw.enableDoubleBuffering();
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.setPenRadius(0.003);
		rect.draw();
		StdDraw.show();
		
		int count=0;
		for(Point2D p:pointset.range(rect)){
			count++;
		}
		StdOut.println("range: "+count);
		
		Point2D p = new Point2D(1,1);
		StdOut.println("nearestpoint to p3: "+pointset.nearest(p)+" with distance "+pointset.nearest(p).distanceSquaredTo(p));
			
	}

}
