package ro.utcn.pt.assignment2.back;



public class Store extends Thread {

    private Queue q[];
    private int queues;
    static long ID =0;
    private int nr_clients;//Numar clienti care trebuie deserviti de catre ghisee
   
    public Store( int queues, Queue q[], String name, int nr_clients ){
    
        setName(name);
        this.queues = queues;
        this.q = new Queue[queues];
        this.nr_clients = nr_clients;
        for( int i=0; i<queues; i++){
        this.q[i] =q[i] ; 

        }
    }
 
    private int min_index (){
    int index = 0;
        try
        {
            long min = q[0].getSize();

            for( int i=1; i<queues; i++){
                long lung = q[ i ].getSize();

            if ( lung < min ){
            min = lung;
            index = i;
            }
            }
        }
            catch( InterruptedException e ){
            System.out.println( e.toString());
        }
        return index;
    }

    public void run(){
        try
        {
            int i=0;
            while( i < nr_clients ){
                i++;
                Client c = new Client( ++ID, 15);
                int m = min_index();
                System.out.println("Client :" +Long.toString( ID )+" added to queue "+
                Integer.toString(m));
                q[ m ].enqueue(c);
                sleep( (int) (Math.random()*1000) );
            }
        }
            catch( InterruptedException e ){
            System.out.println( e.toString());
        }
    } 
    }
