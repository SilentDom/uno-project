import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javafx.scene.image.ImageView;

public class Game {
	private String player;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> discardPile;
	private Deck deck;
	private Card[] cards;
	private Card.Color checkColor;
	private Card.Type checkType;
	boolean gameDirection; 

	public Game() {
		deck = new Deck(108);
		discardPile = new ArrayList<Card>();
		gameDirection = false;
		playerCards = new ArrayList<Card>();
	}

	public void startGame(Game game) {
		deck.createDeck();
		Card card = deck.drawCard();
		checkColor = card.getColor();
		checkType = card.getType();

		if (card.getType() == Card.Type.Wild) {
			startGame(game);
		}
		else if (card.getType() == Card.Type.WildDrawFour || card.getType() == Card.Type.DrawTwo) {
			startGame(game);
		}
		discardPile.add(card);
	}

	public ArrayList<Card> getPlayerHand() {
		return playerCards;
	}

	public Card getPlayerCard(int hand) {
		return playerCards.get(hand);
	}

	public Card getTopCard() {
		return new Card(checkColor, checkType);
	}

	public ImageView getTopCardImage() {
		return new ImageView(checkColor + "_" + checkType + ".png");
	}

	public String getCurrentPlayer() {
		return player;
	}
	
	public ArrayList<Card> getDiscardPile() {
		return discardPile;
	}

	public boolean isGameOver() {
		if (hasEmptyHand()) {
			return true;
		}
		return false;
	}

	public boolean hasEmptyHand() {
		if (playerCards.size() == 0) {
			return true;
		}
		return false;
	}

	public boolean checkCard(Card card) {
		return card.getColor() == checkColor | card.getType() == checkType;
	}

	public void setCardColor(Card.Color color) {
		checkColor = color; 
	}

	public void cardPlayed(Card card, Card.Color color) {
		if (!checkCard(card)) {
			if (card.getColor() == Card.Color.Wild) {
				checkColor = card.getColor();
				checkType = card.getType();
			}
			if (card.getColor() != checkColor) {
				throw new IllegalArgumentException("Color chosen does not match the current color: " + checkColor);
			}
		}
		playerCards.remove(card);

		if (hasEmptyHand() == true) {
			JLabel msg = new JLabel("You won the game!");
			msg.setFont(new Font("Arial", Font.BOLD, 48));
			JOptionPane.showMessageDialog(null, msg);
			System.exit(0);
		}
		checkColor = card.getColor();
		checkType = card.getType();
		discardPile.add(card);
	}
}