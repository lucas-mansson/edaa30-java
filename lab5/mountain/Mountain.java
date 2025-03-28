package mountain;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal{
	private Point a; //punkten nere vänster
	private Point b; // punkten högst upp
	private Point c; //punkten nere höger
	private int dev; //parameter till randFunc som anger hur "taggigt" berget ska bli
	private Map<Side, Point> sideMap; // map som håller koll på mittpunkterna till sidorna
	
	public Mountain(int dev, Point a, Point b, Point c) {
		super();
		this.a = a; 
		this.b = b; 
		this.c = c; 
		this.dev = dev;
		sideMap = new HashMap<Side, Point>();
	}
	
	/**
	 * Returns the title.
	 * @return the title
	 */
	public String getTitle() {
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(a.getX(), a.getY()); // börjar i a
		fractalTriangle(turtle, order, dev, a, b, c); //det som faktiskt ritar trianglarna
	}
	
	
	public void fractalTriangle(TurtleGraphics turtle, int order, int dev, Point a, Point b, Point c) {
		
		if(order == 0) { //ritar en standard triangel.a -> b -> c -> a
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		}else {
			Point midLeft = getMid(a, b, dev); //beräknar mittenpunkterna på alla linjerna i triangeln, då det är utgångspunkt för nästa ordning
			Point midRight = getMid(b, c, dev);
			Point midBottom = getMid(a, c, dev);
			
			dev = dev/2; //halvera dev för att få en mer "naturlig" look
			
			//topp
			fractalTriangle(turtle, order - 1, dev, midLeft, b, midRight); //ritar motsvarande trianglar men med de nya mittpunkterna som utgångspunkter
			// Mitten
			fractalTriangle(turtle, order - 1, dev, midLeft, midRight, midBottom);
			// Botten vänster
			fractalTriangle(turtle, order - 1, dev, a, midLeft, midBottom);
			// Botten höger
			fractalTriangle(turtle, order - 1, dev, midBottom, midRight, c);
		}
		
	}
	
	public Point getMid(Point p1, Point p2, double dev) {
		
		Side tempSide = new Side(p1, p2); //skapar en sida att jämföra med nycklarna i mappen
		
		if(sideMap.containsKey(tempSide)) { //kollar ifall sidan finns i mapen
			
			Point tempMid = sideMap.get(tempSide); //hämtar i så fall sidan
			sideMap.remove(tempSide); // tar bort sidan från mappen för att underlätta sökningar
			
			return tempMid; //returnerar värdet (mittpunkten) för sida, ifall det finns
			
		}else { //annars beräknas nya mittpunkten
			
			double r = RandomUtilities.randFunc(dev);
			
			Point mid = new Point((int)((p1.getX() + p2.getX())/2.0),(int)((p1.getY() + p2.getY()) / 2.0 + r));
			
			sideMap.put(tempSide, mid); 
			
			return mid;
		}
	
	}
}
