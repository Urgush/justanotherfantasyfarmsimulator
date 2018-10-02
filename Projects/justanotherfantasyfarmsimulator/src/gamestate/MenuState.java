package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import tilemap.Background;

public class MenuState extends GameState{

	private Background background;
	
	private int currentChoice = 0;
	private String[] options = {
			"Start",
			"Help",
			"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public MenuState (GameStateManager gsm) {
		
		this.gsm = gsm;
		
		try {
			background = new Background("/backgrounds/menubackground.gif", 1);
			background.setVector(-0.1, 0);
			
			titleColor = new Color(128,0,0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 12);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public void init() {}
	
	public void update() {
		background.update();
	}
	
	public void draw (java.awt.Graphics2D graphics) {
		background.draw(graphics);
		
		graphics.setColor(titleColor);
		graphics.setFont(titleFont);
		graphics.drawString("Just Another Fantasy Farm Simulator", 80, 70);
		
		graphics.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				graphics.setColor(Color.BLACK);
			} 
			else {
				graphics.setColor(Color.RED);
			}
			graphics.drawString(options[i], 145, 140 + i *15);
		}
	}
	
	private void select() {
		
		if(currentChoice == 0) {
			// start
		}
		if (currentChoice == 1) {
			// help
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
		
	}
	
	public void keyPressed (int key) {
		if(key == KeyEvent.VK_ENTER) {
			select();
		}
		if(key == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(key == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	
	public void keyReleased (int key) {}
	
}
