package eg.edu.alexu.csd.oop.game.dynamicLinkage;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import eg.edu.alexu.csd.oop.game.Factory_Flyweight.ShapeObject;
import eg.edu.alexu.csd.oop.game.object.Bomb;
import eg.edu.alexu.csd.oop.game.object.Star;

public class ClassLoader implements ClassLoaderInterface {
	private Class<?>[] classes = new Class<?>[2] ;
	@Override
	public Class<?>[] getLoadedClasses() throws Exception {
		// TODO Auto-generated method stub
	//	final String pathToJar =System.getProperty("user.home") + "/Desktop/project3with(state)(iterator)(pool)(singleton)+Observer.rar";
		final String pathToJar ="Shapes.jar";
		//CreateLogger.debug(pathToJar);
		
		JarFile jarFile = new JarFile(pathToJar);
		Enumeration<JarEntry> e = jarFile.entries();
		URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		    while (e.hasMoreElements()) {
		        JarEntry je = (JarEntry) e.nextElement();
		        if(je.isDirectory() || !je.getName().endsWith(".class")){
		            continue;
		        }
		    // -6 because of .class
		        String className = je.getName().substring(0,je.getName().length()-6);
		        className = className.replace('/', '.');
		        Class<?> c = cl.loadClass(className);
		        if(Star.class.isAssignableFrom(c)){
		        	// plate class
		        	System.out.println("yes");
		    		classes[0]=c;  

		    	}
		     if(Bomb.class.isAssignableFrom(c)){
		        	// EGG class
		    		System.out.println("yes");
		        	classes[1]=c;
		    	}
		        }
		   
		   
		   jarFile.close();
		 

	return classes;
}




public static void main(String[] args) throws Exception {
	// TODO Auto-generated method stub
	
	ClassLoaderInterface o = new ClassLoader();
	 Class<?>[] classes = new Class<?>[2] ;
	 classes=o.getLoadedClasses();
	System.out.println(classes[0].getName());
	System.out.println(classes[1].getName());
	Constructor<?> con=classes[0].getConstructor(int.class,int.class);
	ShapeObject m=(ShapeObject) con.newInstance(5,6);
	System.out.println(m.getX());
	//ShapeObject m= (ShapeObject) classes[0].newInstance();
	
	try {
		o.getLoadedClasses();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	
	}


