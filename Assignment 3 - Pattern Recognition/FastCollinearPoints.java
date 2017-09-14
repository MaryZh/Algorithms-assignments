//package test;

//Guanghua Zhang, week 3 assignment - 3/3

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	
	private Point[] points;

	public FastCollinearPoints(Point[] points){    // finds all line segments containing 4 points
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
		//this.N=points.length;
		//Arrays.sort(points, points[0].BY_Y);
	} //end of constructor
		
	public int numberOfSegments(){        // the number of line segments
		return segments().length;
	}
	
	public LineSegment[] segments(){      // the line segments
		int N = points.length;
		ArrayList<LineSegment> segments=new ArrayList<LineSegment>();
		// select 4 out of n points
		for(int l=0;l<N-3;l++){
			//StdOut.println(l+ " point is: "+points[l]); //debug
			//Point[] otherpoints = new Point[N-1];
			Point[] otherpoints = new Point[N-1-l];
			/*for(int m = 0; m<N; m++){
				if(m<l)
					otherpoints[m]=points[m];
				if(m>l)
					otherpoints[m-1]=points[m];
			}*/
			for(int i = 0; i<N-1-l; i++){
				otherpoints[i]=points[i+l+1];
			}
			//StdOut.println("Other points are: ");  //debug
			//for(Point point : otherpoints)
			//	StdOut.println(point);
			Arrays.sort(otherpoints, points[l].slopeOrder());
			//debug
			//StdOut.println("Point p: "+points[l].toString());
			//StdOut.println("Sorted by slope: ");
			//for(Point point : points)
			//	StdOut.println(point.toString());
			
			int segmentlength=2;
			int startindex=0;
			for(int j=0; j< N-2-l;j++){
			//for(int j=0; j< N-2;j++){
				//Double start=points[l].slopeTo(otherpoints[startindex]);
				if( Double.compare(points[l].slopeTo(otherpoints[startindex]), points[l].slopeTo(otherpoints[j+1]))== 0){
					//StdOut.println(points[l].toString()+"-"+otherpoints[j].toString()+" and "+points[l].toString()+"-"+otherpoints[j+1].toString()+" has the same slope: "
					//		+points[l].slopeTo(otherpoints[j])+" and "+points[l].slopeTo(otherpoints[j+1]));
					segmentlength++;
					//StdOut.println("segmentlength: "+segmentlength);
					if(j+1==N-2-l && segmentlength >= 4){
						//StdOut.println("segmentlength: "+segmentlength);
						Point[] segmentpoints = new Point[segmentlength];
						for(int i=0;i<segmentlength-1;i++)
							segmentpoints[i]=otherpoints[i+startindex];
						segmentpoints[segmentlength-1]=points[l];
						Arrays.sort(segmentpoints);
						LineSegment mylinesegment =  new LineSegment(segmentpoints[0],segmentpoints[segmentlength-1]);
						//StdOut.println("Line segment: ");
						//for(Point point:segmentpoints)
						//	StdOut.print( point.toString()+"->");
						segments.add(mylinesegment);
					}
				}
				else{
					//endindex =j;
					//int segmentlength =endindex-startindex+2;
					if(segmentlength >= 4){
						//StdOut.println("segmentlength: "+segmentlength);
						Point[] segmentpoints = new Point[segmentlength];
						for(int i=0;i<segmentlength-1;i++)
							segmentpoints[i]=otherpoints[i+startindex];
						segmentpoints[segmentlength-1]=points[l];
						Arrays.sort(segmentpoints);
						LineSegment mylinesegment =  new LineSegment(segmentpoints[0],segmentpoints[segmentlength-1]);
						//StdOut.println("Line segment: ");
						//for(Point point:segmentpoints)
						//	StdOut.print( point.toString()+"->");
						segments.add(mylinesegment);
					}
					startindex=j+1;
					//start=points[l].slopeTo(otherpoints[startindex]);
					segmentlength=2;
				}
				
				/*if(segmentlength >= 4){
					StdOut.println("segmentlength: "+segmentlength);
					Point[] segmentpoints = new Point[segmentlength];
					for(int i=0;i<segmentlength-1;i++)
						segmentpoints[i]=otherpoints[i+startindex];
					segmentpoints[segmentlength-1]=points[l];
					Arrays.sort(segmentpoints);
					LineSegment mylinesegment =  new LineSegment(segmentpoints[0],segmentpoints[segmentlength-1]);
					StdOut.println("Line segment: ");
					for(Point point:segmentpoints)
						StdOut.print( point.toString()+"->");
					segments.add(mylinesegment);
				}
				
				startindex=j+1;
				//start=points[l].slopeTo(otherpoints[startindex]);
				*/
			}
			//for(int k=0; k< segments.size(); k++)
			//	StdOut.println("Line segment: "+ segments.get(k).toString());
		} //end of while
		//move elements in arraylist segments into a LineSegment[].
		LineSegment[] segmentlist= new LineSegment[segments.size()];
		for(int k=0; k< segments.size(); k++){
			segmentlist[k]=segments.get(k);
			//StdOut.println("Line segment: "+ segmentlist[k].toString());
		}
		//StdOut.println("Line segment #: "+ segments.size());
		//StdOut.println("Line segment #: "+ segmentlist.length);
		return segmentlist;
			
	} //end of segments()

	
	  public static void main(String[] args) {
	        /* YOUR CODE HERE */
		    ArrayList<Point> points=new ArrayList<Point>();
	    	//Comparator<Point> comparator = new Comparator<Point> slopeOrder();
		    Point a= new Point(10000, 0);
	    	Point b =new Point(0, 10000);
	    	Point c =new Point(3000,   7000);
	    	Point d =new Point(7000,  3000);
	    	Point e =new Point(20000,  21000);
	    	Point f =new Point(3000,   4000 );
	    	Point g =new Point(14000,  15000);
	    	Point h =new Point(6000,  7000);
	   
	    	points.add(a);
	    	points.add(b);
	    	points.add(c);
	    	points.add(d);
	    	points.add(e);
	    	points.add(f);
	    	points.add(g);
	    	points.add(h);
	    	
	    	int n=points.size();
	    	Point[] pointarray = new Point[n];
			for(int o=0; o<n; o++)
				pointarray[o]=points.get(o);
	    	
	    	StdOut.println("Number of points: "+n);
	    	
	    	//StdOut.println("Raw points: ");
	    	//for(Point point:pointarray)
	    	//	StdOut.println(point.toString());
	    	
	    	FastCollinearPoints test = new FastCollinearPoints(pointarray);
	    	
	    	StdOut.println("# of segments: "+test.numberOfSegments());
	    	
	    	LineSegment[] my=test.segments();
	    	
	    	//StdOut.println("# of segments: "+test.numberOfSegments());
	    	StdOut.println("# of segments: "+my.length);
	    	
	    	//for(LineSegment linesegment :my)
	    	//	StdOut.println("Line segment: "+linesegment.toString());
	    	
	    }
}
