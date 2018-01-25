package eg.edu.alexu.csd.oop.game.Factory_Flyweight;

import com.sun.corba.se.spi.orbutil.fsm.State;

public interface Shape {
	
	public void setX(int mX);

	public void setY(int mY);
	
	public void setCurrentState(State state);


}
