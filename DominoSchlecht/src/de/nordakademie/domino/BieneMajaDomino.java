package de.nordakademie.domino;

public class BieneMajaDomino implements IDomino {

	private final int left, right;
	private final String names[] = { "Maja", "Willy", "Thekla", "Flip", "Kassandra", "Puck" };

	
	public BieneMajaDomino(int left, int right) {
		this.left = left;
		this.right = right;
	}

	
	public int value() {
		return (left == 0 ? -1 : left) + (right == 0 ? -1 : right);
	}

	
	public String toString() {
		return "[" + names[left] + "|" + names[right] + "]";
	}

	
	public boolean isJoinableRight(IDomino other) {
		if (other instanceof BieneMajaDomino) {
			return right == ((BieneMajaDomino) other).left || right == 0 || ((BieneMajaDomino) other).left == 0;
		} else {
			return false;
		}
	}

	
	public boolean isJoinableLeft(IDomino other) {
		if (other instanceof BieneMajaDomino) {
			return left == ((BieneMajaDomino) other).right || left == 0 || ((BieneMajaDomino) other).right == 0;
		} else {
			return false;
		}
	}

	
	public boolean isJoinable(IDomino other) {
		return (this.isJoinableRight(other) || this.isJoinableLeft(other));
	}

	
	public BieneMajaDomino leftJoin(IDomino other) {
		if (other instanceof BieneMajaDomino) {
			return isJoinableLeft(other) ? new BieneMajaDomino(((BieneMajaDomino) other).left, right) : null;
		} else {
			return null;
		}
	}

	
	public BieneMajaDomino rightJoin(IDomino other) {
		if (other instanceof BieneMajaDomino) {
			return isJoinableRight(other) ? new BieneMajaDomino(left, ((BieneMajaDomino) other).right) : null;
		} else {
			return null;
		}
	}

}