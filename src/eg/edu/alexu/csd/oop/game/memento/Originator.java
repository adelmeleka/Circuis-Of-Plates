package eg.edu.alexu.csd.oop.game.memento;

public class Originator {
	 private States state;

	   public void setState(States state){
	      this.state = state;
	   }

	   public States getState(){
	      return state;
	   }

	   public Memento saveStateToMemento(){
	      return new Memento(state);
	   }

	   public void getStateFromMemento(Memento memento){
	      state = memento.getState();
	   }

}
