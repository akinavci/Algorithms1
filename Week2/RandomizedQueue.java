import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private int size = 0;
	private Item[] items;
	
	private int arrayLength = 16;
	
	@SuppressWarnings("unchecked")
    public RandomizedQueue()                 // construct an empty randomized queue
	{
		items = (Item[])new Object[arrayLength];
		size = 0;
	}
	
	public boolean isEmpty()                 // is the queue empty?
	{
		if (size == 0)
			return true;
		else
			return false;
	}
   
   public int size()                        // return the number of items on the queue
   {
	   return size;
   }
   public void enqueue(Item item)           // add the item
   {
	   if (item == null)
		   throw new NullPointerException();
	   
	   if (size == arrayLength)
	   {
		   // we need to extend the array size
		   arrayLength = arrayLength * 2;
		   Item[] tmp = (Item[]) new Object[arrayLength]; 
		   
		   for (int i = 0; i < size; i++)
		   {
			   tmp[i] = items[i];
			   items = tmp;
		   }
	   }
	   // now ready to add a new item
	   
	   items[size] = item;
	   size++;
   }
   public Item dequeue()                    // delete and return a random item
   {
	   if (size == 0)
		   throw new NoSuchElementException();
	   
	   // find a random number
	   int index = StdRandom.uniform(0, size);
	   
	   Item tmp = items[index];
	   size--;
	   
	   
	   // after removing item, check if we need to resize the array
	   if (size == arrayLength / 4)
	   {
		   
	   }
   }
   public Item sample()                     // return (but do not delete) a random item
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   public static void main(String[] args)   // unit testing
}