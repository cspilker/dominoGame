package de.nordakademie.domino;

public class Main {
	public void startDomino() {
		DominoGame game = new DominoGame(new BMDominoPool());
	
		
		new Match(new IPlayer[] { new HumanPlayer("Sie"), new BoringComputerPlayer("Ich") }, game).play();
		
	}
	
	public static void main(String[] args) {
		new Main().startDomino();
	}
}
