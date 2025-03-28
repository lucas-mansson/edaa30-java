package game;

public class Board {
	private int noPins;
	
	public Board() {
		
	}
	
	public void setUp(int a) {
		noPins = a; //antal stickor som ska vara i högen
	}
	
	public void takePins(int a) {
		noPins = noPins-a;
	}
	
	public int getNoPins() {
		return noPins;
	}
	
	

}
