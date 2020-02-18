package de.nordakademie.domino;


public class DominoPool extends AbstractDominoPool {

	public IDomino specialGetDomino(int i, int j) {
		return new Domino(i, j);
	}
}
