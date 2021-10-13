
public class Player {
	private String playerName;
	private boolean gameDirection;
	private int roundNumber = 1;
	private int numberOfPlayers;
	private int numberOfCards;
	private String[] playerList = new String[numberOfPlayers];
	private String[] playerCards = new String[numberOfCards];
	
	public Player (String[] playerList) {
		this.playerList = playerList;
	} 
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String name) {
		this.playerName = name; 
	}
	
	public boolean getGameDirection() {
		return gameDirection;
	}
	
	public void setGameDirection(boolean direction) {
		this.gameDirection = direction;
	}
	
	public int getRoundNumber() {
		return roundNumber;
	}
	
	public void setRoundNumber(int round) {
		this.roundNumber++; 
	}
	
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void setNumberOfPlayers(int players) {
		this.numberOfPlayers = players;
	}
	
	public String[] getPlayerList() {
		return playerList;
	}
	
	public void setPlayerList(String[] players) {
		for (int i = 0; i < playerList.length; i++) {
			playerList[i] = playerName;
		}
	}
	
	public String[] getPlayerCards() {
		return playerCards; 
	}
	
	public void printPlayerList() {
		for (String ele : playerList) {
			System.out.printf("%d ", ele);
		}
		System.out.println();
	}
	
	public void printCardList() {
		for (String ele : playerCards) {
			System.out.printf("%d ", ele);
		}
		System.out.println();
	}
}
