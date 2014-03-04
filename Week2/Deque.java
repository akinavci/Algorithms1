import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> 
{
    private Node first;
    private Node last;
    private int size;
    
   public Deque()                           // construct an empty deque
   {
       first = null;
       last = null;
       size = 0;
   }
   public boolean isEmpty()                 // is the deque empty?
   {
       return first == null;
//       if (first == null)
//           return true;
//       else
//           return false;
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
   
   private class Node
   {
       private Node next;
       private Node prev;
       private Item item;
   }
   
   private class MyIterator<Item> implements Iterator<Item>
   {
       private Deque<Item> tmpDeque;
       private Node current;
       
       public MyIterator(Deque deq) 
       {
           tmpDeque = deq;
           current = (Node) tmpDeque.first;
       }

        @Override
        public boolean hasNext() {
            return current != null;
//            if (current == null)
//                return false;
//            else
//                return true;
        }
    
        @Override
        public Item next() {        
            
            if (current == null)
                throw new NoSuchElementException();
            
            Item tmp = (Item) current.item;
            current = current.next;
            return tmp;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException();
        }
        
    }
}