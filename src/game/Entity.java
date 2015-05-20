/**
 * 
 */
package game;

import java.awt.Graphics2D;

import math.Vector2d;

/**
 * @author Aaron
 * @author Ryan
 *
 */
public class Entity {
	private Vector2d position;
	private Vector2d velocity;
	private Hitbox hitbox;

	/**
	 * 
	 * @param position
	 * @param hitbox
	 */
	public Entity(Vector2d position, Hitbox hitbox) {
		this.position = position;
		this.hitbox = hitbox;
	}
	
	/**
	 * 
	 */
	public void init(){
	}
	
	/**
	 * 
	 */
	public void update(){
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
	}
	
	/**
	 * Checks whether two entities collide using the sat method.
	 * @param e
	 * @return
	 */
	public boolean collides(Entity e){
		boolean collides = false;
		
		int edgeCount = hitbox.vertices.length;
		int eEdgeCount = e.hitbox.vertices.length;
		Vector2d edge;
		Vector2d axis;
		double[] interval;
		double[] eInterval;
		
		//If aabb start sat method
		if(aabb(e)){
			collides = true;
			for(int i = 0; i < edgeCount + eEdgeCount; i++){
				if (i < edgeCount) {
		            edge = hitbox.edges[i];
		        } else{
		            edge = e.hitbox.edges[i - edgeCount];
		        }
				//Find the normalized axis perpendicular to the current edge.
				axis = new Vector2d(-edge.y, edge.x);
				axis = axis.normalize(axis);
				
				//Project the hitboxes onto the current axis.
				interval = projectOnAxis(axis);
				eInterval = e.projectOnAxis(axis);
				
				//Check if the projected intervals do not intersect.
				//No intersection means no collision
				if(interval[1] < eInterval[0] || eInterval[1] < interval[0]){
					collides = false;
					break;
				}
				
				//Check if the hitboxes will intersect
			}
		}
		
		return collides;
	}
	
	/**
	 * Checks whether two entities collide using the aabb method.
	 * @param e
	 * @return
	 */
	private boolean aabb(Entity e){
		boolean aabb = false;
		
		double[] xInterval = projectOnAxis(new Vector2d(1, 0));
		double[] exInterval = e.projectOnAxis(new Vector2d(1, 0));
		double[] yInterval = projectOnAxis(new Vector2d(0, 1));
		double[] eyInterval = e.projectOnAxis(new Vector2d(0, 1));
		
		if((xInterval[1] >= exInterval[0] && exInterval[1] >= xInterval[0]) || (yInterval[1] >= eyInterval[0] && eyInterval[1] >= yInterval[0])){
			aabb = true;
		}
		
		return aabb;
	}
	
	/**
	 * Projects the vertices of the hitbox onto a given normalized axis.
	 * @param axis
	 * @return
	 */
	public double[] projectOnAxis(Vector2d axis){
		double dotProduct = hitbox.getVertices(position)[0].dotProduct(axis);
		double[] interval = {dotProduct, dotProduct};
		
		for(int i = 1; i < hitbox.getVertices(position).length; i++){
			dotProduct = hitbox.getVertices(position)[i].dotProduct(axis);
			if(dotProduct < interval[0]){
				interval[0] = dotProduct;
			} else if(dotProduct > interval[1]){
				interval[1] = dotProduct;
			}
		}
		
		return interval;
	}
	
	public static void main(String[] args){
		Vector2d[] hitboxVertices = {
				new Vector2d(1, 0),
				new Vector2d(0, 2),
				new Vector2d(3, 2),
		};
		Vector2d[] hitboxVertices2 = {
				new Vector2d(0, 1),
				new Vector2d(1, 0),
				new Vector2d(2, 1),
		};
		Hitbox hitbox = new Hitbox(hitboxVertices);
		Hitbox hitbox2 = new Hitbox(hitboxVertices2);
		Entity e1 = new Entity(new Vector2d(1, 1), hitbox);
		Entity e2 = new Entity(new Vector2d(2, 1), hitbox2);
		
		System.out.println(e1.collides(e2));
	}

}
