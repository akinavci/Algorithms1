public class Fast {
	
	private static Point[] points;
	private static Double[] slopes;
	
	public static void main(String[] args)
	{
		if (args.length != 1)
			System.err.println("must enter filename");
		String filename = args[0].toString();
		
		In reader = new In(filename);
		
		int count = reader.readInt();
		
		points = new Point[count];
		
		int x, y;
		Point tmp;
		
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		
		
		for (int i = 0; i < count; i++)
		{
			x = reader.readInt();
			y = reader.readInt();
			
			tmp = new Point(x, y);
			
			points[i] = tmp;
			
			tmp.draw();
		}
		
		
		
		for (int i = 0; i < count; i++)
		{
			
		}
		
	}
	
	private void quickPointSort(Point[] points, int pivotIndex, int ilk, int son)
	{
		int lo = ilk, hi = ilk;
		Double refSlope = points[pivotIndex].slopeTo(points[ilk]);
		Double tmpSlope = 0.0;
		for (int i = ilk; i <= son; i++)
		{
			tmpSlope = points[pivotIndex].slopeTo(points[i]);
			if (tmpSlope == refSlope)
			{
				
			}
		}
	}

}
