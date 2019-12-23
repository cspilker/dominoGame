
public class Match {
	private final IPlayer[] players;
	private final UserDialog dialog = new UserDialog();
	private final IGame game;
	int[] scores;

	public Match(IPlayer[] players, IGame game) {
		super();
		this.players = players;
		this.game = game;
		if (players.length != 2 || players[0] == null || players[1] == null || game == null) {
			throw new IllegalArgumentException();
		}
		scores = new int[players.length];
	}

	public void play() {
		boolean finished = false;
		while (!finished) {
			computeScore(game.play(players));
			printScore();
			finished = dialog.getUserInput("Weitere Runde? ", "Nein", "Ja") == 0;
		}
		System.out.println("Tschüß");
	}

	private void printScore() {
		for (int i = 0; i < players.length; i++) {
			System.out.print(players[i].getName() + ((i < players.length - 1) ? "-" : " "));
		}
		for (int i = 0; i < scores.length; i++) {
			System.out.print(scores[i] + ((i < scores.length - 1) ? ":" : ""));
		}
		System.out.println();
	}

	private void computeScore(int[] results) {
		for (int i = 0; i < scores.length; i++) {
			scores[i] += results[i];
		}
	}


}