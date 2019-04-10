package ro.utcn.pt.assignment2.back;

public class ClockTime {
	
	int timer;
	
	public synchronized void increment(){
		timer++;
	}

	public synchronized void decrement(){
		timer--;
	}

	public synchronized int getTime(){
		return timer;
	}

	public synchronized void setTime(int setter){
		timer = setter;
	}
	
}
