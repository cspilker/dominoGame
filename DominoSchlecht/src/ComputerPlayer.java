
public abstract class ComputerPlayer extends AbstractPlayer {

	public ComputerPlayer(String name) {
		super(name);
	}

	public int selectMove(String[] possibleMoves) {
		int myChoice = myChoice( possibleMoves);
		String selected = possibleMoves[myChoice];
		System.out.println(getName()+": "+selected);
		return myChoice;
	}

	public void currentStatus(String string) {
	}

    protected abstract int myChoice(String[] possibleMoves);

}
