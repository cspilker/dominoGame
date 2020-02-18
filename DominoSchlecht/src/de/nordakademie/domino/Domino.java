package de.nordakademie.domino;

public class Domino implements IDomino {

	private final int left, right;

	
	public Domino(int left, int right) {
		this.left = left;
		this.right = right;
	}

	
	public int value() {
		return left + right;
	}

	
	public String toString() {
		return "[" + left + "|" + right + "]";
	}

	
	public boolean isJoinableRight(IDomino other) {
		return (other instanceof Domino) ? (this.right == ((Domino) other).left) : false;
	}

	
	public boolean isJoinableLeft(IDomino other) {
		return (other instanceof Domino) ? (this.left == ((Domino) other).right) : false;
	}

	
	public boolean isJoinable(IDomino other) {
		return (this.isJoinableRight(other) || this.isJoinableLeft(other));
	}

	
	public Domino leftJoin(IDomino other) {
		if (other instanceof Domino) {
			return isJoinableLeft(other) ? new Domino(((Domino) other).left, right) : null;
		} else {
			return null;
		}
	}

	
	public Domino rightJoin(IDomino other) {
		if (other instanceof Domino) {
			return isJoinableRight(other) ? new Domino(left, ((Domino) other).right) : null;
		} else {
			return null;
		}
	}
}