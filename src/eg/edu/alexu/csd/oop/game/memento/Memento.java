package eg.edu.alexu.csd.oop.game.memento;


public class Memento {
	   private States state;

	   public Memento(States state){
	      this.state=state;
	   }

	   public States getState(){
	      return state;
	   }	
	}
