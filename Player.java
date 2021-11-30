import java.util.List;

public class Player {
    private String playerName;
    private List<Player> players;
    private List<Card> playerHand;
    private boolean isComputer = false;
    private boolean cardPlayed = false;
    private Game game;
    private int playerNum;
    // Constructor for the player(s):
    public Player(String playerName, int playerNum) {
        this.playerName = playerName;
    }

    public Game getGame() {
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

    // Gives the player the card they drew:
    public void givePlayerCard(Card card) {
        this.playerHand.add(card);
    }

    // Sets the player's hand:
    public void setPlayerHand(List<Card> hand) {
        this.playerHand = hand;
    }

    // Gets the card in the player's hand at the specific index passed to the method:
    public Card getPlayerCard(int index) {
        return playerHand.get(index);
    }

    // Sets whether the player is a computer player:
    public void setComputerBool(boolean value) {
        this.isComputer = value;
    }

    // Gets if the player is a computer:
    public boolean getComputerBool() {
        return isComputer;
    }

    // Set method that becomes true once a card is played:
    public void setCardPlayed(boolean value) {
        this.cardPlayed = value;
    }

    // Boolean method that checks if a card was played:
    public boolean getCardPlayed() {
        return cardPlayed;
    }

    // Displays the player's name and their hand:
    @Override
    public String toString() {
        return this.playerName + " " + this.playerHand;
    }
    

}