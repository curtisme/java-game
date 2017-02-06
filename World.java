public class World {
	private TileMap map;
	private Unit guy;
	private Camera cam;
	
	public World(String path, int tileWidth) {
		map = new TileMap(path+".png", 8);
		guy = new Unit(80,80,"SpriteSheet.png");
		cam = new Camera(160,160,80,80,map.getMapWidth(), map.getMapHeight());
	}
	
	public TileMap getMap() {
		return map;
	}
	
	public void update(InputHandler input) {
		guy.update(input);
		cam.update(guy.x_pos,guy.y_pos);
	}
	
	public void render(int[] pixels) {
		for (int i=0;i<pixels.length;i++) {
			int x = getXTileOrd(i);
			int y = getYTileOrd(i);
			pixels[i] = map.drawPixel(x,y);
		}
		guy.render(pixels,cam);
	}
	
	private int getXTileOrd(int i) {
		int w = cam.getWidth();
		return (i)%w + cam.x_pos - w/2;
	}
	
	private int getYTileOrd(int i) {
		int w = cam.getWidth();
		return (i)/w + cam.y_pos - w/2;
	}
}