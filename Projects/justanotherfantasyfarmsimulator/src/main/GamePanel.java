package main;

import gamestate.GameStateManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

// No need for serialization so far
@SuppressWarnings("serial")

public class GamePanel extends JPanel implements Runnable, KeyListener{

	// Dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	// Game Thread
	private Thread thread;
	private boolean running;
	private int fps = 60;
	private long targetTime = 1000 / fps;
	
	// Image
	private BufferedImage image;
	private Graphics2D graphics;
	
	// Game State Manager
	private GameStateManager gsm;
	
	public GamePanel() {
		
		// TODO Check what super does exactly and if it is necessary
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		
		// TODO Check addNotify 
		super.addNotify();
		
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}

	}

	private void init() {
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		graphics = (Graphics2D) graphics;
		
		running = true;
		
		gsm = new GameStateManager();
		
	}
	
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// Game Loop
		while (running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			try {
				Thread.sleep(wait);
			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		gsm.update();
	}
	
	public void draw() {
		gsm.draw(graphics);
	}
	
	public void drawToScreen() {
		
		Graphics graphicsDraw = getGraphics();
		graphicsDraw.drawImage(image, 0, 0, null);
		graphicsDraw.dispose();
		
	}
	
	public void keyTyped(KeyEvent key) {}
	
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}
	
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}
	
}
