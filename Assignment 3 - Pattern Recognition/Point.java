//package test;

// week 3 assignment - 1/3

import java.util.ArrayList;
import java.util.Arrays;

/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    //private final Comparator<Point> BY_SLOPE =new SlopeOrder();
   // private final Comparator<Point> BY_Y =new YOrder();
    //public final Comparator<Point> BY_SLOPE =new slopeOrder(); //doesn't work

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if( this.y<that.y)
    		return -1;
    	else if(this.y==that.y)
    		if(this.x<that.x)
    			return -1;
    		else if(this.x==that.x)
    			return 0;
    		else
    			return 1;
    	else
    		return 1;
    	
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	double ydiff = that.y-this.y;
    	double xdiff = that.x-this.x;
    	if(this.x==that.x)
    		if(this.y == that.y) //same point
    			return Double.NEGATIVE_INFINITY;
    		else //vertical points
    			return Double.POSITIVE_INFINITY;
    	else if(this.y==that.y) //horizontal points
    		return +0.0;
    	else
    		return ydiff/xdiff;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
   public Comparator<Point> slopeOrder() {
	   // YOUR CODE HERE
	  return (a,b) -> Double.compare(slopeTo(a),slopeTo(b));
   }
 /* 
   private class SlopeOrder implements Comparator<Point>{
	   public int compare(Point a, Point b){
   		  return Double.compare(slopeTo(a),slopeTo(b));
   	  }
   }
   
   private class YOrder implements Comparator<Point>{
	   public int compare(Point a, Point b){
   		  return a.compareTo(b);
   	  }
   }
*/

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	Point[] pointarray = new Point[6];
    	Point a= new Point(0,0);
    	Point b =new Point(1,2);
    	Point c =new Point(2,1);
    	Point d =new Point(2,2);
    	Point e =new Point(3,3);
    	pointarray[0]=a;
    	pointarray[1]=b;
    	pointarray[2]=c;
    	pointarray[3]=d;
    	pointarray[4]=e;
    	pointarray[5]=b;
    	
    	StdOut.println(pointarray.length);
    	//a.draw();
    	//b.draw();
    	//c.draw();
    	//a.drawTo(b);
    	for(int i=0; i<pointarray.length-2;i++){
    		StdOut.println("Slope of "+pointarray[i].toString()+" and "+pointarray[i+1].toString()+": "+pointarray[i].slopeTo(pointarray[i+1]));
    	}
    	
       	for(int i=0; i<pointarray.length-2;i++){
    		boolean test = pointarray[i].slopeTo(pointarray[i+1])==pointarray[i].slopeTo(pointarray[i+2]);
    		StdOut.println("Compare slope: "+pointarray[i].toString()+"-"+pointarray[i+1].toString()+" and "+pointarray[i].toString()+"-"+pointarray[i+2].toString()+ test);
    	}

    	for(int i=0; i<pointarray.length-1;i++)
    		StdOut.println("Compare "+pointarray[i].toString()+" and "+pointarray[i+1].toString()+": "+pointarray[i].compareTo(pointarray[i+1]));
    	//Arrays.sort(pointarray, a.BY_SLOPE);
 
    	StdOut.println("Normal sort: "); //This sorts by y coordinates
    	Arrays.sort(pointarray); 
    	for(Point point : pointarray)
    		StdOut.println(point.toString());
    	
    	StdOut.println("Slope sort: ");
    	Arrays.sort(pointarray, a.slopeOrder()); 
    	
    	//StdOut.println("Sorted by y: ");
    	//Arrays.sort(pointarray, a.BY_Y);	
    	for(Point point : pointarray)
    		StdOut.println(point.toString()+" with slope "+a.slopeTo(point));
    	
    	
    }
}
