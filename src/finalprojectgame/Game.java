package finalProjectGame;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public class Game {

	/** The game id. */
	private String gameId;

	/** The title. */
	private String title;

	/** The provider. */
	private String provider;

	/** The rented. */
	private boolean rented; // true not available, false available

	/** The type. */
	private String type;

	/** The brand new cost. */
	private int BRAND_NEW_COST = 4;

	/** The recent cost. */
	private int RECENT_COST = 3;

	/** The old cost. */
	private int OLD_COST = 2;

	/**
	 * Instantiates a new game.
	 */
	public Game() {

	}

	/**
	 * Instantiates a new game.
	 *
	 * @param type
	 *            the type
	 */
	public Game(String type) {
		this.setType(type);
	}

	/**
	 * Equals.
	 *
	 * @param gameInfo
	 *            the game info
	 * @return true, if successful
	 */
	public boolean equals(String gameInfo) {
		if (this.gameId.equals(gameInfo) || this.title.equals(gameInfo))
			return true;
		else
			return false;
	}

	/**
	 * Gets the fees.
	 *
	 * @param type
	 *            the type
	 * @param days
	 *            the days
	 * @return the fees
	 */
	public int getFees(String type, int days) {
		int sum;

		if (type == "BrandNew")
			sum = BRAND_NEW_COST * days;
		else if (type == "Recent")
			sum = RECENT_COST * days;
		else
			sum = OLD_COST * days;

		return sum;
	}

	/**
	 * Gets the game id.
	 *
	 * @return the game id
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * Sets the game id.
	 *
	 * @param gameId
	 *            the new game id
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the provider.
	 *
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * Sets the provider.
	 *
	 * @param provider
	 *            the new provider
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * Checks if is rented.
	 *
	 * @return true, if is rented
	 */
	public boolean isRented() {
		return rented;
	}

	/**
	 * Sets the rented.
	 *
	 * @param rented
	 *            the new rented
	 */
	public void setRented(boolean rented) {
		this.rented = rented;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return gameId + " " + type + " " + title + " " + provider + " "
				+ rented + "\n";
	}

}