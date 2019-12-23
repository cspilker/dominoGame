public interface IPlayer {
	String getName();

	int selectMove(String[] possibleMoves);

	void currentStatus(String string);
}