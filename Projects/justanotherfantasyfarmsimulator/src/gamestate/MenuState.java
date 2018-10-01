package gamestate;

import tilemap.Background;

public class MenuState extends GameState{

	private Background background;
	
	public MenuState (GameStateManager gsm) {
		
		this.gsm = gsm;
		
	}
	
	public void init() {}
	public void update() {}
	public void draw (java.awt.Graphics2D graphics) {}
	public void keyPressed (int key) {}
	public void keyReleased (int key) {}
	
}
