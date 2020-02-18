package de.nordakademie.domino;

import java.util.ArrayList;
import java.util.Collections;


public abstract class AbstractDominoPool implements IDominoPool {

	
	public ArrayList<IDomino> provideShuffledDominoHeap() {

		ArrayList<IDomino> heapOfDominos = new ArrayList<IDomino>();

		for (int i = 0; i < 5; i++) {
			
			for (int j = 0; j < 5; j++) {
				heapOfDominos.add(specialGetDomino(i, j));
			}
		}
		Collections.shuffle(heapOfDominos);

		return heapOfDominos;
	}

	
	abstract IDomino specialGetDomino(int i, int j);
}
