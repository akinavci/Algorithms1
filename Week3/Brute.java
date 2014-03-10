
public class Brute {
	
	private static Point[] points;
	
	public static void main (String[] args)
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
			for (int j = i + 1; j < count; j++)
				for (int k = j + 1; k < count; k++)
					for (int l = k + 1; l < count; l++)
					{
						double s1, s2, s3;
						
						s1 = points[i].slopeTo(points[j]);
						s2 = points[i].slopeTo(points[k]);
						s3 = points[i].slopeTo(points[l]); 
						
						//System.out.println("Slopes: " + s1 + ", " + s2 + ", " + s3);
						if (s1 == s2 && s1 == s3)
						{
							points[i].drawTo(points[j]);
							points[i].drawTo(points[k]);
							points[i].drawTo(points[l]);
							
							System.out.println(points[i].toString() + " -> " +
							points[j].toString() + " -> " + 
							points[k].toString() + " -> " + 
							points[l].toString());
						}
					}
			
		
		
	}

}
