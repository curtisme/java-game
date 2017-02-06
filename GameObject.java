public abstract class GameObject {
	protected int x_pos,y_pos;
	
	public abstract void update();
	public abstract void render(int[] pixels, Camera cam);
}