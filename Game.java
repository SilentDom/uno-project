import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javafx.scene.image.ImageView;

public class Game {
	private int currentPlayer;
	private String[] playerNames;
	private ArrayList<ArrayList<Card>> playerCards;
	private Deck deck;
	private ArrayList<Card> discardPile;
	private Card.Color checkColor;
	private Card.Type checkType;
	boolean gameDirection; 

	public Game(String[] players) {
		deck = new Deck();
		deck.shuffleDeck();
		discardPile = new ArrayList<Card>();
		gameDirection = false;
		playerNames = players;
		currentPlayer = 0;
		playerCards = new ArrayList<ArrayList<Card>>();

		for (int i = 0; i < players.length; i++) {
			ArrayList<Card> playerHand = new ArrayList<Card>(Arrays.asList(deck.drawCard()));
			playerCards.add(playerHand);
		}
	}
	public void startGame(Game game) {
		Card card = deck.drawCard();
		checkColor = card.getColor();
		checkType = card.getType();

		if (card.getType() == Card.Type.Wild) {
			startGame(game);
		}
		else if (card.getType() == Card.Type.WildDrawFour || card.getType() == Card.Type.DrawTwo) {
			startGame(game);
		}
		else if (card.getType() == Card.Type.Skip) {
			JLabel message = new JLabel(playerNames[currentPlayer] + "'s turn was skipped.");
			message.setFont(new Font("Arial", Font.BOLD, 48));
			JOptionPane.showMessageDialog(null, message);

			if (gameDirection == false) {
				currentPlayer = (currentPlayer + 1) % playerNames.length;
			}
			else if (gameDirection == true) {
				currentPlayer = (currentPlayer - 1) % playerNames.length;
				if (currentPlayer < 0) {
					currentPlayer = playerNames.length - 1;
				}
			}
		}
		else if (card.getType() == Card.Type.Reverse) {
			JLabel message = new JLabel(playerNames[currentPlayer] + " reversed the game direction.");
			message.setFont(new Font("Arial", Font.BOLD, 48));
			JOptionPane.showMessageDialog(null, message);
			if (gameDirection == true) {
				gameDirection = false;
			} else {
				gameDirection = true; 
			}
			currentPlayer = playerNames.length - 1;
		}
		discardPile.add(card);
	}

	public Card getTopCard() {
		return new Card(checkColor, checkType);
	}

	public ImageView getTopCardImage() {
		return new ImageView(checkColor + "_" + checkType + ".png");
	}

	public boolean isGameOver() {
		for (String players : this.playerNames) {
			if (hasEmptyHand(players)) {
				return true;
			}
		}
		return false;
	}

	public String getCurrentPlayer() {
		return this.playerNames[this.currentPlayer];
	}

	public String getPreviousPlayer (int i) {
		int index = this.currentPlayer - i;
		if (index < 0) {
			index = playerNames.length - 1;
		}
		return this.playerNames[index];
	}

	public String[] getPlayers() {
		return playerNames;
	}

	public ArrayList<Card> getPlayerHand(String playerName) {
		int i = Arrays.asList(playerNames).indexOf(playerName);
		return playerCards.get(i);
	}

	public int getPlayerCardAmount(String playerName) {
		return getPlayerHand(playerName).size();
	}

	public Card getPlayerCard (String playerName, int card) {
		ArrayList<Card> hand = getPlayerHand(playerName);
		return hand.get(card);
	}

	public boolean hasEmptyHand(String playerName) {
		return getPlayerHand(playerName).isEmpty();
	}
	
	public boolean checkCard(Card card) {
		return card.getColor() == checkColor | card.getType() == checkType;
	}
	
	public void checkPlayerTurn(String playerName) {
		if (this.playerNames[this.currentPlayer] != playerName) {
			throw new IllegalArgumentException("It is not " + playerName + "'s turn.");
		}
	}
	
	public void submitDraw(String playerName) {
		checkPlayerTurn(playerName);
		if (deck.isDeckEmpty()) {
			deck.replaceDeck(discardPile);
			deck.shuffleDeck();
		}
		getPlayerHand(playerName).add(deck.drawCard());
		if (gameDirection == false) {
			currentPlayer = (currentPlayer + 1) % playerNames.length;
		}
		else if (gameDirection == true) {
			currentPlayer = (currentPlayer - 1) % playerNames.length;
			if (currentPlayer < 0) {
				currentPlayer = playerNames.length - 1;
			}
		}
	}
	
	public void setCardColor(Card.Color color) {
		checkColor = color; 
	}
}

