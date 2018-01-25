package eg.edu.alexu.csd.oop.game.memento;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
   private List<Memento> mementoList = new ArrayList<Memento>();

   public void add(Memento state){
      mementoList.add(state);
   }

   public Memento get(int index){
      return mementoList.get(index);
   }
   public boolean isEmpty (){
	   if (mementoList.isEmpty())
		   return true;
	   else return false;
   }
   public int getsize(){
	   return mementoList.size();
   }
}
