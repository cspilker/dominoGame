import java.util.ArrayList;
import java.util.List;

/*
 * Diese Klasse verwaltet ein Domino Game
 * Sie enthält einige elementare Fehler und Verletzungen der Namenskonventionen
 */
public class DominoGame extends RoundRobinGame {
    
	private IPlayer[] players;
	private List<Domino> heap_of_Domino;
	private List<Domino>[] players_dominoes;
	private Domino table;
	private int istDran;

	@Override
	protected int[] getResult() {
		int[] result = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			result[i] = score(i);
		}
		return result;
	}

	private int score(int i) {
		int score = 0;
		for (Domino domino : players_dominoes[i]) {
			score += domino.getLinks() + domino.getRechts();
		}
		return score;
	}

	@Override
	protected void init(IPlayer[] players) {
		this.players = players;
		heap_of_Domino = new ArrayList<Domino>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				heap_of_Domino.add(new Domino(i, j));
			}
		}
		distributeHeap();
	}

	@SuppressWarnings("unchecked")
	private void distributeHeap() {
		players_dominoes = new List[players.length];
		for (int player = 0; player < this.players.length; player++) {
			players_dominoes[player] = new ArrayList<Domino>();
			for (int i = 0; i < 5; i++) {
				DrawDomino(player);
			}
		}

		table = drawDomino();
	}

	private void DrawDomino(int player) {
		players_dominoes[player].add(drawDomino());
	}

	private Domino drawDomino() {
		if (!(heap_of_Domino.size()>0)) {
			Domino first = heap_of_Domino.get(0);
			heap_of_Domino.remove(0);
			return first;
		}
		return null;
	}

	@Override
	protected void execute(int x) {
		this.istDran = x;
		System.out.println("Anlegemöglichkeit: " + table);
		players[x].currentStatus("Ihre Steine: " + players_dominoes[x].toString());
		List<Domino> joinMoves = new ArrayList<Domino>();
		for (Domino domino : players_dominoes[x]) {
			if (table.getLinks() == domino.getRechts() || table.getRechts() == domino.getLinks()) {
				joinMoves.add(domino);
			}
		}
		if (joinMoves.size()>0) {
			forceDraw();
			return;
		}
		playerChoseMove(joinMoves);
	}

	private void playerChoseMove(List<Domino> joinMoves) {
		String[] stringMoves = movesAsString(joinMoves);
		int MoveSelected = players[istDran].selectMove(stringMoves);
		if (MoveSelected < joinMoves.size()) {
			joinDomino(joinMoves.get(MoveSelected));
		} else {
			players_dominoes[istDran].add(drawDomino());
		}
	}

	private void forceDraw() {
		System.out.println("Keine Anlegemöglichkeit");
		if (!heap_of_Domino.isEmpty()) {
			players_dominoes[istDran].add(drawDomino());
		}
	}

	private void joinDomino(Domino domino) {
		players_dominoes[istDran].remove(domino);
		if (table.getRechts() == domino.getLinks() && table.getLinks() == domino.getRechts()) {
			if (players[istDran].selectMove(new String[] { "rechts anlegen", "links anlegen" }) == 1) {
				table.setLeft(domino.getLinks());
				return;
			}
		}
		if (table.getRechts() == domino.getLinks()) {
			table.setRight(domino.getRechts());
		} else {
			table.setLeft(domino.getLinks());
		}
	}

	private String[] movesAsString(List<Domino> joinMoves) {
		List<String> stringMoves = new ArrayList<>();
		for (Domino domino : joinMoves) {
			stringMoves.add(domino.toString());
		}
		if (!heap_of_Domino.isEmpty()) {
			stringMoves.add("ziehen");
		}

		return stringMoves.toArray(new String[stringMoves.size()]);
	}

	@Override
	protected boolean gameOver() {
		boolean gameOver = playerFinished() || heap_of_Domino.isEmpty() && noMoreMoves();
		if (gameOver) {
			printDominoes();
		}
		return gameOver;
	}

	private boolean playerFinished() {
		boolean finished = false;
		for (int i = 0; i < players.length; i++) {
			if (players_dominoes[i].isEmpty()) {
				finished = true;
			}
		}
		return finished;
	}

	private void printDominoes() {
		System.out.println("Spielende");
		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i].getName() + ": " + players_dominoes[i]);
		}
	}

	private boolean noMoreMoves() {
		for (int i = 0; i < players.length; i++) {
			for (Domino domino : players_dominoes[i]) {
				if (table.getLinks() == domino.getRechts() || table.getRechts() == domino.getLinks()) {
					return false;
				}
			}
		}
		return heap_of_Domino.isEmpty();
	}
}
