import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Game {
	private String player;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> discardPile;
	private Deck deck;
	private Card[] cards;
	private Card.Color checkColor;
	private Card.Type checkType;
	private Image lastDiscard;
	boolean gameDirection;
	int startingHandSize = 7;
	int maxHandSize = 9;

	public Game() {
		deck = new Deck(108);
		discardPile = new ArrayList<Card>();
		gameDirection = false;
		playerCards = new ArrayList<Card>();
	}

	public void startGame(Game game, int playerCount) {
		deck.createDeck();
		deck.shuffleDeck();
		Card card = deck.drawCard();
		checkColor = card.getColor();
		checkType = card.getType();
		dealCards(playerCount);
		Card discardCard = deck.drawCard();
		while (discardCard.getType() == Card.Type.Wild || discardCard.getType() == Card.Type.WildDrawFour || discardCard.getType() == Card.Type.DrawTwo) {
			this.addToDiscardPile(discardCard);
			discardCard = deck.drawCard();
		}
		this.addToDiscardPile(discardCard);
	}
	
//	private void DiscardCard(Card card) {
//		if (discardPile.size() == 0) {
//			if (card.getType() == Card.Type.Wild || card.getType() == Card.Type.WildDrawFour || card.getType() == Card.Type.DrawTwo) {
//				DiscardCard()
//			}
//		}
//		discardPile.add(card);
//		lastDiscard = new Image(card.getImage(card.getColor(), card.getType()));
//	}

	public ArrayList<Card> getPlayerHand() {
		return playerCards;
	}

	public Card getPlayerCard(int hand) {
		return playerCards.get(hand);
	}
	
	public Image getLastDiscard() {
		return lastDiscard;
	}
	
	public int getMaxHandSize() {
		return maxHandSize;
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
	
	public void givePlayerCard() {
		Card c = deck.drawCard();
		getPlayerHand().add(c);
	}

//	public void cardPlayed(Card card, Card.Color color) {
//		if (!checkCard(card)) {
//			if (card.getColor() == Card.Color.Wild) {
//				checkColor = card.getColor();
//				checkType = card.getType();
//			}
//			if (card.getColor() != checkColor) {
//				throw new IllegalArgumentException("Color chosen does not match the current color: " + checkColor);
//			}
//		}
//		playerCards.remove(card);
//
//		if (hasEmptyHand() == true) {
//			JLabel msg = new JLabel("You won the game!");
//			msg.setFont(new Font("Arial", Font.BOLD, 48));
//			JOptionPane.showMessageDialog(null, msg);
//			System.exit(0);
//		}
//		checkColor = card.getColor();
//		checkType = card.getType();
//		discardPile.add(card);
//	}
	
	public void addToDiscardPile(Card card) {
		getDiscardPile().add(card);
		lastDiscard = new Image(card.getImage(card.getColor(), card.getType()));
	}

	public void playCard(int cardNumber) {
		try {
			Card currentPlayerCard = getPlayerCard(cardNumber);
			getPlayerHand().remove(cardNumber); 
			addToDiscardPile(currentPlayerCard);
		} catch (Exception e) {
			System.out.println("We caught exception: " + e.toString());
		}
	}

//	public void setPlayerName() {
//		String currentPlayer = getCurrentPlayer();
//		player1Label.setText(currentPlayer + "'s cards");
//	}

	public void dealCards(int players) {
		for (int i = 0; i < startingHandSize; i++) {
			for (int j = 0; j < players ; j++) {
				Card c1 = deck.drawCard();
				getPlayerHand().add(c1);
			}
		}
	}




}