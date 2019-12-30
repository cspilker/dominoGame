package de.nordakademie.domino;
public class BoringComputerPlayer extends ComputerPlayer {


	public BoringComputerPlayer(String name) {
		super(name);
	}

	protected int myChoice(String[] possibleMoves) {
		return 0;
	}
}