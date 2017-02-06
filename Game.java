import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.image.*;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	int tickCount = 0;
	int waves = 0;

	public static final int WIDTH = 160, HEIGHT = 160;
	public static final int SCALE = 3;
	
	public static final String NAME = "Game";
	
	private JFrame frame;
	
	public boolean running = false;
	
	private BufferedImage image= new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private World world;
	private InputHandler input;
	
	public Game() {
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this,BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void init() {
		this.requestFocus();
		world = new World("Map", 8);
		input = new InputHandler(this);
	}
	
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			
			while(delta >= 1) {
				ticks++;
				tick();
				delta-=1;
				shouldRender = true;
			}
			
			/*try {
				Thread.sleep(2);
			}
			
			catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			
			if (shouldRender) {
				frames++;
				update();
				render();
				
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer+=1000;
				System.out.println(frames+","+ticks);
				frames = 0;
				ticks = 0;
				world.getMap().update();
			}
		}
	}
	
	public void tick() {
		tickCount++;
	}
	
	public void update() { 
	//System.out.println(input.right.isPressed());
		world.update(input);
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		world.render(pixels);
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image,0,0,getWidth(),getHeight(),null);
		
		g.dispose();
		bs.show();
	}
	public static void main(String[] args) {
		new Game().start();
	}
}