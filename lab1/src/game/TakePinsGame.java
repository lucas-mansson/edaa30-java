package game;

public class TakePinsGame {

	public static void main(String[] args) {
		
		//setup
		Board board = new Board();
		board.setUp(21);
		HumanPlayer human = new HumanPlayer("Du");
		Player computer = null;
		
		UserInterface.printMessage("Välkommen till detta spelet! Vinn mot datorn genom att ta den sista stickan från högen! Du kan endast ta en eller två åt gången!");
		
		//välj svårighetsgrad genom att ändra vilket typ av player-objekt som skapas. Default är den "lättare"
		int difficulty = UserInterface.difficulty();
		if(difficulty == 2) {
			computer = new SmartComputerPlayer("Datorn");
		}else if(difficulty == 1){
			computer = new ComputerPlayer("Datorn");
		}else if(difficulty == 0) {
			System.exit(0);
		}
		
		UserInterface.printMessage("Brädet har just nu " + board.getNoPins() + " stickor!");
		
		UserInterface.printMessage(human.getUserId() + " börjar!");
		
		while(true) {
			//spelaren börjar
			human.takePins(board);
			//om det tar slut efter spelaren plockar är spelet vunnit
			if(board.getNoPins() < 1) {
				UserInterface.printMessage("Du vann!");
				break;
			}
			
			computer.takePins(board);
			//samma princip för datorn
			if(board.getNoPins() < 1) {
				UserInterface.printMessage("Datorn vann :(");
				break;
			}
		}
		
	}
}
