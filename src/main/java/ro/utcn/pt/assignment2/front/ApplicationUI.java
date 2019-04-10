package ro.utcn.pt.assignment2.front;

import ro.utcn.pt.assignment2.back.Client;
import ro.utcn.pt.assignment2.back.Log;
import ro.utcn.pt.assignment2.back.Store;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ApplicationUI {
    private Store store;
    private JPanel panelMail;
    private JList q1;
    private JList q2;
    private JList q3;
    private JList q4;
    private JTextArea log;
    private JList waitingClients;
    private JButton startStoreButton;
    private JButton stopStoreButton;
    private JButton generateButton;
    private JSpinner numberOfClients;

    private DefaultListModel<Client> q1List = new DefaultListModel<>();
    private DefaultListModel<Client> q2List = new DefaultListModel<>();
    private DefaultListModel<Client> q3List = new DefaultListModel<>();
    private DefaultListModel<Client> q4List = new DefaultListModel<>();
    private DefaultListModel<Client> waitingListModel = new DefaultListModel<Client>();


    AtomicBoolean cancelationFlag = new AtomicBoolean();

    private Map<String, DefaultListModel> mapLists = new HashMap<>();

    public ApplicationUI() throws InterruptedException {
        this.initialize();

        ApplicationUI self = this;
        startStoreButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearValues();

                cancelationFlag = new AtomicBoolean(true);
                try {
                    store = new Store(4, 50, self, cancelationFlag);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                super.mouseClicked(e);
                new Thread(store).start();
            }
        });
        stopStoreButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                cancelationFlag.set(false);
            }
        });
        generateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if ((int) numberOfClients.getValue() > 0 && (int) numberOfClients.getValue() < 50) {
                    try {
                        store.generateClients((int)numberOfClients.getValue(), false);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    private void clearValues() {
        for (Map.Entry<String, DefaultListModel> v : this.mapLists.entrySet()){
            v.getValue().clear();
        }

        if (this.store != null && this.store.clients != null) {
            this.store.clients.clear();
        }

        this.waitingClients.setModel(new DefaultListModel());
        this.log.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    ApplicationUI gui = new ApplicationUI();

                    JFrame frame = new JFrame("My awesome app");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(gui.panelMail);
                    frame.pack();
                    frame.setVisible(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void react(Log log) {
        this.setupWaitingClients();
        this.processTerminatedClients(log);
        processLogName(log);
    }

    private void processTerminatedClients(Log log) {
        DefaultListModel<Client> listModel = this.mapLists.get(log.queueName);

        listModel.addElement(log.client);
    }

    private void processLogName(Log log) {
        this.log.append(log.message + "\n");
    }

    private void initialize() {
        q1.setModel(q1List);
        q2.setModel(q2List);
        q3.setModel(q3List);
        q4.setModel(q4List);

        this.mapLists.put("Q1", q1List);
        this.mapLists.put("Q2", q2List);
        this.mapLists.put("Q3", q3List);
        this.mapLists.put("Q4", q4List);
    }

    private void setupWaitingClients() {
        this.waitingListModel = new DefaultListModel<>();

        for (Object c : store.clients.toArray()) {
            waitingListModel.addElement((Client)c);
        }

        this.waitingClients.setModel(this.waitingListModel);
    }
}
