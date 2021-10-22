import java.util.List;

public class Player {
	private String playerName;
	private List<Player> players;
	private List<Card> playerHand;
	
	// Constructor for the player(s):
	public Player (String playerName) {
		this.playerName = playerName;
	} 
	
	public Game getGame(Game game) {
		return game;
	}
	
	// Getter for the player's name:
	public String getPlayerName() {
		return playerName;
	}
	
	// Setter for the player's name:
	public void setPlayerName(String name) {
		this.playerName = name; 
	}

	// Getter for the players in the game: 
	public List<Player> getPlayers() {
		return players;
	}
	
	// Getter for the player's hand: 
	public List<Card> getPlayerHand() {
		return playerHand; 
	}
	
	// Sets the player's hand:
	public void setPlayerHand(List<Card> hand) {
		this.playerHand = hand;
	}
	
	public Card getPlayerCard(int hand) {
		return this.playerHand.get(hand);
	}
	
	@Override
	public String toString() {
		return this.playerName + " " + this.playerHand;
	}
}
