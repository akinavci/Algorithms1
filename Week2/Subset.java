public class Subset 
{
   public static void main(String[] args)
   {
       String tmp = "";        
       if (args.length != 1)
           System.out.println("Wrong input");
       
       int k = Integer.parseInt(args[0]);        
       
       RandomizedQueue<String> rQueue = new RandomizedQueue<String>();
       
       while (!StdIn.isEmpty())
       {
           tmp = StdIn.readString();
           
           //System.out.println(tmp);
           
           rQueue.enqueue(tmp);
       }
       
       for (int i = 0; i < k; i++)
       {
           System.out.println(rQueue.dequeue());
       }
       
       //System.out.println("\nOUT");
   }
}