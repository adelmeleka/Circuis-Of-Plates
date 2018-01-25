package eg.edu.alexu.csd.oop.game.world;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import com.sun.istack.internal.Pool;
import com.sun.media.jfxmediaimpl.platform.Platform;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.iterator.ShapeIterator;
import eg.edu.alexu.csd.oop.game.marker.egg;
import eg.edu.alexu.csd.oop.game.marker.plate;
import eg.edu.alexu.csd.oop.game.memento2.Memento;
import eg.edu.alexu.csd.oop.game.memento2.State2;
import eg.edu.alexu.csd.oop.game.object.BarObject;
import eg.edu.alexu.csd.oop.game.object.ClownObject;
import eg.edu.alexu.csd.oop.game.object.ImageObject;

import eg.edu.alexu.csd.oop.game.Factory_Flyweight.*;
import eg.edu.alexu.csd.oop.game.Factory_Flyweight.ShapeObject;
import eg.edu.alexu.csd.oop.game.Factory_Flyweight.Flyweight;
import eg.edu.alexu.csd.oop.game.Factory_Flyweight.ShapeFactory;
import eg.edu.alexu.csd.oop.game.observer.ScoreObserver;
import eg.edu.alexu.csd.oop.game.states.IntersectLeftState;
import eg.edu.alexu.csd.oop.game.states.IntersectRightState;
import eg.edu.alexu.csd.oop.game.states.MovingState;
import eg.edu.alexu.csd.oop.game.states.State;
import eg.edu.alexu.csd.oop.game.strategy.ScoreStrategy1;
import eg.edu.alexu.csd.oop.game.strategy.ScoreStrategy2;
import eg.edu.alexu.csd.oop.game.strategy.ScoreStrategy3;
import eg.edu.alexu.csd.oop.game.dynamicLinkage.ClassLoader;
public class Circuis implements World {

