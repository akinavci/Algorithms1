public class PercolationStats
{
   private int N;
   private int T;
   
   private double mean = 0;
   private double stdDev = 0;
   
   private Percolation perc;
   private double[] result;
   
   public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
   {
      if (N <= 0 || T <= 0)
         throw new IllegalArgumentException();
      
      this.T = T;
      this.N = N;
      
      result = new double[T];
      
      
      for (int exp = 0; exp < T; exp++)
      {
         //System.out.println("Starting Exp #"+ (exp+1));
         
         perc = new Percolation(N);
         
         
         for (int index = 0; index < (N*N); index++)
         {
            int i, j;
            
            do
            {
               i = StdRandom.uniform(N) + 1;
               j = StdRandom.uniform(N) + 1;
               
               //System.out.println("[" + i + "," + j + "] -> XXX");            
            }
            while (perc.isOpen(i, j));
            //System.out.println("[" + i + "," + j + "] -> blocked");
            
            // open this site
            perc.open(i, j);
            //System.out.println("[" + i + "," + j + "] -> opened");
            
            if (perc.percolates())
            {
               result[exp] = (double) (index+1) / (N*N);
               //System.out.println("Percolates = " + (index+1));
               break;
            }
         }
      }
      
      System.out.println("mean                  =" + mean());
      System.out.println("stddev                  =" + stddev());
      System.out.println("95% confidence interval          =" + confidenceLo() + ", " + confidenceHi());
   }
   public double mean()                     // sample mean of percolation threshold
   {
      //double mean;
      double sum = 0;
      
      for (int i = 0; i < T; i++)
      {
         sum += result[i];
      }
      
      mean = sum / T;
      
      return mean;
   }
   public double stddev()                   // sample standard deviation of percolation threshold
   {
      double sum = 0;
      
      for (int i = 0; i < T; i++)
      {
         sum += (result[i] - mean) * (result[i] - mean);
      }
      stdDev = sum / (T-1);
      return Math.sqrt(stdDev);
   }
   public double confidenceLo()             // returns lower bound of the 95% confidence interval
   {
      double conf = 1.96;
      conf = conf * stdDev / Math.sqrt(T);
      conf = mean - conf;
      return conf;
   }
   public double confidenceHi()             // returns upper bound of the 95% confidence interval
   {
      double conf = 1.96;
      conf = conf * stdDev / Math.sqrt(T);
      conf = mean + conf;
      return conf;
   }
   public static void main(String[] args)   // test client, described below
   {
      int n = 0, t = 0;
      if (args.length != 2)
      {
         System.out.println("provide N and T values");
         return;
      }
      
      try
      {
         n = Integer.parseInt(args[0]);
         t = Integer.parseInt(args[1]);
         //System.out.println(n + ", " + t);
      }
      catch (IndexOutOfBoundsException e)
      {
         System.err.println(e.toString());
      }
      
      PercolationStats stats = new PercolationStats(n, t);
   }
}