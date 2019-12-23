public abstract class RoundRobinGame implements IGame {

	public int[] play(IPlayer[] players) {
		init(players);
		int whoseTurn=0;
		while (!gameOver()) {
			execute(whoseTurn);
			whoseTurn=(whoseTurn+1)%players.length;
		}
		return getResult();
	}

	protected abstract int[] getResult() ;

	protected abstract void init(IPlayer[] players);

	protected abstract void execute(int whoseTurn);

	protected abstract boolean gameOver() ;
}