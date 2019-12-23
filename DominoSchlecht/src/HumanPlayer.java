public class HumanPlayer extends AbstractPlayer {

	private UserDialog dialog = new UserDialog();
	
	public HumanPlayer(String name) {
		super(name);
	}

	public int selectMove(String[] possibleMoves) {
		return dialog.getUserInput("Auswahlmöglichkeiten:\n", possibleMoves);
		
	}

	public void currentStatus(String string) {
		System.out.println(string);
	}
}