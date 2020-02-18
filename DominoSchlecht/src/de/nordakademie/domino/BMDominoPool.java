package de.nordakademie.domino;


public class BMDominoPool extends AbstractDominoPool {

	public IDomino specialGetDomino(int i, int j) {	
		return new Domino(i, j);
	}
}
