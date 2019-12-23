
public class Main {
	public void startDomino() {
		new Match(new IPlayer[] { new HumanPlayer("Sie"), new BoringComputerPlayer("Ich") }, new DominoGame()).play();
	}
	
	public static void main(String[] args) {
		new Main().startDomino();
	}
}
