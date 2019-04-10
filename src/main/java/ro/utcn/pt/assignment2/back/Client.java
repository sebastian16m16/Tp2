package ro.utcn.pt.assignment2.back;

import java.time.LocalTime;
import java.util.Random;

public class Client {
    static long idCounter = 0;

    static long id;
    LocalTime arrivalTime;
    int serviceTime;
    

    public Client(LocalTime arrivalTime){
        this.id = idCounter++;
        this.arrivalTime = arrivalTime;
        
        Random r = new Random();
    	this.serviceTime = r.nextInt(3) + 2;
    }


	public long getId() {
		return id;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
    
	public String toString() {
		return "Client with id: " + id + " AT: " +arrivalTime + " ST: " + serviceTime;
	}
}
