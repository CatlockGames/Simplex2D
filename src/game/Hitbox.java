/**
 * 
 */
package game;

import math.Vector2d;

/**
 * @author Ryan
 *
 */
public class Hitbox {
	public Vector2d[] vertices;
	public Vector2d[] edges;

	/**
	 * 
	 * @param vertices
	 */
	public Hitbox(Vector2d[] vertices) {
		this.vertices = vertices;
		this.edges = new Vector2d[this.vertices.length];
		this.buildEdges();
	}
	
	/**
	 * Builds the edges of the hitbox in order to construct normal vectors.
	 */
	private void buildEdges(){
		Vector2d p1;
		Vector2d p2;
		for(int i = 0; i < vertices.length; i++){
			p1 = vertices[i];
			if(i + 1 >= vertices.length){
				p2 = vertices[0];
			} else{
				p2 = vertices[i + 1];
			}
			edges[i] = p2.subtract(p1);
		}
	}
	
	/**
	 * Returns the hitbox's vertices relative to the origin.
	 * @param position
	 * @return
	 */
	public Vector2d[] getVertices(Vector2d position){
		Vector2d[] orHitbox = new Vector2d[vertices.length];
		for(int i = 0; i < vertices.length; i++){
			orHitbox[i] = new Vector2d(position.x + vertices[i].x, position.y + vertices[i].y);
		}
		
		return orHitbox;
	}

}
