package ro.utcn.pt.assignment2.back;


import java.util.Vector;

public class Queue extends Thread {

    Queue q1;
    ClockTime cTime;
    ClockTime count;
    String name;

    public Queue(String name, ClockTime cTime, ClockTime count) {
        this.cTime = cTime;
        this.count = count;
        setName(name);
    }

    public Queue(Queue q1, String name, ClockTime cTime, ClockTime count) {
        this.q1 = q1;
        this.cTime = cTime;
        this.count = count;
        setName(name);
    }

    Vector<Client> thisQueue = new Vector<Client>();

    public void run() {                
                
        try {
            		
        	while (true) {
            			
	        Thread.sleep(1000);
		    }
        }catch(InterruptedException e) {
            		System.out.println("Interrupt");
            		System.out.println( e.toString()); 
        }
    }       
            
    
    public synchronized void enqueue(Client client) throws InterruptedException{
        thisQueue.addElement(client);
        notifyAll(); 
    }

    public synchronized void dequeue() throws InterruptedException{

        while(thisQueue.size()==0)
            wait();
        Client c = (Client) thisQueue.elementAt(0);
        System.out.println(Long.toString( c.getId())+" a fost deservit de casa "+getName());
        notifyAll();



    }

      
    public synchronized long getSize() throws InterruptedException{
        notifyAll();
        long size = thisQueue.size();
        return size;
    }

    public int getCount(){
        return count.getTime();
    }
}
