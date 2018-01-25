package eg.edu.alexu.csd.oop.game.memento2;

import java.util.ArrayList;
import java.util.Stack;

public class Memento {
	private State2 state;
	public Memento(){
	}
public void addstate(State2 state){
	System.out.println(state.getScore());
	
	this.state= state;
}
public State2 getState(){
	
	return state;
}
public boolean hasstate(){
	if(state!= null){
		return true;
	}
	else{
		return false;
	}
}
}
