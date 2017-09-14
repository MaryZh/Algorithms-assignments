//package test; //uncomment this to run in eclipse

import edu.princeton.cs.algs4.In;

// week 5 assignment: dktree - 2/2

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
	private Node root;
	private int size;
	
	private class Node{
		private Point2D p;
		private Node left, right;
		private RectHV rect;
		private int level;
		
		 public Node(Point2D key, int level, RectHV rect) {
	            this.p = key;
	            this.rect = rect;
	            this.level=level;
	     }
	}
	
	public KdTree(){
	}
	
	public boolean isEmpty(){  // is the BST empty? 
		return size == 0;
	}
	
	public int size(){     // number of points in the BST 
		return size;
	}
	
	/* private int size(Node x) {
	    if (x == null) return 0;
	    else return x.size;
	}*/
	
	public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("calledput() with a null key");
        if(contains(p))
        	return;
        root = put(root, p, 0,new RectHV(0,0,1,1));
        size++;
    }

    private Node put(Node x, Point2D p, int level, RectHV rect) {
    	//int xlevel=0;
    	//RectHV xrect = null;
    	//if empty create new node
        if (x == null){
        	//StdOut.println("("+p.x()+", "+p.y()+") has level: "+ level);
            //StdOut.println("("+p.x()+", "+p.y()+") has rect: "+ "("+ rect.xmin() +", " +rect.xmax() +", "+rect.ymin() +", " + rect.ymax() +").");
        	//size++;
        	return new Node(p, level, rect);
        }
        //if(x.p.equals(p)) return null; //check if p already exists or not
        // need to find out the level, even: compare x; odd: compare y;
        int cmp;
        if(x.level % 2 == 0){ //even level, vertical, start from 0
        	cmp = Double.compare(p.x(), x.p.x());
            if (cmp < 0 || (cmp == 0 && Double.compare(p.y(), x.p.y()) < 0)){ //left
             	//StdOut.println("("+p.x()+", "+p.y()+") is added to the left.");
             	x.left  = put(x.left, p, x.level+1, new RectHV(x.rect.xmin(),x.rect.ymin(), x.p.x(),x.rect.ymax()));
            }
            else{  // (cmp > 0) right
             	//StdOut.println("("+p.x()+", "+p.y()+") is added to the right.");
             	x.right = put(x.right, p, x.level+1, new RectHV(x.p.x(),x.rect.ymin(),x.rect.xmax(),x.rect.ymax()));   
             }
        }
        else{ //odd level, horizontal
        	cmp = Double.compare(p.y(), x.p.y());
        	if (cmp < 0 || (cmp == 0 && Double.compare(p.x(), x.p.x()) < 0)){ //below
            	//StdOut.println("("+p.x()+", "+p.y()+") is added to the left.");
        		x.left  = put(x.left, p, x.level+1, new RectHV(x.rect.xmin(),x.rect.ymin(),x.rect.xmax(),x.p.y()));
            }
            else{  // (cmp > 0) above
            	//StdOut.println("("+p.x()+", "+p.y()+") is added above.");
            	x.right = put(x.right, p, x.level+1, new RectHV(x.rect.xmin(),x.p.y(),x.rect.xmax(),x.rect.ymax())); 
            }
        }
        return x;
    }
	 
	public boolean contains(Point2D p){   // does the BST contain point p? 
		if(p == null)
			throw new NullPointerException("Null argument!");
			return get(root, p) != null;
	}
	 
	private Point2D get(Node x, Point2D p) { //return a point if it is in the BST
	    if (p == null) throw new IllegalArgumentException("called get() with a null key");
	    if (x == null) return null;
	    if(x.p.equals(p)) return x.p;
	    int cmp;
	    // need to find out the level, even: compare x; odd: compare y;
        if(x.level % 2 == 0){ //compare x first
        	cmp = Double.compare(p.x(), x.p.x());
        	if (cmp < 0 || (cmp == 0 && Double.compare(p.y(), x.p.y()) < 0)){
        		return get(x.left, p);
        	}
        	else{
        		return get(x.right,p);
        	}
        }
        else{ //compare y first
        	cmp = Double.compare(p.y(), x.p.y());
        	if (cmp < 0 || (cmp == 0 && Double.compare(p.x(), x.p.x()) < 0)){
        		return get(x.left, p);
        	}
        	else{
        		return get(x.right,p);
        	}
        }
	}
	
	public void draw(){  // draw all points to standard draw 
		StdDraw.enableDoubleBuffering();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		drawallpoint(root);
		StdDraw.show();
	}
	
	private void drawallpoint(Node x) {
		//if (p == null) throw new IllegalArgumentException("called get() with a null key");
		if (x == null) return;
		x.p.draw();
		//x.rect.draw();
		//StdOut.println("x.p: "+"("+x.p.x()+", "+ x.p.y() +").");
		
		drawallpoint(x.left);
		drawallpoint(x.right);
	}
	
	public Iterable<Point2D> range(RectHV rect){  // all points that are inside the rectangle 
		if(rect == null)
			throw new NullPointerException("Null argument!");
		Stack<Point2D> stack=new Stack<Point2D>();
		//if(! rect.intersects(root.rect))
		//	return null;
		//Node x = root;
		/* while(x != null && isOverlap(x.rect,rect)){
			if(rect.contains(x.p))
	        	stack.push(x.p);
			if(x.left != null){
				x = x.left;
			}
		} */
		getpoint(stack, root, rect);
		return stack;
	}
	
	private void getpoint(Stack<Point2D> stack, Node x, RectHV rect) { //push a point(x.p) into stack if it is in rect
		//if (p == null) throw new IllegalArgumentException("called get() with a null key");
		if (x == null) return;
		if(! rect.intersects(x.rect)) return;

		if(rect.contains(x.p))
		     stack.push(x.p);
		getpoint(stack, x.left, rect);
		getpoint(stack, x.right, rect);
	}
	
	public  Point2D nearest(Point2D p){  // a nearest neighbor in the set to point p; null if the set is empty
		if(p == null)
			throw new NullPointerException("Null argument!");
		if(isEmpty())
			return null;
		return findnearest(root, p, root.p);
	}
	
	private Point2D findnearest(Node x, Point2D p, Point2D nearestpoint){
		//Point2D nearestpoint = new Point2D(0,0); //store the nearest point to p so far
		if(Double.compare(nearestpoint.distanceSquaredTo(p), 0)==0) return nearestpoint;
		//if(Double.compare(x.rect.distanceSquaredTo(p),0) <= 0){	//this is wrong aparantly	
			//StdOut.println("debug 1");
			if(Double.compare(x.p.distanceSquaredTo(p), nearestpoint.distanceSquaredTo(p)) < 0){
				nearestpoint = x.p;
				//StdOut.println("debug 1a"+nearestpoint);
				if(Double.compare(nearestpoint.distanceSquaredTo(p), 0)==0)
					return nearestpoint;
				//d2 = x.p.distanceSquaredTo(p);
			}
			if(x.left != null){  //if left is not null, examine left
				//StdOut.println("debug 2"+nearestpoint);
				nearestpoint= findnearest(x.left,p, nearestpoint);
				if(x.right != null ){ //otherwise if right is not null, examine right
					if(Double.compare(nearestpoint.distanceSquaredTo(p), x.right.rect.distanceSquaredTo(p)) <= 0 )
						return nearestpoint;
					//StdOut.println("debug 3"+nearestpoint);
					return findnearest(x.right,p,nearestpoint);
				}
				else //if both children are null return
					return nearestpoint;
			}
			else if(x.right != null )
				return findnearest(x.right,p,nearestpoint);
			else
				return nearestpoint;
		//}
		//StdOut.println("debug 3"+nearestpoint);
		//return nearestpoint;
	}
	
	public static void main(String[] args){  // unit testing of the methods (optional) 
		KdTree mytree = new KdTree();
		/*Point2D p1 = new Point2D(0.1,0.2);
		Point2D p2 = new Point2D(0.3,0.4);
		Point2D p3 = new Point2D(0.5,0.6);
		mytree.insert(p1);
		mytree.insert(p2);
		mytree.insert(p3);
		mytree.insert(p3);*/
		
		In in = new In("testing_data/kdtree/input10K.txt");
		for (int i = 0; i < 10000; i++) {
			double x = in.readDouble();
			double y = in.readDouble();
			Point2D point = new Point2D(x,y);
			mytree.insert(point);
		}
		
		StdOut.println("contains: "+mytree.contains(new Point2D(0.1,1)));
		StdOut.println("size: "+mytree.size());
		//StdOut.println("level of p1": );
		mytree.draw();
		RectHV rect = new RectHV(0.1,0.2,1,0.4);
		StdDraw.enableDoubleBuffering();
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.setPenRadius(0.003);
		rect.draw();
		StdDraw.show();
		
		int count=0;
		for(Point2D p:mytree.range(rect)){
			count++;
		}
		StdOut.println("range: "+count);
		
		Point2D p = new Point2D(1,1);
		StdOut.println("nearestpoint to p3: "+mytree.nearest(p)+" with distance "+mytree.nearest(p).distanceSquaredTo(p));
	}
}
