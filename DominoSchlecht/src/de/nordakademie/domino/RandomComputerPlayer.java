package de.nordakademie.domino;
import java.util.Random;

public class RandomComputerPlayer extends ComputerPlayer {

	private Random randomSequence = new Random();
	public RandomComputerPlayer(String name) {
		super(name);
	}


	@Override
	protected int myChoice(String[] possibleMoves) {
		return randomSequence.nextInt(possibleMoves.length);
	}
}