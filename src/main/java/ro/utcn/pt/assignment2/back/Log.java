package ro.utcn.pt.assignment2.back;

public class Log {
    public final String queueName;
    public final String message;
    public final Client client;

    public Log(Client client, String message, String queueName) {
        this.client = client;
        this.message = message;
        this.queueName = queueName;
    }
}
