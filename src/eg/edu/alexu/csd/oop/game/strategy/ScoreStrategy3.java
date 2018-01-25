package eg.edu.alexu.csd.oop.game.strategy;

public class ScoreStrategy3 implements ScoreStrategyInterfcae {
	private int score;

	public ScoreStrategy3(int score) {

		this.score = score;
	}

	@Override
	public void increaseScore() {
		score = score + 3;
	}

	@Override
	public int getScore() {
		return score;
	}

}
