package de.nordakademie.domino;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Diese Klasse verwaltet ein Domino Game
 * Sie enthält einige elementare Fehler und Verletzungen der Namenskonventionen
 */
public class DominoGame {
    //alle Exemplarvariablen sind private! Verletzung des Geheimnisprinzips. Schwerer Fehler!
	private IPlayer[] players;
	
	private List<IDomino> heapOfDominos;
	
	private List<IDomino>[] playersDominos;
	//table: Name nicht unbedingt sprechend: es ist doch ein Domino
	private IDomino tableDomino;
	//istDran ist Deusch. Ausserdem erwartet man eine boolean Varaible
	private int indexOfCurrentPlayer;
	
	private IDominoPool pool;
	
	public DominoGame(IDominoPool pool) {
		this.pool = pool;
	}
	
	//Alle Methoden sind public. Geheimnisprinzip verletzt. Nur play und der Konstruktor müssen public sein. 
	//Schwerer Fehler
	private int[] getResult() {
		int[] result = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			result[i] = score(i);
		}
		return result;
	}

	private int score(int i) {
		int score = 0;
		for (IDomino domino : playersDominos[i]) {
			score += domino.value();
		}
		return score;
	}
	

	private void init(IPlayer[] players) {
		this.players = players;
		
		heapOfDominos = pool.provideShuffledDominoHeap();
		
		Collections.shuffle(heapOfDominos);
		distributeHeap();
	}

	
	@SuppressWarnings("unchecked")
	private void distributeHeap() {
		playersDominos = new List[players.length];
		for (int player = 0; player < this.players.length; player++) {
			//Der Typ Domino kann durch Type Inference ermittelt werden
			playersDominos[player] = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				drawDomino(player);
			}
		}

		tableDomino = drawDomino();
	}
	//DrawDomino: Namenskonventionen verletzt
	private void drawDomino(int player) {
		playersDominos[player].add(drawDomino());
	}

	//Collection Framework richtig nutzen: remove gibt das gelöschte Objekt zurück
	//Collection Framework richtig nutzen: statt size()>0 !isEmpty() nutzen
	private IDomino drawDomino() {
		if (!heapOfDominos.isEmpty()) {
			return heapOfDominos.remove(0);
		}
		return null;
	}

	//Variablenname x ist nicht sprechend. Kommentar wäre nötig! also Variable sprechend benennen
	//Parameter ist aber auch überflüssig!
	private void execute() {
		System.out.println("Anlegem�glichkeit: " + tableDomino);
		players[indexOfCurrentPlayer].currentStatus("Ihre Steine: " + playersDominos[indexOfCurrentPlayer].toString());
		//Der Typ Domino kann durch Type Inference ermittelt werden
		List<IDomino> joinMoves = new ArrayList<>();
		for (IDomino domino : playersDominos[indexOfCurrentPlayer]) {
			if (tableDomino.isJoinableLeft(domino) || tableDomino.isJoinableRight(domino)) {
				joinMoves.add(domino);                
			}
		}
		//CollectionFramework richtig nutzen: statt size()==0  isEmpty() nutzen
		if (joinMoves.isEmpty()) {
			forceDraw();
			return;
		}
		playerChoseMove(joinMoves);
	}

	private void playerChoseMove(List<IDomino> joinMoves) {
		String[] stringMoves = movesAsString(joinMoves);
		//Namenskonventionen verletzt: CamelCase
		int moveSelected = players[indexOfCurrentPlayer].selectMove(stringMoves);
		if (moveSelected < joinMoves.size()) {
			joinDomino(joinMoves.get(moveSelected));
		} else {
			playersDominos[indexOfCurrentPlayer].add(drawDomino());
		}
	}

	private void forceDraw() {
		System.out.println("Keine Anlegem�glichkeit");
		if (!heapOfDominos.isEmpty()) {
			playersDominos[indexOfCurrentPlayer].add(drawDomino());
		}
	}

	private void joinDomino(IDomino domino) {
		playersDominos[indexOfCurrentPlayer].remove(domino);
		//Geschachteltes if statement nicht n�tig. mit && arbeiten
		if (tableDomino.isJoinableRight(domino) && tableDomino.isJoinableLeft(domino) && 
				players[indexOfCurrentPlayer].selectMove(new String[] { "rechts anlegen", "links anlegen" }) == 1) {
				tableDomino.leftJoin(domino);
				return;
		}
		
		if (tableDomino.isJoinableRight(domino)) {
			tableDomino.rightJoin(domino);
		} else {
			tableDomino.leftJoin(domino);
		}
	}

	
	private String[] movesAsString(List<IDomino> joinMoves) {
		List<String> stringMoves = new ArrayList<>();
		for (IDomino domino : joinMoves) {
			stringMoves.add(domino.toString());
		}
		if (!heapOfDominos.isEmpty()) {
			stringMoves.add("ziehen");
		}

		return stringMoves.toArray(new String[stringMoves.size()]);
	}

	private boolean gameOver() {
		//Collection Framework richtig nutzen: isEmpty() verwenden
		boolean gameOver = playerFinished() || heapOfDominos.isEmpty() && noMoreMoves();
		if (gameOver) {
			printDominoes();
		}
		return gameOver;
	}

	private boolean playerFinished() {
		boolean finished = false;
		for (int i = 0; i < players.length; i++) {
			if (playersDominos[i].isEmpty()) {
				finished = true;
			}
		}
		return finished;
	}

	private void printDominoes() {
		System.out.println("Spielende");
		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i].getName() + ": " + playersDominos[i]);
		}
	}

	private boolean noMoreMoves() {
		for (int i = 0; i < players.length; i++) {
			for (IDomino domino : playersDominos[i]) {
				if (tableDomino.isJoinableLeft(domino) || tableDomino.isJoinableRight(domino) ) {
					return false;
				}
			}
		}
		//Collection Framework richtig nutzen: isEmpty() verwenden
		return heapOfDominos.isEmpty();
	}

	public int[] play(IPlayer[] players) {
		init(players);
		//Variablenname auf Deutsch
		//Lokale Variable durch Exemplarvariable ersetzen
		indexOfCurrentPlayer=0;
		while (!gameOver()) {
			execute();
			indexOfCurrentPlayer=(indexOfCurrentPlayer+1)%players.length;
		}
		return getResult();
	}
}
