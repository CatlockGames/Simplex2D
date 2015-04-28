/**
 * 
 */
package math;

/**
 * @author Aaron
 * @author Ryan
 *
 */
public class Vector2d {
	public double x;
	public double y;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @param v
	 */
	public Vector2d(Vector2d v) {
		this.x = v.x;
		this.y = v.y;
	}
	
	/**
	 * 
	 * @return
	 */
	public double magnitude() {	
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * 
	 * @param angle
	 * @return
	 */
	public Vector2d rotate(double angle) {
		return new Vector2d(Math.cos(Math.toRadians(angle)) * x - Math.sin(Math.toRadians(angle)) * y, Math.sin(Math.toRadians(angle)) * x + Math.cos(Math.toRadians(angle)) * y);
	}
	
	/**
	 * 
	 * @param v
	 * @return
	 */
	public Vector2d add(Vector2d v) {
		return new Vector2d(x + v.x, y + v.y);
	}
	
	/**
	 * 
	 * @param v
	 * @return
	 */
	public Vector2d subtract(Vector2d v) {
		return new Vector2d(x - v.x, y - v.y);
	}
	
	/**
	 * 
	 * @param scale
	 * @return
	 */
	public Vector2d scale(double scale) {
		return new Vector2d(x * scale, y * scale);
	}
}
