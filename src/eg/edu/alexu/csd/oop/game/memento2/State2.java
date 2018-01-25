package eg.edu.alexu.csd.oop.game.memento2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.Factory_Flyweight.ShapeObject;

public class State2 {

	private int score;

	 public State2(int score)
	 {
	this.score = score;
	}

	public State2() {
		// TODO Auto-generated constructor stub
	}

	public int getScore() {
		return score;
	}

}