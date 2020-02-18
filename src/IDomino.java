package de.nordakademie.domino;


public interface IDomino {
	
	int value();

	String toString();
	
	boolean isJoinableRight(IDomino other);

	boolean isJoinableLeft(IDomino other);	
	
	boolean isJoinable(IDomino other);
	
	IDomino leftJoin(IDomino other);
	
	IDomino rightJoin(IDomino other);

}
