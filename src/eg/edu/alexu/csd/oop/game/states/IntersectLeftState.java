package eg.edu.alexu.csd.oop.game.states;

import eg.edu.alexu.csd.oop.game.object.ImageObject;

public  class IntersectLeftState  implements State{

	@Override
	public int setX(int mX, ImageObject b) {
 if(mX>890){
		return b.getX();	
		}
 else {
	 return mX;
 }

		
	}

	@Override
	public int sety(int mY, ImageObject b) {
		return b.getY();
		
	}

	

}
