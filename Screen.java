
public class Screen {	
	public int xOffset = 0;
	public int yOffset = 0;
	
	public int width;
	public int height;
	
	
	public TileMap map;
	
	public Screen(int width, int height, TileMap map) {
		this.width = width;
		this.height = height;
		this.map = map;
	}
	
	/*public void render(int[] pixels, int offset, int row) {
		for (int yTile=yOffset>>3;yTile<=(yOffset+height)>>3;yTile++) {
			int yMin = yTile*8 - yOffset;
			int yMax = yMin + 8;
			if (yMin < 0) yMin=0;
			if (yMax>height) yMax=height;
			for (int xTile=xOffset>>3;xTile<=(xOffset+width)>>3;xTile++) {
				int xMin = xTile*8 - xOffset;
				int xMax = xMin + 8;
				if (xMin < 0) xMin=0;
				if (xMax>width) xMax=width;
				
				int tileIndex = (xTile & MAP_WIDTH_MASK)+(yTile & MAP_WIDTH_MASK)*MAP_WIDTH;
				
				for (int y=yMin;y<yMax;y++) {
					int sheetPixel = ((y+yOffset)&7)*sheet.width + ((xMin+xOffset)&7);
					int tilePixel = offset + xMin + y*row;
					for (int x=xMin;x<xMax;x++) {
						int colour = tileIndex*4+sheet.pixels[sheetPixel++];
						pixels[tilePixel++] = colours[colour];
					}
				}
			}
			
		}
	}*/
	
	
	public void render(int[] pixels, int offset, int row) {
		for (int i=0;i<pixels.length;i++) {
			int x = getXTileOrd(i);
			int y = getYTileOrd(i);
			pixels[i] = map.drawPixel(x,y);
		}
	}
	
	//takes pixel number and returns x ordinate 
	private int getXTileOrd(int i) {
		return (i)%width+xOffset;
	}
	
	private int getYTileOrd(int i) {
		return (i)/width+yOffset;
	}
	
}
