
public abstract class AbstractPlayer implements IPlayer {
 
	private final String name;

	public String getName() {
		return name;
	}

	public AbstractPlayer(String name) {
		super();
		this.name = name;
	}


	
}