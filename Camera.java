public class Camera {
	public int x_pos,y_pos;
	private int width, height;
	private int mapWidth, mapHeight;
	
	public Camera(int width, int height, int x, int y, int mWidth, int mHeight) {
		this.width = width;
		this.height = height;
		mapWidth = mWidth;
		mapHeight = mHeight;
		x_pos = x;
		y_pos = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isOnCam(int x, int y) {
		return Math.abs(x_pos-x) < width/2 && Math.abs(y_pos-y) < height/2;
	}
	
	public void update(int x, int y) {
		if (x-width/2>0&&x+width/2<mapWidth) x_pos = x;
		if (y-width/2>0&&y+width/2<mapHeight) y_pos = y;
	}
	
}
