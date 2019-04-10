package ro.utcn.pt.assignment2.back;


import java.util.concurrent.BlockingQueue;

public class ServiceProcessor implements Runnable {
    private final BlockingQueue<Client> queue;
    private final Store store;
    private String name;

    public ServiceProcessor(String name, BlockingQueue<Client> queue, Store store) {
        this.queue = queue;
        this.name = name;
        this.store = store;
    }

    public void run() {
        try {
            while (true) {
                Client c = dequeue();
                this.process(c);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupt");
            System.out.println(e.toString());
        }
    }


    public synchronized void process(Client c) throws InterruptedException {
        this.wait((long)c.serviceTime);
        System.out.println();

        Log log = new Log(c, c.getId() + " a fost deservit de casa " + name, this.name);

        store.GetLog(log);

        notifyAll();
    }

    public synchronized Client dequeue() throws InterruptedException {
        while (queue.isEmpty())
            wait();

        Client c = queue.take();
        return c;
    }
}
