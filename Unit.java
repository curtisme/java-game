//A moving dude
public class Unit extends GameObject{
	protected int xv, yv;
	protected SpriteSheet sheet;
	private int t = 0;

	public Unit(int x, int y, String path) {
		this.x_pos = x;
		this.y_pos = y;
		this.sheet = new SpriteSheet(path,8,8);
		xv = yv = 0;
	}
	
	public void update(InputHandler input) {
		if (!input.left.isPressed()&&!input.right.isPressed()) xv = 0;
		if (input.left.isPressed()&&!input.right.isPressed()) xv = -1;
		if (!input.left.isPressed()&&input.right.isPressed()) xv = 1;
		
		if (!input.up.isPressed()&&!input.down.isPressed()) yv = 0;
		if (input.up.isPressed()&&!input.down.isPressed()) yv = -1;
		if (!input.up.isPressed()&&input.down.isPressed()) yv = 1;
		
		
		update();
	}
	
	public void update() {
		x_pos += xv;
		y_pos += yv;
	}
	
	public void render(int[] pixels, Camera cam) {
		if (cam.isOnCam(x_pos, y_pos)) {
			int a = x_pos + 80 - cam.x_pos;
			int b = y_pos + 80 - cam.y_pos;
			for (int i = a-4;i<a+4;i++) {
				for (int j = b-4;j<b+4;j++) {
					try {
						pixels[cam.getWidth()*j+i] = sheet.pixels[sheet.width*(j+4-b) + i+4-a + 8*t];
					}
					catch (Exception e) {
						continue;
					}
				}
			}
		}
	}
}