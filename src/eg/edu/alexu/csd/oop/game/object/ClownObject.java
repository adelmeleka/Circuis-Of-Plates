package eg.edu.alexu.csd.oop.game.object;


public class ClownObject extends ImageObject  {
		private String path;

	public ClownObject(int posX, int posY, String path) {
		
		super(posX, posY, path);
		this.path =path;
	}
	

	@Override
	public void setY(int mY) {
	
	}
	
	public String getpath(){
		return path;
	}
}
 