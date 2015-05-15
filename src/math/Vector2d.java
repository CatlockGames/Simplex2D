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
	public double magnitude;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
		this.magnitude = Math.sqrt(dot(this));
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
	 * @param angle
	 * @return
	 */
	public Vector2d rotate(double angle) {
		return new Vector2d(x * Math.cos(Math.toRadians(angle)) - y * Math.sin(Math.toRadians(angle)), x * Math.sin(Math.toRadians(angle)) + y * Math.cos(Math.toRadians(angle)));
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
	
	/**
	 * 
	 * @param v
	 * @return
	 */
	public double dot(Vector2d v) {
		return x * v.x + y * v.y;
	}
	
	/**
	 * 
	 * @param v
	 * @return
	 */
	public Vector2d project(Vector2d v) {
		return v.scale(dot(v) / Math.pow(v.magnitude, 2));
	}
	
}
