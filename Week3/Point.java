/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new MyComparator (this);       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x && this .y == that.y)
        	return Double.NEGATIVE_INFINITY;
        else if (this.y == that.y)
        	return 0;
        else if (this.x == that.x)
        	return Double.POSITIVE_INFINITY;
        else
        {
        	double tmp1 = that.y - this.y;
        	double tmp2 = that.x - this.x;
        	return tmp1 / tmp2;
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
    	if (this.y < that.y || (this.y == that.y && this.x < that.x))
    		return -1;
    	else
    		return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point p0 = new Point(1, 1);
        Point p1 = new Point(2, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(1, 2);
        Point p4 = new Point(2, 3);
        
        System.out.println();
        System.out.println("Comparing Slopes");
        System.out.println("p0, p1 = " + p0.slopeTo(p1));
        System.out.println("p0, p2 = " + p0.slopeTo(p2));
        System.out.println("p0, p3 = " + p0.slopeTo(p3));
        System.out.println("p0, p0 = " + p0.slopeTo(p0));
        System.out.println("p1, p0 = " + p1.slopeTo(p0));
        System.out.println("p1, p3 = " + p1.slopeTo(p3));
        System.out.println("Testing slope order");
        System.out.println("p0 -> p2, p4 = " + p0.SLOPE_ORDER.compare(p2, p4));
        System.out.println("Test Finished!!!");
    }
    
    class MyComparator implements Comparator<Point>
    {
    	public MyComparator (Point p0)
    	{
    		this.p0 = p0;
    	}
    	Point p0;
    	
    	public void setP0 (Point p0)
    	{
    		this.p0 = p0;
    	}
    	
    	public Point getP0 (Point p0)
    	{
    		return this.p0;
    	}

		@Override
		public int compare(Point p1, Point p2) {
			double s1 = p0.slopeTo(p1);
			double s2 = p0.slopeTo(p2);
			
			if (s1 < s2)
				return -1;
			else if (s1 == s2)
				return 0;
			else
				return 1;
		}
    	
    }
}
