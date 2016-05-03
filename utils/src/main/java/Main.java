/**
 * Created by rexer.
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World".lastIndexOf(" ", 2));
	}

	public static double area(double radius) {
		if (radius <= 0 || radius == Double.NaN)
			throw new IllegalArgumentException();
		else
			return Math.PI * Math.pow(radius, 2);
	}
}
