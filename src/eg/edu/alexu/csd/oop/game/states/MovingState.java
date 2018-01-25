package eg.edu.alexu.csd.oop.game.states;

import eg.edu.alexu.csd.oop.game.object.ImageObject;

public class MovingState implements State {
	private int x;
	private int y;
	@Override
	public int setX(int mX, ImageObject b) {
			return x=mX;	
	}

	@Override
	public int sety(int mY, ImageObject b) {
		return y=mY;
	}

	

	
}
