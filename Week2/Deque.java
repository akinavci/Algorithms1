import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> 
{
	Node first;
	Node last;
	int size;
	
   public Deque()                           // construct an empty deque
   {
	   first = null;
	   last = null;
	   size = 0;
   }
   public boolean isEmpty()                 // is the deque empty?
   {
	   if (first == null)
		   return false;
	   else
		   return true;
   }
   public int size()                        // return the number of items on the deque
   {
	   return size;
   }
   public void addFirst(Item item)          // insert the item at the front
   {
	   if (item == null)
		   throw new NullPointerException();
	   
	   Node tmp = new Node();
	   
	   if (first == null)
	   {
		   tmp.next = null;
		   tmp.prev = null;
		   tmp.item = item;
		   
		   first = tmp;
		   last = tmp;
	   } else
	   {
		   // this is not the first node of the deque
		   tmp.next = first;
		   tmp.prev = null;
		   tmp.item = item;
		   
		   first.prev = tmp;
		   
		   first = tmp;
	   }
	   
	   size++;
   }
   public void addLast(Item item)           // insert the item at the end
   {
	   if (item == null)
		   throw new NullPointerException();
	   
	   Node tmp = new Node();
	   
	   if (first == null)
	   {
		   tmp.next = null;
		   tmp.prev = null;
		   tmp.item = item;
		   
		   first = tmp;
		   last = tmp;
	   } else
	   {
		   // this is not the first node of the deque
		   tmp.next = null;
		   tmp.prev = last;
		   tmp.item = item;
		   
		   last.next = tmp;
		   
		   last = tmp;
	   }
	   
	   size++;
	   return;
   }
   
   public Item removeFirst()                // delete and return the item at the front
   {
	   if (first == null)
		   throw new NoSuchElementException();
	   
	   else if (first.next == null)
	   {
		   // this is the only element
		   Node tmp = first;
		   
		   first = null;
		   last = null;
		   size = 0;
		   return tmp.item;
	   }
	   else
	   {
		   Node tmp = first;
		   
		   first = tmp.next;
		   first.prev = null;
		   size--;
		   
		   tmp.next = null;
		   tmp.prev = null;
		   
		   return tmp.item;
	   }
   }
   public Item removeLast()                 // delete and return the item at the end
   {
	   if (last == null)
		   throw new NoSuchElementException();
	   
	   else if (last.prev == null)
	   {
		   // this is the only element
		   Node tmp = last;
		   
		   first = null;
		   last = null;
		   size = 0;
		   return tmp.item;
	   }
	   else
	   {
		   Node tmp = last;
		   
		   last = tmp.prev;
		   last.next = null;
		   size--;
		   
		   tmp.next = null;
		   tmp.prev = null;
		   
		   return tmp.item;
	   }
   }
   
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
	   MyIterator<Item> it = new MyIterator<>(this);
	   return it;
   }
   public static void main(String[] args)   // unit testing
   {
	   // test
	   Deque<Integer> deq = new Deque<>();
	   
	   deq.addFirst(5);
	   deq.addFirst(10);
	   deq.addLast(2);
	   deq.addLast(4);
	   deq.removeLast();
	   deq.addLast(6);
	   deq.addFirst(15);
	   deq.removeFirst();
	   
	   Iterator<Integer> it = deq.iterator();
	   
	   System.out.println("Printing...");
	   for (int i : deq)
	   {
		   System.out.print(i + " ");
	   }
	   
	   System.out.println("\ndone..");
	   return;
   }
   
   class Node
   {
	   Node next;
	   Node prev;
	   Item item;
   }
   
   class MyIterator<Item> implements Iterator<Item>
   {
	   Deque<Item> tmpDeque;
	   Node current;
	   
	   public MyIterator(Deque deq) 
	   {
		   tmpDeque = deq;
		   current = (Node) tmpDeque.first;
	   }

		@Override
	    public boolean hasNext() {
		    if (current == null)
		    	return false;
		    else
		    	return true;
	    }
	
		@Override
	    public Item next() {
			Item tmp = (Item) current.item;
		    current = current.next;
		    return tmp;
	    }

		@Override
        public void remove() {
	        // TODO Auto-generated method stub
	        
        }
	    
    }
}