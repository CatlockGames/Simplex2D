/**
 * 
 */
package game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import math.Vector2d;

/**
 * @author Aaron
 * @author Ryan
 *
 */
public class Entity {
	private Vector2d position;
	private Vector2d velocity;
	private ArrayList<Hitbox> hitboxes;

	/**
	 * 
	 * @param position
	 * @param hitbox
	 */
	public Entity(Vector2d position, ArrayList<Hitbox> hitboxes) {
		this.position = position;
		this.hitboxes = hitboxes;
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
		int edgeCount;
		int eEdgeCount;
		Vector2d edge;
		Vector2d axis;
		double[] interval;
		double[] eInterval;
		
		for(int hitbox = 0; hitbox < hitboxes.size(); hitbox++){
			for(int ehitbox = 0; ehitbox < e.hitboxes.size(); ehitbox++){
				edgeCount = hitboxes.get(hitbox).vertices.length;
				eEdgeCount = e.hitboxes.get(ehitbox).vertices.length;
				
				//If aabb start sat method
				if(aabb(e, hitbox, ehitbox)){
					collides = true;
					for(int i = 0; i < edgeCount + eEdgeCount; i++){
						if (i < edgeCount) {
				            edge = hitboxes.get(hitbox).edges[i];
				        } else{
				            edge = e.hitboxes.get(ehitbox).edges[i - edgeCount];
				        }
						//Find the normalized axis perpendicular to the current edge.
						axis = new Vector2d(-edge.y, edge.x);
						axis = axis.normalize(axis);
						
						//Project the hitboxes onto the current axis.
						interval = projectOnAxis(axis, hitbox);
						eInterval = e.projectOnAxis(axis, ehitbox);
						
						//Check if the projected intervals do not intersect.
						//No intersection means no collision
						if(interval[1] < eInterval[0] || eInterval[1] < interval[0]){
							collides = false;
							break;
						}
						
						//Check if the hitboxes will intersect
					}
				}
			}
		}
		
		return collides;
	}
	
	/**
	 * Checks whether two entities collide using the aabb method.
	 * @param e
	 * @return
	 */
	private boolean aabb(Entity e, int hitbox, int ehitbox){
		boolean aabb = false;
		
		double[] xInterval = projectOnAxis(new Vector2d(1, 0), hitbox);
		double[] exInterval = e.projectOnAxis(new Vector2d(1, 0), ehitbox);
		double[] yInterval = projectOnAxis(new Vector2d(0, 1), hitbox);
		double[] eyInterval = e.projectOnAxis(new Vector2d(0, 1), ehitbox);
		
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
	public double[] projectOnAxis(Vector2d axis, int hitbox){
		double dotProduct = hitboxes.get(hitbox).getVertices(position)[0].dotProduct(axis);
		double[] interval = {dotProduct, dotProduct};
		
		for(int i = 1; i < hitboxes.get(hitbox).getVertices(position).length; i++){
			dotProduct = hitboxes.get(hitbox).getVertices(position)[i].dotProduct(axis);
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
				new Vector2d(1, 2),
				new Vector2d(0, 3)
		};
		Vector2d[] hitboxVertices2 = {
				new Vector2d(1, 0),
				new Vector2d(2, 3),
				new Vector2d(1, 2)
		};
		Vector2d[] hitboxVertices3 = {
				new Vector2d(0, 0),
				new Vector2d(1, 1),
				new Vector2d(1, 2)
		};
		Vector2d[] hitboxVertices4 = {
				new Vector2d(1, 1),
				new Vector2d(2, 0),
				new Vector2d(1, 2)
		};
		Hitbox hitbox = new Hitbox(hitboxVertices);
		Hitbox hitbox2 = new Hitbox(hitboxVertices2);
		Hitbox hitbox3 = new Hitbox(hitboxVertices3);
		Hitbox hitbox4 = new Hitbox(hitboxVertices4);
		ArrayList<Hitbox> hitboxes = new ArrayList<Hitbox>();
		ArrayList<Hitbox> hitboxes2 = new ArrayList<Hitbox>();
		hitboxes.add(hitbox);
		hitboxes.add(hitbox2);
		hitboxes2.add(hitbox3);
		hitboxes2.add(hitbox4);
		Entity e = new Entity(new Vector2d(1, 0), hitboxes);
		Entity e2 = new Entity(new Vector2d(2, 2.5), hitboxes2);
		
		System.out.println(e.collides(e2));
	}

}
