package eg.edu.alexu.csd.oop.game.Factory_Flyweight;


import eg.edu.alexu.csd.oop.game.states.State;
import eg.edu.alexu.csd.oop.game.object.ImageObject;
import eg.edu.alexu.csd.oop.game.states.IntersectLeftState;
import eg.edu.alexu.csd.oop.game.states.IntersectRightState;
import eg.edu.alexu.csd.oop.game.states.MovingState;

public class ShapeObject extends ImageObject {
private State intersectLeftState; 
private State intersectRightState; 
private State movingState; 
private State currentState;
	public ShapeObject(int posX, int posY, String path) {
		super(posX, posY, path);
	 intersectLeftState = new IntersectLeftState();
	 intersectRightState = new IntersectRightState();
	 movingState =  new MovingState();
	 currentState= movingState;	
	}
	@Override
		public void setX(int mX) {
		this.x=	currentState.setX(mX, this);
		}
@Override
	public void setY(int mY) {

this.y= currentState.sety(mY, this);
	}
public void setCurrentState(State state){
	currentState= state;
	
}
}
