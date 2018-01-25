package eg.edu.alexu.csd.oop.game.iterator;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public class ShapeIterator implements Container {
List<GameObject> array;
	@Override
	public Iterator getIterator(List<GameObject> array) {
		this.array=array;
		return new NameIterator();
	}
	private class NameIterator implements Iterator{
		int index;
		@Override
		public boolean hasNext() {
			 if(index < array.size()){
		            return true;
		         }
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext()){
	            return array.get(index++);
	         }
			return null;
		}
		
	}
	 
	}
