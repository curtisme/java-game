import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;	

public class SpriteSheet {
	public String path;
	public int width,height;
	protected int tilesWide, tilesHigh;
	protected int tileWidth,tileHeight;
	public int[] pixels;
	
	public SpriteSheet(String path, int tileWidth, int tileHeight) {
		BufferedImage image=null;
		
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		if (image==null) {
			return;
		}
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.path = path;
		this.width = image.getWidth();
		this.height = image.getHeight();
		tilesWide  = width/tileWidth;
		tilesHigh  = height/tileHeight;
		
		pixels = image.getRGB(0,0,width,height,null,0,width);
	}	
}
