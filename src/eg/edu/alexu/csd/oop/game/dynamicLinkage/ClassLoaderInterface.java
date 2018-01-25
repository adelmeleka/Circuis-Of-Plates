package eg.edu.alexu.csd.oop.game.dynamicLinkage;

public interface ClassLoaderInterface {
	public Class<?>[] getLoadedClasses() throws Exception;
	
}
