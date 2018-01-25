package eg.edu.alexu.csd.oop.game.Factory_Flyweight;

import java.util.HashMap;

import eg.edu.alexu.csd.oop.game.GameObject;


public class Flyweight {
	
	private static final HashMap<String, GameObject> objectsMap = new HashMap<String, GameObject>();
    
	ShapeFactory shapeFactory = new ShapeFactory();


	   public GameObject getPlate(String path,int x,int y){
		  
		      Plate plate = (Plate)objectsMap.get(path);

		      if(plate == null) {
		    	  //create new one
		    	plate =  (Plate) shapeFactory.getShape("plate", x, path);
		         objectsMap.put(path, plate);

		         return plate;
		      }
		         else{
		    	  //reuse it with different coordinates
		    	  if(plate.getHeight()>700){

		    		  plate.setX(x);
		    		  plate.setY(y);
		    		  
		    		  return plate;
		    		  
		    	  }else{
		    		 Plate  newPlate =  (Plate) shapeFactory.getShape("plate", x,plate.getpath());
		    		 
		    		 return newPlate;
		    	  }
		    	  
		      }
   
}
	 

	   
	   public GameObject getEgg(String path,int x,int y){

		      Egg egg = (Egg)objectsMap.get(path);

		      if(egg == null) {
		    	  //create new one
		    	egg =  (Egg) shapeFactory.getShape("egg", x,path);
		         objectsMap.put(path, egg);
		         
		         return egg;

		      }
		         else{
		    	  //reuse it with different coordinates
		    	  if(egg.getHeight()>700){
		    		  egg.setX(x);
		    		  egg.setY(y);
		    		  
		    		  return egg;
		    		  
		    	  }else{
		    		  
		    		 Egg newEgg =  (Egg) shapeFactory.getShape("egg", x,egg.getpath());
		    		 
		    		 return newEgg;
		    	  }
		    	  
		      }
	      

}
	}
