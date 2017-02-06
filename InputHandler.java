import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	
	public InputHandler(Game game) {
		game.addKeyListener(this);
	}
	public class Key {
		private boolean pressed = false;
		
		public boolean isPressed() {
			return pressed;
		}
		
		public void toggle(boolean pressed) {
			this.pressed = pressed;
		}
	}
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) up.toggle(true);
		if (e.getKeyCode() == KeyEvent.VK_A) left.toggle(true);
		if (e.getKeyCode() == KeyEvent.VK_D) right.toggle(true);
		if (e.getKeyCode() == KeyEvent.VK_S) down.toggle(true);
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) up.toggle(false);
		if (e.getKeyCode() == KeyEvent.VK_A) left.toggle(false);
		if (e.getKeyCode() == KeyEvent.VK_D) right.toggle(false);
		if (e.getKeyCode() == KeyEvent.VK_S) down.toggle(false);
	}
	
	public void keyTyped(KeyEvent e) {
	
	}
}