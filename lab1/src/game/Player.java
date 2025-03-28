package game;

public abstract class Player {
	public String userId;
	
	public Player(String userId) {
		this.userId=userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public abstract int takePins(Board b);
	
}
