package ro.utcn.pt.assignment2.back;


import java.time.LocalTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

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
            while (this.store.cancelationFlag.get()) {
                Client c = dequeue();
                this.process(c);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupt");
            System.out.println(e.toString());
        }
    }


    public synchronized void process(Client c) throws InterruptedException {
        LocalTime startTime = LocalTime.now();

        this.wait((long)c.serviceTime * 1000);

        LocalTime endTime = LocalTime.now();

        String message = String.format("[%d]: deservit de casa {%s}. [Arrival time]: %s. [Processing started]: %s. [Processing ended]: %s. [Time processed]: %d. [Waited in queue]: %d.", c.getId(), this.name, c.arrivalTime.toString(), startTime.toString(), endTime.toString(), startTime.until(endTime, SECONDS), c.arrivalTime.until(endTime, SECONDS));

        Log log = new Log(c, message, this.name);

        store.GetLog(log);

        notifyAll();
    }

    public synchronized Client dequeue() throws InterruptedException {
        while (queue.isEmpty() && !this.store.cancelationFlag.get())
            wait();

        Client c = queue.take();
        return c;
    }
}
