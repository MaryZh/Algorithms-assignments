//package test;

// week 3 assignment - 2/3

import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
	private Point[] points;

	public BruteCollinearPoints(Point[] points){    // finds all line segments containing 4 points
		// exceptions
		if(points==null)
			throw new java.lang.NullPointerException("argument is null");
		for(Point point:points){
			if(point==null)
				throw new java.lang.NullPointerException("null point");
		}
		for(int i=0;i<points.length;i++){
			for(int j=i+1;j<points.length;j++){
				if(points[i].compareTo(points[j])==0)
					throw new java.lang.IllegalArgumentException("repeated points");
			}
		}
		this.points=points;
		//Arrays.sort(points);
	} //end of constructor
		
	public int numberOfSegments(){        // the number of line segments
		return segments().length;
	}
	
	public LineSegment[] segments(){      // the line segments
		int N = points.length;
		Point p,q,r,s;
		ArrayList<LineSegment> segments=new ArrayList<LineSegment>();
		Point[] examinedpoints =new Point[4];
		
		// select 4 out of n points
		for(int i=0;i<N;i++){
			for(int j=i+1;j<N;j++){
				for(int k=j+1;k<N;k++){
					for(int l=k+1;l<N;l++){
						p=points[i];
						q=points[j];
						r=points[k];
						s=points[l];
						examinedpoints[0]=p;
						examinedpoints[1]=q;
						examinedpoints[2]=r;
						examinedpoints[3]=s;
						Arrays.sort(examinedpoints);
						//if(p.compareTo(q) !=0 && p.compareTo(r) !=0 && p.compareTo(s) !=0 && q.compareTo(r) !=0 && q.compareTo(s) !=0 && r.compareTo(s) !=0){
							if(p.slopeTo(q)==p.slopeTo(r) && p.slopeTo(q)==p.slopeTo(s) && p.slopeTo(r) == p.slopeTo(s)){
								LineSegment mylinesegment =  new LineSegment(examinedpoints[0],examinedpoints[3]);
								//StdOut.println("Line segment: "+ mylinesegment.toString());
								segments.add(mylinesegment);
								//nsegments++;
							}
						//}
					}	
				}
			}
		}
		//move elements in arraylist segments into a LineSegment[].
		LineSegment[] segmentlist= new LineSegment[segments.size()];
		for(int i=0; i<segments.size(); i++)
			segmentlist[i]=segments.get(i);
		return segmentlist;
			
	} //end of segments()
	
	  public static void main(String[] args) {
		    ArrayList<Point> points=new ArrayList<Point>();
	    	//Comparator<Point> comparator = new Comparator<Point> slopeOrder();
	    	Point a= new Point(0, 0);
	    	Point b =new Point(1, 1);
	    	Point c =new Point(1, 2);
	    	Point d =new Point(2, 1);
	    	Point e =new Point(2, 2);
	    	Point f =new Point(3, 3);
	    	Point g =new Point(3, 1);
	    	Point h =new Point(4, 1);
	   
	    	points.add(a);
	    	points.add(b);
	    	points.add(c);
	    	points.add(d);
	    	points.add(e);
	    	points.add(f);
	    	points.add(g);
	    	points.add(h);
	    	
	    	int N=points.size();
	    	Point[] pointarray = new Point[N];
			for(int i=0; i<N; i++)
				pointarray[i]=points.get(i);
	    	
	    	StdOut.println("Number of points: "+N);
	    	
	    	for(Point point:pointarray)
	    		StdOut.println("Points: "+point.toString());
	    	
	    	BruteCollinearPoints test = new BruteCollinearPoints(pointarray);
	    	
	    	StdOut.println("# of segments: "+test.numberOfSegments());
	    	
	    	LineSegment[] my=test.segments();
	    	
	    	StdOut.println("# of segments: "+test.numberOfSegments());
	    	
	    	for(LineSegment linesegment : my)
	    		StdOut.println("Line segment: "+linesegment.toString());
	    	
	    }
/*	  
	  public static void main(String[] args) {

		    // read the n points from a file
		    In in = new In(args[0]);
		    int n = in.readInt();
		    Point[] points = new Point[n];
		    for (int i = 0; i < n; i++) {
		        int x = in.readInt();
		        int y = in.readInt();
		        points[i] = new Point(x, y);
		    }

		    // draw the points
		    StdDraw.enableDoubleBuffering();
		    StdDraw.setXscale(0, 32768);
		    StdDraw.setYscale(0, 32768);
		    for (Point p : points) {
		        p.draw();
		    }
		    StdDraw.show();

		    // print and draw the line segments
		    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		    for (LineSegment segment : collinear.segments()) {
		        StdOut.println(segment);
		        segment.draw();
		    }
		    StdDraw.show();
		}
*/
}
