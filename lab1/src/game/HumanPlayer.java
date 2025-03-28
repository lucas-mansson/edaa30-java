package game;

public class HumanPlayer extends Player{

	public HumanPlayer(String userId) {
		super(userId);
	}

	
	public String getUserId() {
		return userId;
	}

	
	public int takePins(Board b) {
		
		while(true) {
			int a = UserInterface.askForInt("Ta en eller två stickor. Det finns " + b.getNoPins() + " stickor kvar");
			
			if(a==1 | a==2) {
				b.takePins(a);
				return a;
			}else if(a==-2){
				System.exit(0);
			}else{
				UserInterface.printMessage("Felaktig input");
			}
		}
	}
}
