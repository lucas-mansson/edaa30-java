package fractal;

import java.awt.Point;

import koch.Koch;
import mountain.Mountain;

public class FractalApplication {
	public static void main(String[] args) {
		Point a = new Point(100, 450);
		Point b = new Point(250, 100);
		Point c = new Point(500, 500);
		Fractal[] fractals = new Fractal[2];
		fractals[1] = new Koch(300);
		fractals[0] = new Mountain(30, a, b, c);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
