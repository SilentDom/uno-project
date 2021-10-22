import java.util.ArrayList;
import java.util.List;
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

	public Game() {
		for (int i = 0; i < 4; i ++) {
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
		return card.getCardColor() == checkColor | card.getCardType() == checkType;
	}

	public void setCardColor(CardColor color) {
		checkColor = color; 
	}

	public void givePlayerCard(Player player, Card card) {
		card = deck.getDeck().remove(0);
		player.getPlayerHand().add(card);
	}

	public void addToDiscardPile(Card card) {
		getDiscardPile().add(card);
		lastDiscard = new Image(card.getImage(card.getCardColor(), card.getCardType()));
	}

	public Player getActingPlayer(int playerIndex) {
		return players.get(playerIndex);
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

				Player player = getActingPlayer(0);
				player.getPlayerHand().remove(cardNumber); 
				addToDiscardPile(currentCard);
			}
		} catch (Exception e) {
			System.out.println("We caught an exception: " + e.toString());
		}
	}
}