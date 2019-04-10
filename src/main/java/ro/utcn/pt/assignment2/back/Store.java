package ro.utcn.pt.assignment2.back;


import ro.utcn.pt.assignment2.front.ApplicationUI;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Store implements Runnable {
    private final ApplicationUI ui;
    public final AtomicBoolean cancelationFlag;
    ArrayList<ServiceProcessor> queues = new ArrayList<>();
    public BlockingQueue<Client> clients = new ArrayBlockingQueue<Client>(50);

    public Store(int queues, int nrOfClients, ApplicationUI ui, AtomicBoolean cancelationFlag) throws InterruptedException {
        generateQueues(queues);
        generateClients(nrOfClients, false);
        this.ui = ui;
        this.cancelationFlag = cancelationFlag;
    }

    private void generateQueues(int nrOfQueues) {
        for (int i = 1; i <= nrOfQueues; i++) {
            queues.add(new ServiceProcessor("Q" + i, clients, this));
        }
    }

    public void GetLog(Log log) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ui.react(log);
            }
        });
    }

    public synchronized void generateClients(int nrOfClients, boolean sleep) throws InterruptedException {
        for(int i = 0 ; i < nrOfClients; i++) {
            Client c = new Client(LocalTime.now());
            clients.put(c);
            if (sleep) {
                Thread.sleep((int)(Math.random() * 3) + 100);
            }
        }
    }

    public void run() {
        for(ServiceProcessor sp : queues){
            new Thread(sp).start();
        }

        while (cancelationFlag.get()){
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
