public class Percolation {
	
	Site [][]percArray;
	int N;
	
	public Percolation(int N){
		// create N-by-N grid, with all sites blocked
		percArray = new Site[N][N];
		this.N = N;
		
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
			{
				percArray[i][j].setBlocked(true);
				percArray[i][j].setSiteNo((i-1)*N + j);
			}
	}
	   public void open(int i, int j)         // open site (row i, column j) if it is not already
	   {
		   if(i<1 || i>N || j<1 || j>N)
			   throw new IndexOutOfBoundsException();
		   
		   if(!percArray[i][j].isBlocked())
		   {
			   // already open so
			   return;
		   }
		   else
		   {
			   if(i != 1 && !percArray[i-1][j].isBlocked())
			   {
				   //union
			   }
			   if(j != 1 && !percArray[i][j-1].isBlocked())
			   {
				   //union
			   }
			   if(i != N && !percArray[i+1][j].isBlocked())
			   {
				   //union
			   }
			   if(j != N && !percArray[i][j+1].isBlocked())
			   {
				   //union
			   }
		   }
		   //percArray[i][j] = 0;
	   }
	   
	   private void union(int i, int j, int openI, int openJ)
	   {
		   int value = percArray[i][j].getSiteNo();
	   }
	   public boolean isOpen(int i, int j)    // is site (row i, column j) open?
	   {
		   if(i<1 || i>N || j<1 || j>N)
			   throw new IndexOutOfBoundsException();
		   
		   return !percArray[i][j].isBlocked();
	   }
	   public boolean isFull(int i, int j)    // is site (row i, column j) full?
	   {
		   if(i<1 || i>N || j<1 || j>N)
			   throw new IndexOutOfBoundsException();
		   
		   return true;
	   }
	   public boolean percolates()            // does the system percolate?
	   {
		   return true;
	   }
    
}

class Site {
	
	private int siteNo;

	public int getSiteNo() {
		return siteNo;
	}

	public void setSiteNo(int siteNo) {
		this.siteNo = siteNo;
	}
	
	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	private boolean isBlocked;
	

}


