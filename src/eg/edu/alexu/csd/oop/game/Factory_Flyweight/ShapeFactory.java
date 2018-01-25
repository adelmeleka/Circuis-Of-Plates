package eg.edu.alexu.csd.oop.game.Factory_Flyweight;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.marker.egg;
import eg.edu.alexu.csd.oop.game.marker.plate;
import eg.edu.alexu.csd.oop.game.states.State;


public class ShapeFactory {
	
	private final int  yPosition = 50-35;	
	
	   //use getShape method to get object of type shape 
	
	   public GameObject getShape(String shapeType,int position ,String path){
	      if(shapeType == null){
	         return null;
	      }		
	      if(shapeType.equalsIgnoreCase("EGG")){
	         return  new Egg( position ,yPosition,path);
	         
	      } else if(shapeType.equalsIgnoreCase("PLATE")){
	         return new Plate(position, yPosition,path);
	         
	      }
	      
	      return null;
	 	
}

	   
	  
	   
}