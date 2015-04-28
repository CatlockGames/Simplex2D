/**
 * 
 */
package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import example.ExampleState;

/**
 * @author Aaron
 *
 */
public class GameStateManager {
	public static final int EXAMPLESTATE = 0;
	
	private GameState[] gameStates = {
			new ExampleState(this)
	};
	private int currentGameState;

	/**
	 * 
	 */
	public GameStateManager() {
		setGameState(EXAMPLESTATE);
	}
	
	/**
	 * 
	 */
	public void update(){
		gameStates[currentGameState].update();
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		gameStates[currentGameState].render(g2d);
	}
	
	/**
	 * 
	 * @param gameState
	 */
	public void setGameState(int gameState){
		currentGameState = gameState;
		gameStates[currentGameState].init();
	}
	
	/**
	 * 
	 * @param e
	 */
	public void mouseWheelMoved(MouseWheelEvent e) {
		gameStates[currentGameState].mouseWheelMoved(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void mouseDragged(MouseEvent e) {
		gameStates[currentGameState].mouseDragged(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void mouseMoved(MouseEvent e) {
		gameStates[currentGameState].mouseMoved(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void mouseClicked(MouseEvent e) {
		gameStates[currentGameState].mouseClicked(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void mouseEntered(MouseEvent e) {
		gameStates[currentGameState].mouseEntered(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void mouseExited(MouseEvent e) {
		gameStates[currentGameState].mouseExited(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {
		gameStates[currentGameState].mousePressed(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void mouseReleased(MouseEvent e) {
		gameStates[currentGameState].mouseReleased(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		gameStates[currentGameState].keyPressed(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		gameStates[currentGameState].keyReleased(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void keyTyped(KeyEvent e) {
		gameStates[currentGameState].keyTyped(e);
	}

}
