package eg.edu.alexu.csd.oop.game.iterator;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface Container {
	   public Iterator getIterator(List<GameObject> array);
	}
