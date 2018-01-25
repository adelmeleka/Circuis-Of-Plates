package eg.edu.alexu.csd.oop.game.states;

import eg.edu.alexu.csd.oop.game.object.ImageObject;

public  class IntersectRightState  implements State {

	@Override
	public int setX(int mx, ImageObject b) {
if(mx<=70){
		return b.getX();	
		}else  {
		return mx;	
		}

		
	}

	@Override
	public int sety(int my, ImageObject b) {
		return b.getY();
		// TODO Auto-generated method stub
		
	}

	

}
