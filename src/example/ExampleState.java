/**
 * 
 */
package example;

import game.GamePanel;
import game.GameState;
import game.GameStateManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * @author Aaron
 * @author Ryan
 *
 */
public class ExampleState extends GameState {

	/**
	 * 
	 */
	public ExampleState(GameStateManager gameStateManager) {
		super(gameStateManager);
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics2D g2d) {
		//Clear the screen
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//Other drawing.
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
