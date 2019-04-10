package ro.utcn.pt.assignment2.back;

import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

public class Client {
    long id;
    LocalTime arrivalTime;
    int serviceTime;
    

    public Client(LocalTime arrivalTime){
    	long num =(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        this.id = (long) (num / Math.pow(10, Math.floor(Math.log10(num)) - 4 + 1));
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
