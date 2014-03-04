public class Percolation
{
   private int N;
   private WeightedQuickUnionUF uf;
   private boolean[] openArray;
   
   public Percolation(int N)              // create N-by-N grid, with all sites blocked
   {
      this.N = N;
      
      uf = new WeightedQuickUnionUF(N * N);
      openArray = new boolean[N * N];
      
      //assert uf.count() == N * N : "uf created!";
      
      //System.out.println("Component count= " + uf.count());
      
   }
   
   private void checkSurrounding(int i, int j)
   {
      int location = (i-1)*N + (j-1);
      
      int up = -1;
      int down = -1;
      int left = -1;
      int right = -1;
      
      //System.out.println("checkSurrounding = " + i + ", " + j);
            
      if (i > 1)
         up = (i-2)*N + (j-1);
      if (i < N)
         down = (i)*N + (j-1);
      if (j > 1)
         left = (i-1)*N + (j-2);
      if (j < N)
         right = (i-1)*N + (j);
         
      if (up != -1 && openArray[up] && !uf.connected(location, up))
      {
         uf.union(location, up);
         checkSurrounding(i-1, j);
      }
          
      if (down != -1 && openArray[down]  && !uf.connected(location, down))
      {
         uf.union(location, down);
         checkSurrounding(i+1, j);
      }
          
      if (left != -1 && openArray[left] && !uf.connected(location, left))
      {
         uf.union(location, left);
         checkSurrounding(i, j-1);
      }
          
      if (right != -1 && openArray[right] && !uf.connected(location, right))
      {
         uf.union(location, right);
         checkSurrounding(i, j+1);
      }
   }
   
   public void open(int i, int j)         // open site (row i, column j) if it is not already
   {
      if (i < 1 || i > N || j < 1 || j > N)
         throw new IndexOutOfBoundsException();
      
      int location = (i-1)*N + (j-1);
      
      if (openArray[location])
         return;            // already open
      
      openArray[location] = true;
      
      checkSurrounding(i, j);
      
      //assert false : uf.count();
      //System.out.println("Count after union = " + uf.count());
   }
   public boolean isOpen(int i, int j)    // is site (row i, column j) open?
   {
      if (i < 1 || i > N || j < 1 || j > N)
         throw new IndexOutOfBoundsException();
      int location = (i-1)*N + (j-1);
      
      return (openArray[location]);
   }
   
   //int[] id;
   
   public boolean isFull(int i, int j)    // is site (row i, column j) full?
   {
      if (i < 1 || i > N || j < 1 || j > N)
         throw new IndexOutOfBoundsException();
      
      int location = (i-1)*N + (j-1);
      
      //System.out.println("Location: " + i + ", " + j);
      
      //id = uf.getID();
      
      //printArray(openArray);
      
      //printArray(id);
      
      // check if it is connected to the top row
      if (!isOpen(i, j))
         return false;
      
      for (int index = 0; index < N; index++)
      {
         if (uf.connected(location, index))
            return true;
      }
      return false;
   }
   
//   private void printArray(boolean[] arr)
//   {
//      for (int i = 0; i < N; i++)
//      {
//         for (int j = 0; j < N; j++)
//         {
//            int location = (i)*N + (j);
//            
//            System.out.print( ( arr[location] ? 1 : 0 ) + " ");
//         }
//         System.out.println("");
//      }
//      System.out.println("");
//      System.out.println("");
//   }
//   
//   private void printArray(int[] arr)
//   {
//      for (int i = 0; i < N; i++)
//      {
//         for (int j = 0; j < N; j++)
//         {
//            int location = (i)*N + (j);
//            
//            System.out.print( ( arr[location] ) + " ");
//         }
//         System.out.println("");
//      }
//      System.out.println("");
//      System.out.println("");
//   }
   
   public boolean percolates()            // does the system percolate?
   {
      // check the bottom row if it is full
      for (int i = 1; i <= N; i++)
      {
         // see if it is open
         if (isFull(N, i))
            return true;
            
      }
      return false;
   }
}