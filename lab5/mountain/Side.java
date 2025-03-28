package mountain;

import java.awt.Point;

public class Side {
	
	private Point p1;
	private Point p2;
	
	public Side(java.awt.Point p1, java.awt.Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getP1() {
		return p1;
	}
	
	public Point getP2() {
		return p2;
	}
	
	@Override
	public boolean equals(Object e) {
		if(this == e) {
			return true;
		}
		
		Side side2 = (Side) e;
		
		if((p1.equals(side2.getP1()) || p1.equals(side2.getP2())) && (p2.equals(side2.getP1()) || p2.equals(side2.getP2()))) {
			return true;
		}
	
		return false;
	}
	
	@Override
	public int hashCode() {
	return p1.hashCode() + p2.hashCode();
	}

}
