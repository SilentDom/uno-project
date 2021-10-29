import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Collections;

public class Game {
	private Deck deck;
	private List<Player> players = new ArrayList<Player>();
	private List<Card> playerCards = new ArrayList<Card>();
	private List<Card> discardPile = new ArrayList<Card>();
	private boolean gameDirection;
	private CardColor checkColor;
	private CardType checkType;
	private Image lastDiscard;
	int turn = 0;

	public Game() {
		for (int i = 0; i < 3; i ++) {
			players.add(new Player("Player " + i));
		}
		gameDirection = false;
		deck = new Deck();
		deck.shuffleDeck();
		dealCards();
	}

	public void dealCards() {
		for (Player players : players) {
			List<Card> hand = new ArrayList<Card>();
			for (int i = 0; i < 7; i++) {
				hand.add(deck.getDeck().remove(0));
			}
			playerCards = hand;
			players.setPlayerHand(hand);
		}
		Card discardCard = deck.getDeck().remove(0);
		checkColor = discardCard.getCardColor();
		checkType = discardCard.getCardType();
		while (discardCard.getCardType() == CardType.Wild || discardCard.getCardType() == CardType.WildDrawFour || discardCard.getCardType() == CardType.DrawTwo) {
			this.addToDiscardPile(discardCard);
			discardCard = deck.getDeck().remove(0);
		}
		this.addToDiscardPile(discardCard);
	}

	public Image getLastDiscard() {
		return lastDiscard;
	}

	public Card getTopCard() {
		return new Card(checkColor, checkType);
	}

	public ImageView getTopCardImage() {
		return new ImageView(checkColor + "_" + checkType + ".png");
	}

	public Player getCurrentPlayer() {
		return players.get(0);
	}

	public List<Card> getDiscardPile() {
		return discardPile;
	}

	public boolean isGameOver() {
		Player thisPlayer = getActingPlayer(0);
		if (thisPlayer.getPlayerHand().size() == 0) {
			return true;
		}
		return false;
	}

	public boolean checkCard(Card card) {
		return card.getCardColor() == checkColor | card.getCardType() == checkType;
	}

	public void setCardColor(CardColor color) {
		checkColor = color; 
	}

	public void givePlayerCard(Player player) {
		Card card = deck.getDeck().remove(0);
		player.getPlayerHand().add(card);
	}

	public void addToDiscardPile(Card card) {
		getDiscardPile().add(card);
		lastDiscard = new Image(card.getImage(card.getCardColor(), card.getCardType()));
	}

	public Player getActingPlayer(int playerIndex) {
		return players.get(playerIndex);
	}
	
	public Deck getDeck() {
		return deck;
	}

	public void playCard(int playerNum, int cardNumber) {
		Player currentPlayer = getActingPlayer(playerNum);
		Card currentCard = currentPlayer.getPlayerCard(cardNumber);
		Card discardCard = getDiscardPile().get(getDiscardPile().size() - 1);
		try {
			if (currentCard.getCardType() == discardCard.getCardType()
					|| currentCard.getCardColor() == discardCard.getCardColor()
					|| currentCard.getCardType() == CardType.Wild
					|| currentCard.getCardType() == CardType.WildDrawFour
					|| discardCard.getCardType() == CardType.Wild	// TODO: temporary until wild color choosing
					|| discardCard.getCardType() == CardType.WildDrawFour) {

				Player player = getActingPlayer(playerNum);
				player.getPlayerHand().remove(cardNumber);
				addToDiscardPile(currentCard);
				cardAction(playerNum, currentCard);
			}
		} catch (Exception e) {
			System.out.println("We caught an exception: " + e.toString());
		}
	}
	
	public int nextPlayer(int turn) {
		if (!gameDirection && turn < players.size()-1)
			return turn+1;
		else if (!gameDirection)
			return 0;
		else if (gameDirection && turn > 0)
			return turn-1;
		else return players.size()-1;
	}
	

	public void cardAction(int playerNum, Card card) {
		if (card.getCardType() == CardType.DrawTwo) {
			givePlayerCard(players.get(nextPlayer(playerNum)));
			givePlayerCard(players.get(nextPlayer(playerNum)));
		}
		else if (card.getCardType() == CardType.Reverse && players.size() > 2) {
			gameDirection = true;
		}
		else if (card.getCardType() == CardType.Skip || card.getCardType() == CardType.Reverse && players.size() <= 2) {
			setTurn(nextPlayer(playerNum));
		}
	}
	
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
//	public void playComputerCard (int cardNum) {
//		Card currentPlayerCard = getPlayerCard(cardNum);
//		for(int i = 0; i < getPlayerHand().size(); i++){
//			if (isPlayable(currentPlayerCard)){
//				getPlayerHand().remove(cardNum); 
//			}
//		}
//		Card newCard = deck.getDeck().remove(0);
//		while (!isPlayable(newCard)) {
//			newCard = deck.getDeck().remove(0);
//			if (isPlayable(newCard)) {
//				getPlayerHand().remove(newCard);
//				getGame(game).getDiscardPile().add(newCard);
//			} else { 
//				getPlayerHand().add(newCard);
//			}
//		}
//	}
//	
//	public boolean isPlayable (Card currentPlayerCard) {
//		Card discardCard = getDiscardPile().get(getDiscardPile().size()-1);
//		if (currentPlayerCard.getCardType() == discardCard.getCardType()
//				|| currentPlayerCard.getCardColor() == discardCard.getCardColor()
//				|| currentPlayerCard.getCardType() == CardType.Wild
//				|| currentPlayerCard.getCardType() == CardType.WildDrawFour
//				|| discardCard.getCardType() == CardType.Wild	// temporary until wild color choosing
//				|| discardCard.getCardType() == CardType.WildDrawFour) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
}