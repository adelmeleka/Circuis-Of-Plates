package eg.edu.alexu.csd.oop.game.memento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.Factory_Flyweight.ShapeObject;
public class States {
	  public int x;
	   public int y;
	   public ArrayList<ShapeObject> left=new ArrayList<ShapeObject>();
	   public ArrayList<ShapeObject> right=new ArrayList<ShapeObject>();

	   public States(int x,int y,ArrayList left,ArrayList right){
	      this.x = x;
	      this.y=y;
	      this.left=left;
	      this.right=right;
	   }
}
