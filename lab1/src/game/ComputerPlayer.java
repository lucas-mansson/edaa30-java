package game;
import java.util.Random;

public class ComputerPlayer extends Player{
	Random rand = new Random();

	public ComputerPlayer(String userId) {
		super(userId);
	}

	
	public String getUserId() {
		return userId;
	}

	
	public int takePins(Board b) {
		int a = rand.nextInt(1,3);
		
		if(b.getNoPins() == 1) {
			b.takePins(1);
		}else {
			b.takePins(a);
		}
		
		UserInterface.printMessage("Datorn tar "+ a +" stickor. Det finns nu "+ b.getNoPins() +" stickor kvar!");

		return a;
	}

}