	private int leftShelf1width = 300;
	private int leftShelf2width = 150;
	private int rightShelf1width = 320;
	private int rightShelf2width = 200;
	private int level1YCoordinate = 50;
	private int level2YCoordinate = 125;
	private State intersectLeftState;
	private State intersectRightState;
	private State movingState;
	private int speed;
	private int level;
	private ScoreObserver scoreObserver;
	private static int MAX_TIME = 1 * 60 * 1000; // 1 minute
	private static int RIGHT_ROCKET = 123456;
	private static int LEFT_ROCKET = 654321;
	private int score = 0;
	private boolean fireRocket = false;
	private long startTime = System.currentTimeMillis();
	private final int width;
	private final int height;
	private ClownObject clown;
	private int clownspeed;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private List<GameObject> moving = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	private ArrayList<ShapeObject> rightlist = new ArrayList<ShapeObject>();
	private ArrayList<ShapeObject> leftlist = new ArrayList<ShapeObject>();
	private Memento memento;
	Flyweight flyWeight = new Flyweight();
	int i, j;
	private static Circuis circuisInstnce = null;
   private Class<?>[] classes = new Class<?>[2] ;
   private ClassLoader classLoader;
	private Circuis(int screenWidth, int screenHeight, int level) {
		width = screenWidth;
		height = screenHeight;
		this.level = level;
		memento = new Memento();
        classLoader= new ClassLoader();
      try {
		classes= classLoader.getLoadedClasses();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		// control objects (clown)
		ClownObject clown = new ClownObject(screenWidth / 3, (int) (screenHeight * 0.8) - 30, "/clown.png");
		control.add(clown);
		movingState = new MovingState();
		intersectLeftState = new IntersectLeftState();
		intersectRightState = new IntersectRightState();
		if (level == 1) {
			scoreObserver = new ScoreObserver(level);
			speed = 2;
			clownspeed = 20;
		}
		if (level == 2) {
			scoreObserver = new ScoreObserver(level);
			speed = 3;
			clownspeed = 30;
		}
		if (level == 3) {
			scoreObserver = new ScoreObserver(level);
			speed = 5;
			clownspeed = 40;
		}

		// moving objects
		// Factory & fly weight Design Pattern

		for (i = 0; i < 10; i++) {

			moving.add(flyWeight.getPlate("/plate" + (int) (1 + Math.random() * 3) + ".png", 0 - 50 * (2 * i),
					level1YCoordinate - 35));
			moving.add(flyWeight.getEgg("/egg" + (int) (1 + Math.random() * 3) + ".png", 0 - 50 * (2 * i + 13),
					level1YCoordinate - 35));

			moving.add(flyWeight.getEgg("/egg" + (int) (1 + Math.random() * 3) + ".png", 1000 + 50 * (2 * i),
					level1YCoordinate - 35));
			moving.add(flyWeight.getPlate("/plate" + (int) (1 + Math.random() * 3) + ".png", 1000 + 50 * (2 * i + 13),
					level1YCoordinate - 35));

		}

		// fixed objects (shelves)
		constant.add(new BarObject(0, level1YCoordinate, leftShelf1width, true, Color.BLACK));
		constant.add(
				new BarObject(width - rightShelf1width, level1YCoordinate, rightShelf1width + 10, true, Color.BLACK));
	}

	// singleton
	public static Circuis getinstance(int level) {
		if (circuisInstnce == null) {
			Circuis circuisInstnce = new Circuis(1000, 700, level);
			return circuisInstnce;
		} else {
			return circuisInstnce;
		}

	}

	private boolean intersect(GameObject o1, GameObject o2) {
		return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth())
				&& (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
	}

	private boolean intersectleft(GameObject o2) {
		return (Math.abs(
				(control.get(0).getX() + 10) - (o2.getX() + o2.getWidth() / 2)) <= (control.get(0).getWidth() - 100)
				&& o2.getY() == control.get(0).getY() - 35);
	}

	private boolean intersectright(GameObject o2) {
		return (Math.abs(
				(control.get(0).getX() + 100) - (o2.getX() + o2.getWidth() / 2)) <= (control.get(0).getWidth() - 100)
				&& o2.getY() == control.get(0).getY() - 35);
	}

	@Override
	public boolean refresh() {
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time
		// game	
		if ((int)(Math.random() * 1000) == 1) {	
			try {
				Constructor<?> con=classes[0].getConstructor(int.class,int.class);
				ShapeObject m=(ShapeObject) con.newInstance(width*(int)(Math.random()*2)-60,15);
				moving.add(m);
			
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		if ((int)(Math.random() * 1000) == 1) {	
			try {
				Constructor<?> con=classes[1].getConstructor(int.class,int.class);
				ShapeObject m=(ShapeObject) con.newInstance(width*(int)(Math.random()*2)-60,15);
				moving.add(m);
			
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		GameObject spaceShip = control.get(0);
		// moving starts
		List<GameObject> list = new LinkedList<GameObject>();
		for (GameObject object : moving) {
			list.add(object);
		}
		ShapeIterator shapeIterator = new ShapeIterator();
		eg.edu.alexu.csd.oop.game.iterator.Iterator it = shapeIterator.getIterator(list);
		while (it.hasNext()) {
			ShapeObject m = (ShapeObject) it.next();
			if (m.getX() < width / 2) {

				if (m.getX() > constant.get(0).getWidth() - 35) {
					m.setY((m.getY() + speed));
				} else
					m.setX((m.getX() + speed));
			}

			if ((m.getX()) > width / 2) {

				if (m.getX() < width - constant.get(1).getWidth()) {
					m.setY(m.getY() + speed);

				} else
					m.setX(m.getX() - speed);

			}

			if (m.getY() > getHeight()) {

				// reuse the objects in another position
				moving.remove(m);

				if (m instanceof egg) {

					if (m.getX() < width / 2) {

						moving.add(flyWeight.getEgg("/egg" + (int) (1 + Math.random() * 3) + ".png",
								0 - 50 * (2 * i + 5), level1YCoordinate - 35));

					} else if (m.getX() > width / 2) {

						moving.add(flyWeight.getEgg("/egg" + (int) (1 + Math.random() * 3) + ".png",
								1000 + 50 * (2 * i + 5), level1YCoordinate - 35));

					}
				} else if (m instanceof plate) {


					if (m.getX() < width / 2) {

						moving.add(flyWeight.getPlate("/plate" + (int) (1 + Math.random() * 3) + ".png",
								0 - 50 * (2 * i + 5), level1YCoordinate - 35));

					} else if (m.getX() > width / 2) {
						moving.add(flyWeight.getPlate("/plate" + (int) (1 + Math.random() * 3) + ".png",
								1000 + 50 * (2 * i + 5), level1YCoordinate - 35));

					}
				}

			}

			int position;

			if (leftlist.isEmpty()) {
				if (intersectleft(m)) {
					if (m.getpath() == "/star.png") {
						State2 state = new State2(scoreObserver.getScore());
						memento.addstate(state);
						moving.remove(m);
					} else if (m.getpath() == "/bomb.png") {
						if (memento.hasstate()) {
							State2 state = memento.getState();
							scoreObserver.setScore(state.getScore());
							moving.remove(m);

						} else {
							moving.remove(m);
							scoreObserver.setScore(0);
						}
					} else {
						(m).setCurrentState(intersectLeftState);

						leftlist.add(m);
						control.add(m);
						moving.remove(m);
					}
				}

			} else if (intersect(leftlist.get(leftlist.size() - 1), m) && !leftlist.isEmpty()) {
				m.setY(leftlist.get(leftlist.size() - 1).getY() - 10);
				if (m.getpath() == "/star.png") {
					State2 state = new State2(scoreObserver.getScore());
					memento.addstate(state);
					moving.remove(m);
				} else if (m.getpath() == "/bomb.png") {
					if (memento.hasstate()) {
						State2 state = memento.getState();
						scoreObserver.setScore(state.getScore());
						moving.remove(m);
					} else {
						moving.remove(m);
						scoreObserver.setScore(0);
					}
				} else {
					m.setX(leftlist.get(leftlist.size() - 1).getX());
					(m).setCurrentState(intersectLeftState);

					leftlist.add(m);
					control.add(m);
					moving.remove(m);
					if (leftlist.size() >= 3) {
						String s1 = leftlist.get(leftlist.size() - 1).getpath();
						String s2 = leftlist.get(leftlist.size() - 2).getpath();
						String s3 = leftlist.get(leftlist.size() - 3).getpath();
						// System.out.println(" " + s1 + " " + s2 + " " + s3);
						if ((s1.charAt(s1.length() - 5)) == (s2.charAt(s2.length() - 5))
								&& (s2.charAt(s2.length() - 5)) == (s3.charAt(s3.length() - 5))) {

							if (control.get(control.size() - 1).getX() > width / 2)
								position = 0 - 50 * (2 * j + 30);
							else
								position = 1000 + 50 * (2 * j + 5);

							System.out.println(
									leftlist.get(leftlist.size() - 1).getpath().substring(1, m.getpath().length() - 5));
							j++;

							moving.add(flyWeight.getPlate("/plate" + (int) (1 + Math.random() * 3) + ".png",
									0 - 50 * (2 * i + 5), level1YCoordinate - 35));
							moving.add(flyWeight.getEgg("/egg" + (int) (1 + Math.random() * 3) + ".png",
									0 - 50 * (2 * i + 5), level1YCoordinate - 35));

							moving.add(flyWeight.getEgg("/egg" + (int) (1 + Math.random() * 3) + ".png",
									1000 + 50 * (2 * i + 5), level1YCoordinate - 35));
							moving.add(flyWeight.getPlate("/plate" + (int) (1 + Math.random() * 3) + ".png",
									1000 + 50 * (2 * i + 5), level1YCoordinate - 35));

							leftlist.remove(leftlist.size() - 1);
							leftlist.remove(leftlist.size() - 1);
							leftlist.remove(leftlist.size() - 1);

							m.setY(level1YCoordinate - 35);
							m.setX((int) (0 + Math.random() * 2) * width + 10);

							control.remove(control.size() - 1);
							control.remove(control.size() - 1);
							control.remove(control.size() - 1);
							scoreObserver.updateScore();
							System.out.println("yes");

						}
					}

				}

			}
			if (rightlist.isEmpty()) {
				if (intersectright(m)) {
					if (m.getpath() == "/star.png") {
						State2 state = new State2(scoreObserver.getScore());
						memento.addstate(state);
						moving.remove(m);
					} else if (m.getpath() == "/bomb.png") {
						if (memento.hasstate()) {
							State2 state = memento.getState();
							scoreObserver.setScore(state.getScore());
							moving.remove(m);
						} else {
							moving.remove(m);
							scoreObserver.setScore(0);
						}
					} else {
						m.setCurrentState(intersectRightState);
						String color = m.getpath();

						rightlist.add(m);
						control.add(m);
						moving.remove(m);
					}

				}
			} else if (intersect(rightlist.get(rightlist.size() - 1), m) && !rightlist.isEmpty()) {
				if (m.getpath() == "/star.png") {
					State2 state = new State2(scoreObserver.getScore());
					memento.addstate(state);
					moving.remove(m);
				} else if (m.getpath() == "/bomb.png") {
					if (memento.hasstate()) {
						State2 state = memento.getState();
						scoreObserver.setScore(state.getScore());
						moving.remove(m);
					} else {
						moving.remove(m);
						scoreObserver.setScore(0);
					}
				} else {
					m.setY(rightlist.get(rightlist.size() - 1).getY() - 10);
					m.setCurrentState(intersectRightState);
					m.setX(rightlist.get(rightlist.size() - 1).getX());
					String color = m.getpath();

					rightlist.add(m);
					control.add(m);
					moving.remove(m);
					if (rightlist.size() >= 3) {

						String s1 = rightlist.get(rightlist.size() - 1).getpath();
						String s2 = rightlist.get(rightlist.size() - 2).getpath();
						String s3 = rightlist.get(rightlist.size() - 3).getpath();
						// System.out.println(" " + s1 + " " + s2 + " " + s3);
						if ((s1.charAt(s1.length() - 5)) == (s2.charAt(s2.length() - 5))
								&& (s2.charAt(s2.length() - 5)) == (s3.charAt(s3.length() - 5))) {

							System.out.println("right");

							if (control.get(control.size() - 1).getX() > width / 2)
								position = 0 - 50 * (2 * (j++) + 30);
							else
								position = 1000 + 50 * (2 * j + 5);
							j++;

							moving.add(flyWeight.getPlate("/plate" + (int) (1 + Math.random() * 3) + ".png",
									0 - 50 * (2 * i + 5), level1YCoordinate - 35));
							moving.add(flyWeight.getEgg("/egg" + (int) (1 + Math.random() * 3) + ".png",
									0 - 50 * (2 * i + 5), level1YCoordinate - 35));

							moving.add(flyWeight.getEgg("/egg" + (int) (1 + Math.random() * 3) + ".png",
									1000 + 50 * (2 * i + 5), level1YCoordinate - 35));
							moving.add(flyWeight.getPlate("/plate" + (int) (1 + Math.random() * 3) + ".png",
									1000 + 50 * (2 * i + 5), level1YCoordinate - 35));

							rightlist.remove(rightlist.size() - 1);
							rightlist.remove(rightlist.size() - 1);
							rightlist.remove(rightlist.size() - 1);

							control.remove(control.size() - 1);
							control.remove(control.size() - 1);
							control.remove(control.size() - 1);
							scoreObserver.updateScore();

						}
					}
				}

			}

		}
	
		return !timeout;
	}

	@Override
	public int getSpeed() {
		return 10;
	}

	@Override
	public int getControlSpeed() {
		return clownspeed;
	}

	@Override
	public List<GameObject> getConstantObjects() {
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return control;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getStatus() {

		return "Score=" + scoreObserver.getScore() + "   |   Time="
				+ Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);

		// update status
	}
}
