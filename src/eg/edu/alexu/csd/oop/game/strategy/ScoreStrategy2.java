package eg.edu.alexu.csd.oop.game.strategy;

public class ScoreStrategy2 implements ScoreStrategyInterfcae {

	private int score=0;

	public ScoreStrategy2(int score) {

		this.score = score;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub;
		return score;
	}

	@Override
	public void increaseScore() {
		// TODO Auto-generated method stub
		if (score == 0) {
			score = 1;
		}
		score = score + 2;
	}

}
