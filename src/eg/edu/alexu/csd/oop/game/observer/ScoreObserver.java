package eg.edu.alexu.csd.oop.game.observer;

import eg.edu.alexu.csd.oop.game.strategy.ScoreStrategy1;
import eg.edu.alexu.csd.oop.game.strategy.ScoreStrategy2;
import eg.edu.alexu.csd.oop.game.strategy.ScoreStrategy3;

public class ScoreObserver implements Observer {
	private ScoreStrategy1 strategy1;
	private ScoreStrategy2 strategy2;
	private ScoreStrategy3 strategy3;
	private int score = 0;
	private int level;
	public ScoreObserver(int level){
		System.out.println(level);
		if (level == 1) {
			strategy1 = new ScoreStrategy1(0);
			strategy2 = null;
			strategy3 = null;
			this.level=level;
		}
		if (level == 2) {
			strategy2 = new ScoreStrategy2(0);
			strategy1 = null;
			strategy3 = null;
			this.level=level;
		}
		if (level == 3) {
			strategy3 = new ScoreStrategy3(0);
			strategy1 = null;
			strategy2 = null;
			this.level=level;
		}
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score=score;
	}
	@Override
	public int updateScore() {
		System.out.println("level"+level);
		if (level == 1) {
			strategy1.increaseScore();
			score=strategy1.getScore();
			System.out.println(score);
			return  score;
		}
		if (level == 2) {
			strategy2.increaseScore();
			score=strategy2.getScore();
			System.out.println(score);
			return score;
		}
		if (level == 3) {
			
			strategy3.increaseScore();
			score= strategy3.getScore();
			System.out.println(score);
			return score;
		}
		else {
			System.out.println(score);
			return 0;
		}
	}
}
