/**
 * 
 */
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author Aaron
 * @author Ryan
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	//Dimensions
	public static final double SCREENWIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final double SCREENHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	public static double SCALE;
	
	//Properties
	public static final String TITLE = "Simplex2D";
	public static final String VERSION = "1.0.0";
	
	//Image
	public BufferedImage image;
	public Graphics2D g2d;
	
	//Thread
	public Thread thread;
	public boolean running;
	public int FPS = 60;
	public long targetTime = 1000 / FPS;
	
	public GameStateManager gameStateManager;
	
	/**
	 * 
	 */
	public GamePanel() {
		//Calculate scaling
		if(Math.abs(SCREENWIDTH / SCREENHEIGHT - 16.0f / 9.0f) / (16.0f / 9.0f) < 0.0005f){
			SCALE = ((int) (SCREENWIDTH / 1360 / 0.25)) * 0.25;
		} else if(SCREENWIDTH / SCREENHEIGHT - (16.0f / 9.0f) / (16.0f / 9.0f) > 0.0005f){
			SCALE = ((int) (SCREENHEIGHT / 1360 / 0.25)) * 0.25;
		} else if(SCREENWIDTH / SCREENHEIGHT - (16.0f / 9.0f) / (16.0f / 9.0f) < -0.0005f){
			SCALE = ((int) (SCREENWIDTH / 765 / 0.25)) * 0.25;
		}
		//Scales the width and height
		WIDTH *= SCALE;
		HEIGHT *= SCALE;

		//Created after SCALE is set
		gameStateManager = new GameStateManager();

		//Sets JPanel size
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public void init(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) image.getGraphics();
		running = true;
	}
	
	public void update(){
		//Update the current game state
		gameStateManager.update();
	}
	
	public void render(){
		//Render the current game state
		gameStateManager.render(g2d);
	}
	
	public void renderToScreen(){
		Graphics g = this.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}
	
	@Override
	public void run() {
		long start;
		long elapsed;
		long delay;
		
		init();
		
		//Game loop
		while(running){
			start = System.nanoTime();
			
			update();
			render();
			renderToScreen();
			
			elapsed = System.nanoTime() - start;
			delay = targetTime - elapsed / 1000000;
			FPS = (int) (1000000000 / elapsed);
			
			if(delay < 0){
				delay = 0;
			}
			
			try{
				Thread.sleep(delay);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//Initializes thread and listeners
	@Override
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			this.addKeyListener(this);
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			this.addMouseWheelListener(this);
			thread.start();
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e){
		gameStateManager.mouseWheelMoved(e);
	}

	@Override
	public void mouseDragged(MouseEvent e){
		gameStateManager.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e){
		gameStateManager.mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e){
		gameStateManager.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e){
		gameStateManager.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e){
		gameStateManager.mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e){
		gameStateManager.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e){
		gameStateManager.mouseReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e){
		gameStateManager.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e){
		gameStateManager.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e){
		gameStateManager.keyTyped(e);
	}

}
