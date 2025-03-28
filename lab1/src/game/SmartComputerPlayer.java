package game;

public class SmartComputerPlayer extends Player {

	public SmartComputerPlayer(String userId) {
		super(userId);
	}

	public String getUserId() {
		return userId;
	}

	public int takePins(Board b) {

		int a = 1;
		
		if((b.getNoPins()-1)%3 == 0) { //tanken för att datorn alltid ska vinna är att antalet stickor den tar adderat med det antalet spelaren tar ska bli lika med 3
			a = 1;
		}else {
			a=2;
		}
		b.takePins(a);
		UserInterface.printMessage("Datorn tar " + a + " stickor. Det finns nu " + b.getNoPins() + " stickor kvar");
		return a;
	}
	
	

}
