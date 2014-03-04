import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int size = 0;
    private Item[] items;
    
    private int arrayLength = 16;
    

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        items = (Item[]) new Object[arrayLength];
        size = 0;
    }
    
    public boolean isEmpty()                 // is the queue empty?
    {
        return size == 0;
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
       items[index] = items[size-1];
       items[size-1] = null;
       size--;
       
       
       // after removing item, check if we need to resize the array
       if (size == arrayLength / 4 && size >= 16)
       {
           arrayLength = arrayLength / 4;
           
           Item[] tmpArr = (Item[]) new Object[arrayLength];
           
           for (int i = 0; i < size; i++)
           {
               tmpArr[i] = items[i];
           }
           items = tmpArr;
       }
       
       return tmp;
   }
   public Item sample()                     // return (but do not delete) a random item
   {
       if (size == 0)
           throw new NoSuchElementException();
       
       // find a random number
       int index = StdRandom.uniform(0, size);
       
       return items[index];
       
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
       MyIterator<Item> it = new MyIterator<Item>(items, size);
       return it;
   }
   public static void main(String[] args)   // unit testing
   {
       RandomizedQueue<Integer> rQueue = new RandomizedQueue<Integer>();
       
       rQueue.enqueue(1);
       rQueue.enqueue(2);
       rQueue.enqueue(3);
       rQueue.enqueue(4);
       rQueue.enqueue(5);
       
       System.out.println("<< " + rQueue.dequeue());
       System.out.println("<< " + rQueue.dequeue());
       System.out.println("<< " + rQueue.dequeue());
       
       rQueue.enqueue(6);
       rQueue.enqueue(7);
       rQueue.enqueue(8);
       
       System.out.println("Testing iterator");
       for (int i : rQueue)
       {
           System.out.print(i + " ");
       }
       System.out.println("\nDONE");
       
       //return;
   }
   
   @SuppressWarnings("hiding")        
   private class MyIterator<Item> implements Iterator<Item>
   {
       private int index, size;
       private Item[] items;
       
       public MyIterator(Item[] items, int size)
       {
           this.items = items;
           index = 0;
           this.size = size;
       }
       
        @Override
        public boolean hasNext() 
        {
            return index <= size - 1;
        }        
    
        @Override
        public Item next() {
            
            if (index == size)
                throw new NoSuchElementException();
            
            // find a random value between index and size
            int random = StdRandom.uniform(index, size);
            
            Item tmp = items[random];
            items[random] = items[index];
            items[index] = tmp;
            index++;
            return items[index-1];
        }
    
        @Override
        public void remove() {
            // TODO Auto-generated method stub    
            throw new UnsupportedOperationException();
        }       
   }
}