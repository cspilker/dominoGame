public class Domino {
	private int left, right;

	public Domino(int left, int right) {
		super();
		this.left = left;
		this.right = right;
	}

	public int getLinks() {
		return left;
	}

	public int getRechts() {
		return right;
	}

	public void setLeft(int left) {
		this.left=left;
	}

	public void setRight(int right) {
		this.right=right;
	}


	public String toString() {
		return "["+left+"|"+right+"]";
	}
}
