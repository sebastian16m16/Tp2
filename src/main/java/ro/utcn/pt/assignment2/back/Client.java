package ro.utcn.pt.assignment2.back;

import java.util.Random;

public class Client {
    static long id = 0;
    int arrivalTime;
    int serviceTime;
    

    public Client(long iD2, int AT){
        this.id = iD2;
        this.arrivalTime = AT;
        
        Random r = new Random();
    	this.serviceTime = r.nextInt(((1000 - 60) + 1)+ 60);
    }


	public long getId() {
		return id;
	}


	public int getArrivalTime() {
		return arrivalTime;
	}


	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
    
	public String toString() {
		return "Client with id: "+id + " AT: " +arrivalTime + " ST: " + serviceTime;
	}
}
